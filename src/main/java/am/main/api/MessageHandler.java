package am.main.api;

import am.main.common.ConfigParam;
import am.main.common.ConfigUtils;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.data.enums.AM_CC;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.IC;
import am.shared.enums.WC;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.Properties;

import static am.shared.enums.Phase.MESSAGE_HANDLER;
import static am.shared.enums.Source.AM;

/**
 * Created by ahmed.motair on 1/8/2018.
 */
@Singleton
public class MessageHandler {
    @Inject private ConfigManager configManager;
    @Inject private AppLogger logger;
    private static final String CLASS = MessageHandler.class.getSimpleName();

    private static MessageHandler instance;
    private static final AppSession appSession = new AppSession(AM, MESSAGE_HANDLER, CLASS);

    private MessageHandler() {

    }
    public static MessageHandler getInstance() throws Exception{
        if (instance == null){
            synchronized(MessageHandler.class){
                if (instance == null){
                    instance = new MessageHandler();
                    instance.load();
                }
            }
        }
        return instance;
    }

    private String ERROR_MSG_FN, INFO_MSG_FN, WARN_MSG_FN;
    private static Properties ERROR_MESSAGES = new Properties();
    private static Properties INFO_MESSAGES = new Properties();
    private static Properties WARNING_MESSAGES = new Properties();

    @PostConstruct
    private void load(){
        String METHOD = "load";
        AppSession session = appSession.updateSession(METHOD);
        try {
            ERROR_MSG_FN = configManager.getConfigValue(AM_CC.ERROR_HANDLER);
            ConfigParam.FILE.ERROR_MESSAGES = ConfigParam.APP_CONFIG_PATH + ERROR_MSG_FN;
            ERROR_MESSAGES = ConfigUtils.loadPropertySystemComponent(session, logger, ConfigParam.FILE.ERROR_MESSAGES, ConfigParam.COMPONENT.MESSAGE_HANDLER);

            INFO_MSG_FN = configManager.getConfigValue(AM_CC.INFO_HANDLER);
            ConfigParam.FILE.INFO_MESSAGES = ConfigParam.APP_CONFIG_PATH + INFO_MSG_FN;
            INFO_MESSAGES = ConfigUtils.loadPropertySystemComponent(session, logger, ConfigParam.FILE.INFO_MESSAGES, ConfigParam.COMPONENT.MESSAGE_HANDLER);

            WARN_MSG_FN = configManager.getConfigValue(AM_CC.WARNING_HANDLER);
            ConfigParam.FILE.WARNING_MESSAGES = ConfigParam.APP_CONFIG_PATH + WARN_MSG_FN;
            WARNING_MESSAGES = ConfigUtils.loadPropertySystemComponent(session, logger, ConfigParam.FILE.WARNING_MESSAGES, ConfigParam.COMPONENT.MESSAGE_HANDLER);
        }catch (Exception ex){
            logger.FAILURE_LOGGER.error(MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.MESSAGE_HANDLER), ex);
            throw new IllegalStateException(MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.MESSAGE_HANDLER), ex);
        }
    }

    public String getMsg(EC errorCode, Object ... arguments) throws Exception{
        String METHOD = "getMsg";
        AppSession session = appSession.updateSession(METHOD);

//        try {
            logger.startDebug(session, errorCode, arguments);

            if(ERROR_MESSAGES == null || ERROR_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(session, AME.IO_005, ERROR_MSG_FN);
            }else if(errorCode == null)
                throw new GeneralException(session, AME.SYS_007, "Error Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(session, logger, ERROR_MESSAGES, errorCode.toString(), ERROR_MSG_FN);
            message = errorCode.toString() + ": " + ConfigUtils.formatMsg(session, logger, message, arguments);

            logger.info(session, AMI.IO_003, "Error Message", errorCode.toString());
            logger.endDebug(session, message);
            return message;
//        }catch (Exception ex){
//            logger.error(session, ex, AME.IO_008, "Error Message", errorCode.toString());
//            return null;
//        }
    }

    public String getMsg(IC infoCode, Object ... arguments) throws Exception{
        String METHOD = "getMsg";
        AppSession session = appSession.updateSession(METHOD);
//        try {
            logger.startDebug(session, infoCode, arguments);

            if(INFO_MESSAGES == null || INFO_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(session, AME.IO_005, INFO_MSG_FN);
            }else if(infoCode == null)
                throw new GeneralException(session, AME.SYS_007, "Info Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(session, logger, INFO_MESSAGES, infoCode.toString(), INFO_MSG_FN);
            message = infoCode.toString() + ": " + ConfigUtils.formatMsg(session, logger, message, arguments);

            logger.info(session, AMI.IO_003, "Info Message", infoCode.toString());
            logger.endDebug(session, message);
            return message;
//        }catch (Exception ex){
//            logger.error(session, ex, AME.IO_008, "Info Message", infoCode.toString());
//            return null;
//        }
    }

    public String getMsg(WC warnCode, Object ... arguments) throws Exception{
        String METHOD = "getMsg";
        AppSession session = appSession.updateSession(METHOD);
//        try {
            logger.startDebug(session, warnCode, arguments);

            if(WARNING_MESSAGES == null || WARNING_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(session, AME.IO_005, WARN_MSG_FN);
            }else if(warnCode == null)
                throw new GeneralException(session, AME.SYS_007, "Warning Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(session, logger, WARNING_MESSAGES, warnCode.toString(), WARN_MSG_FN);
            message = warnCode.toString() + ": " + ConfigUtils.formatMsg(session, logger, message, arguments);

            logger.info(session, AMI.IO_003, "Warning Message", warnCode.toString());
            logger.endDebug(session, message);
            return message;
//        }catch (Exception ex){
//            logger.error(session, ex, AME.IO_008, "Warning Message", warnCode.toString());
//            return null;
//        }
    }
}
