package am.services;

import am.data.enums.NMC;
import am.data.hibernate.model.Notification;
import am.main.api.AppLogger;
import am.main.api.ConfigManager;
import am.main.common.ConfigParam;
import am.main.common.ConfigParam.FILE;
import am.main.common.ConfigUtils;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.data.enums.AM_CC;
import am.main.data.enums.Folders;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.IC;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static am.main.common.ConfigParam.COMPONENT.NOTIFICATION_MANAGER;
import static am.shared.enums.Phase.AM_NOTIFICATION;
import static am.shared.enums.Source.AM;
import static java.nio.charset.StandardCharsets.UTF_8;


@Singleton
public class NotificationManager {
    @Inject private ConfigManager configManager;
    @Inject private AppLogger logger;
    private static final String CLASS = NotificationManager.class.getSimpleName();
    private Session mailSession;

    private static NotificationManager instance;
    private static final AppSession appSession = new AppSession(AM, AM_NOTIFICATION, CLASS);

    private static String EMAIL_NOTIFICATION_CONFIG_FN;
    private Properties EMAIL_NOTIFICATION_CONFIG = new Properties();

    private static Map<String, String> TEXT_FILES = new HashMap<>();

    private NotificationManager() {

    }

    public static NotificationManager getInstance() throws Exception{
        if (instance == null){
            synchronized(NotificationManager.class){
                if (instance == null){
                    instance = new NotificationManager();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String METHOD = "load";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, AM_CC.EMAIL_NOTIFICATION_MANAGER, NOTIFICATION_MANAGER);

            EMAIL_NOTIFICATION_CONFIG_FN = configManager.getConfigValue(AM_CC.EMAIL_NOTIFICATION_MANAGER);
            FILE.EMAIL_NOTIFICATION = ConfigParam.APP_CONFIG_PATH + EMAIL_NOTIFICATION_CONFIG_FN;

            EMAIL_NOTIFICATION_CONFIG = ConfigUtils.readRemotePropertyFiles(session, logger, FILE.EMAIL_NOTIFICATION, NOTIFICATION_MANAGER);

            checkEmailPropertyFile(session);
            logger.info(session, AMI.ENM_002, NOTIFICATION_MANAGER);



            logger.info(session, AMI.SYS_002, NOTIFICATION_MANAGER);
            logger.endDebug(session, EMAIL_NOTIFICATION_CONFIG);
        }catch (Exception ex){
            logger.FAILURE_LOGGER.error(MessageFormat.format(AME.SYS_006.value(), NOTIFICATION_MANAGER), ex);
            throw new IllegalStateException(MessageFormat.format(AME.SYS_006.value(), NOTIFICATION_MANAGER), ex);
        }
    }

    private void checkEmailPropertyFile(AppSession appSession) throws Exception{
        String METHOD = "checkEmailPropertyFile";
        AppSession session = appSession.updateSession(METHOD);

        if(!EMAIL_NOTIFICATION_CONFIG.containsKey(NMC.MAIL_SMTP_AUTH.value()))
            throw new GeneralException(session, AME.IO_009, NMC.MAIL_SMTP_AUTH.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(NMC.MAIL_SMTP_START_TLS_EN.value()))
            throw new GeneralException(session, AME.IO_009, NMC.MAIL_SMTP_START_TLS_EN.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(NMC.MAIL_SMTP_PORT.value()))
            throw new GeneralException(session, AME.IO_009, NMC.MAIL_SMTP_PORT.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
        else if(!EMAIL_NOTIFICATION_CONFIG.containsKey(NMC.MAIL_DEBUG.value()))
            throw new GeneralException(session, AME.IO_009, NMC.MAIL_DEBUG.value(), AM_CC.EMAIL_NOTIFICATION_MANAGER);
    }

    public void sendEmail(AppSession appSession, Notification mailData) throws Exception {
        String METHOD = "sendEmail";
        AppSession session = appSession.updateSession(METHOD);
        logger.startDebug(session, mailData);

        String subject = mailData.getTemplate().getSubject();
        String content = mailData.getFullMessage();
        String mailTo = mailData.getAddress();
        String senderMail = mailData.getSender().getAddress();

        try {
            String senderPassword = new String(Base64.decode(mailData.getSender().getPassword().getBytes()), UTF_8);
            mailSession = Session.getInstance(EMAIL_NOTIFICATION_CONFIG,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    EMAIL_NOTIFICATION_CONFIG.getProperty(senderMail),
                                    EMAIL_NOTIFICATION_CONFIG.getProperty(senderPassword)
                            );
                        }
                    }
            );
            logger.info(session, AMI.ENM_001);
            Message message = new MimeMessage(mailSession);

            try {
                message.setFrom(new InternetAddress(senderMail));
            } catch (AddressException ex) {
                throw new GeneralException(session, ex, AME.ENM_001, senderMail);
            }

            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            } catch (AddressException ex) {
                throw new GeneralException(session, ex, AME.ENM_001, mailTo);
            }


            message.setSubject(subject);
            message.setContent(mailData.getFullMessage(), "text/html");

            try {
                Transport.send(message);
                logger.info(session, AMI.ENM_003, subject, mailTo);
            } catch (SendFailedException ex) {
                throw new GeneralException(session, ex, AME.ENM_002, content, mailTo);
            }

            logger.endDebug(session);
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.ENM_003, subject, mailTo);
        }
    }

    public String readFile(Folders folder, String fileName) throws Exception{
        String METHOD = "readFile";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, folder, fileName);

            if(folder == null)
                throw new GeneralException(session, EC.AMT_0072);

            if(fileName == null)
                throw new GeneralException(session, EC.AMT_0073);

            String fileText = TEXT_FILES.get(fileName);

            if(fileText == null){
                String fullPath = ConfigParam.APP_CONFIG_PATH + folder.folderPath() + "\\" + fileName;
                fileText = ConfigUtils.readRemoteTextFiles(session, logger, fullPath, NOTIFICATION_MANAGER);
                TEXT_FILES.put(fileName, fileText);
            }

            logger.info(session, IC.AMT_0030);
            logger.endDebug(session, fileText);
            return fileText;
        }catch (Exception ex){
            throw new GeneralException(session, ex, EC.AMT_0074, fileName);
        }
    }
}

