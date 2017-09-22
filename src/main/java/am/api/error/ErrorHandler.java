package am.api.error;

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
import java.util.Properties;

@Singleton
public class ErrorHandler {
    @Inject AMConfigurationManager amConfigManager;
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
        FILE_NAME = amConfigManager.getConfigValue(AM_CC.ERROR_HANDLER);
        FILE.ERROR_MESSAGES = ConfigParam.APP_CONFIG_PATH + FILE_NAME;
        ERROR_MESSAGES = ConfigUtils.loadSystemComponent(FILE.ERROR_MESSAGES, COMPONENT.ERROR_HANDLER);

        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
    }

    public String getMsg(EC errorCode, Object ... arguments){
        String FN_NAME = "getMsg";
        try {
            AMLogger.startDebug(CLASS, FN_NAME, errorCode, arguments);

            if(ERROR_MESSAGES == null || ERROR_MESSAGES.isEmpty()) {
                load();
                throw new GeneralException(CLASS, FN_NAME, AME.IO_005, FILE_NAME);
            }else if(errorCode == null)
                throw new GeneralException(CLASS, FN_NAME, AME.SYS_007, "Error Message");

            String message = "";
            message = ConfigUtils.readValueFromPropertyFile(ERROR_MESSAGES, errorCode.toString(), FILE_NAME);
            message = ConfigUtils.formatMsg(message, arguments);

            AMLogger.info(CLASS, FN_NAME, AMI.IO_003, "Error Message", errorCode.toString());
            AMLogger.endDebug(CLASS, FN_NAME, message);
            return message;
        }catch (Exception ex){
            AMLogger.error(CLASS, FN_NAME, AME.IO_008, "Error Message", errorCode.toString(), ex.getMessage());
            return null;
        }
    }

}
