package am.core.config;

import am.common.ConfigParam.COMPONENT;
import am.common.ConfigParam.FILE;
import am.common.ConfigUtils;
import am.core.logger.AME;
import am.core.logger.AMI;
import am.core.logger.AMLogger;
import am.exception.GeneralException;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.Properties;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
@Singleton
public class AMConfigurationManager {
    private static final String CLASS = "AMConfigurationManager";
    private static Properties AM_CONFIG_FILE = new Properties();
    public static AMConfigurationManager instance;

    private AMConfigurationManager() {

    }

    public static AMConfigurationManager getInstance() throws Exception{
        if (instance == null){
            synchronized(AMConfigurationManager.class){
                if (instance == null){
                    instance = new AMConfigurationManager();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        try {
            AMLogger.startDebug(CLASS, FN_NAME);
            AM_CONFIG_FILE = ConfigUtils.readResourceFiles(FILE.AM_CONFIG_PROPERTIES, COMPONENT.AM_CONFIG_MANAGER);
            AMLogger.endDebug(CLASS, FN_NAME);
        }catch (Exception ex){
            AMLogger.error(CLASS, FN_NAME, ex);
        }
    }

    public String getConfigValue(AM_CC code){
        String FN_NAME = "getConfigValue";
        try {
            AMLogger.startDebug(CLASS, FN_NAME, code);

            if(code == null || code.toString() == null || code.toString().isEmpty()) {
                load();
                throw new GeneralException(CLASS, FN_NAME, AME.SYS_007, "AM Config Property");
            }else if(AM_CONFIG_FILE == null || AM_CONFIG_FILE.isEmpty())
                throw new GeneralException(CLASS, FN_NAME, AME.IO_005, FILE.AM_CONFIG_PROPERTIES);

            String value = AM_CONFIG_FILE.getProperty(code.value());

            AMLogger.info(CLASS, FN_NAME, AMI.IO_003, "AM Config Property", code);
            AMLogger.endDebug(CLASS, FN_NAME, value);
            return value;
        }catch (Exception ex){
            AMLogger.error(CLASS, FN_NAME, AME.IO_008, "AM Config Property", code, ex.getMessage());
            return "";
        }
    }
}
