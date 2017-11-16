package am.main.api.components;

import am.shared.enums.EC;
import am.main.common.ConfigParam;
import am.main.common.ConfigParam.COMPONENT;
import am.main.common.ConfigParam.FILE;
import am.main.common.ConfigUtils;
import am.main.core.config.AMConfigurationManager;
import am.main.core.config.AM_CC;
import am.main.common.enums.AME;
import am.main.common.enums.AMI;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.session.Interface;
import am.shared.session.Phase;
import am.main.session.Source;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;

@Singleton
public class ErrorHandler {
    @Inject AMConfigurationManager amConfigManager;
    @Inject private AppLogger logger;
    private static String FILE_NAME;
    private static final String CLASS = "ErrorHandler";
    private static Properties ERROR_MESSAGES = new Properties();
    public static ErrorHandler instance;

    private ErrorHandler() {

    }
    public static ErrorHandler getInstance() throws Exception{
        if (instance == null){
            synchronized(ErrorHandler.class){
                if (instance == null){
                    instance = new ErrorHandler();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        AppSession session = new AppSession(Source.AM, Interface.INITIALIZING, Phase.ERROR, CLASS, FN_NAME);

        FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.ERROR_HANDLER);
        FILE.ERROR_MESSAGES = ConfigParam.APP_CONFIG_PATH + FILE_NAME;
        ERROR_MESSAGES = ConfigUtils.loadSystemComponent(session, FILE.ERROR_MESSAGES, COMPONENT.ERROR_HANDLER);

        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
    }

    public String getMsg(AppSession appSession, EC errorCode, Object ... arguments){
        String FN_NAME = "getMsg";
        AppSession session = appSession.updateSession(Phase.ERROR, CLASS, FN_NAME);

        try {
            logger.startDebug(session, errorCode, arguments);

            if(ERROR_MESSAGES == null || ERROR_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(session, AME.IO_005, FILE_NAME);
            }else if(errorCode == null)
                throw new GeneralException(session, AME.SYS_007, "Error Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(session, ERROR_MESSAGES, errorCode.toString(), FILE_NAME);
            message = ConfigUtils.formatMsg(session, message, arguments);

            logger.info(session, AMI.IO_003, "Error Message", errorCode.toString());
            logger.endDebug(session, message);
            return message;
        }catch (Exception ex){
            logger.error(session, ex, AME.IO_008, "Error Message", errorCode.toString());
            return null;
        }
    }

}
