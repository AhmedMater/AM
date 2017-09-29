package am.api.info;

import am.api.logger.AppLogger;
import am.common.ConfigParam;
import am.common.ConfigParam.COMPONENT;
import am.common.ConfigParam.FILE;
import am.common.ConfigUtils;
import am.core.config.AMConfigurationManager;
import am.core.config.AM_CC;
import am.common.enums.AME;
import am.common.enums.AMI;
import am.exception.GeneralException;
import am.session.AppSession;
import am.session.Phase;
import am.session.Source;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;

/**
 * Created by ahmed.motair on 9/8/2017.
 */
@Singleton
public class InfoHandler{
    @Inject private AMConfigurationManager amConfigManager;
    @Inject private AppLogger logger;
    private static final String CLASS = "InfoHandler";
    private static Properties INFO_MESSAGES = new Properties();
    private static InfoHandler instance;
    private static String FILE_NAME;

    private InfoHandler() {

    }

    public static InfoHandler getInstance() throws Exception{
        if (instance == null){
            synchronized(InfoHandler.class){
                if (instance == null){
                    instance = new InfoHandler();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        AppSession session = new AppSession(Phase.INITIAL_APP, Source.AM, CLASS, FN_NAME);

        FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.INFO_HANDLER);
        FILE.INFO_MESSAGES = ConfigParam.APP_CONFIG_PATH + FILE_NAME;
        INFO_MESSAGES = ConfigUtils.loadSystemComponent(session, FILE.INFO_MESSAGES, COMPONENT.INFO_HANDLER);

        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
    }

//    public String getMsg(IC infoCode, Object ... arguments){
//        String FN_NAME = "getMsg";
//        try {
//            AMLogger.startDebug(CLASS, FN_NAME, infoCode, arguments);
//
//            if(infoCode == null)
//                throw new GeneralException(session, EC.IO_0019);
//            else if(INFO_MESSAGES == null || INFO_MESSAGES.isEmpty())
//                throw errorHandler.generalException(session, EC.IO_0030, FILE.INFO_MESSAGES);
//
//            String infoMsg = "";
//            infoMsg = ConfigUtils.readValueFromPropertyFile(session, INFO_MESSAGES, infoCode.toString(), FILE.INFO_MESSAGES);
//            infoMsg = ConfigUtils.formatMsg(session, infoMsg, arguments);
//
//            AMLogger.info(session, SystemMsg.AM_I_0006, ConfigParam.INFO_MESSAGE, infoCode.toString());
//            AMLogger.endDebugLog(session, infoMsg);
//            return infoMsg;
//        }catch (Exception ex){
//            AMLogger.log(session, EC.IO_0015, infoCode.toString(), ex.getMessage());
//            return "";
//        }
//    }

    public String getMsg(AppSession appSession, IC infoCode, Object ... arguments){
        String FN_NAME = "getMsg";
        AppSession session = appSession.updateSession(Phase.INFO_LOGGING, Source.AM, CLASS, FN_NAME);
        try {
            logger.startDebug(session, infoCode, arguments);

            if(INFO_MESSAGES == null || INFO_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(session, AME.IO_005, FILE_NAME);
            }else if(infoCode == null)
                throw new GeneralException(session, AME.SYS_007, "Info Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(session, INFO_MESSAGES, infoCode.toString(), FILE_NAME);
            message = ConfigUtils.formatMsg(session, message, arguments);

            logger.info(session, AMI.IO_003, "Info Message", infoCode.toString());
            logger.endDebug(session, message);
            return message;
        }catch (Exception ex){
            logger.error(session, AME.IO_008, "Info Message", infoCode.toString(), ex.getMessage());
            return null;
        }
    }
//
//    public void log(AppSession session, String className, String fnName, IC errorCode, Object ... args) {
//        String message = getMsg(session, errorCode, args);
//        appLogger.info(session, className, fnName, message);
//    }
//    public void log(AppSession session, String className, String fnName, IC errorCode) {
//        log(session, className, fnName, errorCode, null);
//    }

    public String getMM(){
        return "Ahmed";
    }
}
