package am.main.api;

import am.main.data.dto.MailData;
import am.main.data.enums.ENMC;
import am.main.common.ConfigParam;
import am.main.common.ConfigParam.COMPONENT;
import am.main.common.ConfigParam.FILE;
import am.main.common.ConfigUtils;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.core.config.AMConfigurationManager;
import am.main.data.enums.AM_CC;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.data.enums.Source;
import am.shared.session.Phase;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static am.shared.session.Phase.AM_LIBRARY;


@Singleton
public class EmailNotificationManager {
    @Inject private AMConfigurationManager amConfigManager;
    @Inject private AppLogger logger;
    private static String FILE_NAME;
    private static final String CLASS = "EmailNotificationManager";
    private Properties EMAIL_NOTIFICATION_CONFIG;
    private Session mailSession;
    private static EmailNotificationManager instance;

    private EmailNotificationManager() {

    }

    public static EmailNotificationManager getInstance() throws Exception{
        if (instance == null){
            synchronized(EmailNotificationManager.class){
                if (instance == null){
                    instance = new EmailNotificationManager();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        AppSession session = new AppSession(Source.AM, Phase.AM_INITIALIZING, CLASS, FN_NAME);

        try {
            logger.startDebug(session, AM_CC.EMAIL_NOTIFICATION_MANAGER, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

            FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.EMAIL_NOTIFICATION_MANAGER);
            FILE.EMAIL_NOTIFICATION = ConfigParam.APP_CONFIG_PATH + FILE_NAME;

            EMAIL_NOTIFICATION_CONFIG = ConfigUtils.readRemoteFiles(session, FILE.EMAIL_NOTIFICATION, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

            checkEmailPropertyFile(session);
            logger.info(session, AMI.ENM_002, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

            boolean sessionCreationDone = false;
            mailSession = Session.getInstance(EMAIL_NOTIFICATION_CONFIG,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                            EMAIL_NOTIFICATION_CONFIG.getProperty(ENMC.MAIL_FROM.value()),
                            EMAIL_NOTIFICATION_CONFIG.getProperty(ENMC.MAIL_PASSWORD.value())
                        );
                    }
                }
            );
            logger.info(session, AMI.ENM_001);

            logger.info(session, AMI.SYS_002, COMPONENT.EMAIL_NOTIFICATION_MANAGER);
            logger.endDebug(session, EMAIL_NOTIFICATION_CONFIG);
        }catch (Exception ex){
            logger.error(session, ex, AME.SYS_006, COMPONENT.EMAIL_NOTIFICATION_MANAGER);
        }
    }

    private void checkEmailPropertyFile(AppSession appSession) throws Exception{
        String FN_NAME = "checkEmailPropertyFile";
        AppSession session = appSession.updateSession(FN_NAME);

        if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_AUTH.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_AUTH.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_START_TLS_EN.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_START_TLS_EN.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_EN_SSL.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_EN_SSL.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_SSL_TRUST.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_SSL_TRUST.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_HOST.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_HOST.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_PORT.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_SMTP_PORT.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_FROM.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_FROM.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_PASSWORD.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_PASSWORD.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_DEBUG.value()))
            throw new GeneralException(session, AME.IO_009, ENMC.MAIL_DEBUG.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
    }


    public void sendEmail(AppSession appSession, MailData mailData) throws Exception {
        String FN_NAME = "sendEmail";
        AppSession session = appSession.updateSession(AM_LIBRARY, CLASS, FN_NAME);
        logger.startDebug(session, mailData);
        try {

            String mailTo = mailData.getTo();
            String mailFrom = ConfigUtils.readValueFromPropertyFile(session, EMAIL_NOTIFICATION_CONFIG, ENMC.MAIL_FROM.value(), FILE_NAME);

            Message message = new MimeMessage(mailSession);

            try {
                message.setFrom(new InternetAddress(mailFrom));
            } catch (AddressException ex) {
                throw new GeneralException(session, ex, AME.ENM_001, mailFrom);
            }

            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            } catch (AddressException ex) {
                throw new GeneralException(session, ex, AME.ENM_001, mailTo);
            }

            message.setSubject(mailData.getSubject());
            message.setText(mailData.getBody());

            try {
                Transport.send(message);
                logger.info(session, AMI.ENM_003, mailData.getSubject(), mailData.getTo());
            } catch (SendFailedException ex) {
                throw new GeneralException(session, ex, AME.ENM_002, mailData.getBody(), mailTo);
            }

            logger.endDebug(session);
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.ENM_003, mailData.getSubject(), mailData.getTo());
        }
    }
}

