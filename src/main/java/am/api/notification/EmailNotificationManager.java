package am.api.notification;

import am.common.ConfigParam;
import am.common.ConfigParam.COMPONENT;
import am.common.ConfigParam.FILE;
import am.common.ConfigUtils;
import am.core.config.AMConfigurationManager;
import am.core.config.AM_CC;
import am.core.logger.AME;
import am.core.logger.AMI;
import am.core.logger.AMLogger;
import am.exception.GeneralException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Singleton
public class EmailNotificationManager {
    @Inject AMConfigurationManager amConfigManager;
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
        String FN_NAME = "loadSystemComponent";
        try {
            AMLogger.startDebug(CLASS, FN_NAME, AM_CC.EMAIL_NOTFICATION_MANAGER, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

            FILE_NAME = amConfigManager.getConfigValue(AM_CC.EMAIL_NOTFICATION_MANAGER);
            FILE.EMAIL_NOTIFICATION = ConfigParam.APP_CONFIG_PATH + FILE_NAME;

            EMAIL_NOTIFICATION_CONFIG = ConfigUtils.readRemoteFiles(FILE.EMAIL_NOTIFICATION, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

            checkEmailPropertyFile();
            AMLogger.info(CLASS, FN_NAME, AMI.ENM_002, COMPONENT.EMAIL_NOTIFICATION_MANAGER);

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
            AMLogger.info(CLASS, FN_NAME, AMI.ENM_001);

            AMLogger.info(CLASS, FN_NAME, AMI.SYS_002, COMPONENT.EMAIL_NOTIFICATION_MANAGER);
            AMLogger.endDebug(CLASS, FN_NAME, EMAIL_NOTIFICATION_CONFIG);
        }catch (Exception ex){
            AMLogger.error(CLASS, FN_NAME, ex, AME.SYS_006, COMPONENT.EMAIL_NOTIFICATION_MANAGER, ex.getMessage());
        }
    }

    private void checkEmailPropertyFile() throws Exception{
        String FN_NAME = "checkEmailPropertyFile";
        if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_AUTH.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_AUTH.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_START_TLS_EN.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_START_TLS_EN.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_EN_SSL.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_EN_SSL.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_SSL_TRUST.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_SSL_TRUST.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_HOST.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_HOST.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_SMTP_PORT.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_SMTP_PORT.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_FROM.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_FROM.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_PASSWORD.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_PASSWORD.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(ENMC.MAIL_DEBUG.value()))
            throw new GeneralException(CLASS, FN_NAME, AME.IO_009, ENMC.MAIL_DEBUG.value(), AM_CC.EMAIL_NOTFICATION_MANAGER);
    }


    public void sendEmail(MailData mailData) throws Exception {
        String FN_NAME = "sendEmail";
        AMLogger.startDebug(CLASS, FN_NAME, mailData);
        try {

            String mailTo = mailData.getTo();
            String mailFrom = ConfigUtils.readValueFromPropertyFile(EMAIL_NOTIFICATION_CONFIG, ENMC.MAIL_FROM.value(), FILE_NAME);
//                    EMAIL_NOTIFICATION_CONFIG.getProperty(ENMC.MAIL_FROM.value());

            Message message = new MimeMessage(mailSession);

            try {
                message.setFrom(new InternetAddress(mailFrom));
            } catch (AddressException ex) {
                throw new GeneralException(CLASS, FN_NAME, ex, AME.ENM_001, mailFrom);
            }

            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            } catch (AddressException ex) {
                throw new GeneralException(CLASS, FN_NAME, ex, AME.ENM_001, mailTo);
            }

            message.setSubject(mailData.getSubject());
            message.setText(mailData.getBody());

            try {
                Transport.send(message);
                AMLogger.info(CLASS, FN_NAME, AMI.ENM_003, mailData.getSubject(), mailData.getTo());
            } catch (SendFailedException ex) {
                throw new GeneralException(CLASS, FN_NAME, ex, AME.ENM_002, mailData.getBody(), mailTo);
            }

            AMLogger.endDebug(CLASS, FN_NAME);
        }catch (Exception ex){
            AMLogger.error(CLASS, FN_NAME, AME.ENM_003, mailData.getSubject(), mailData.getTo(), ex.getMessage());

            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(CLASS, FN_NAME, ex, AME.ENM_003, mailData.getSubject(), mailData.getTo(), ex.getMessage());
        }
    }


}

