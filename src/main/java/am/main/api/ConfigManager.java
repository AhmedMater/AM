package am.main.api;

import am.main.common.ConfigParam;
import am.main.common.ConfigUtils;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.data.enums.AM_CC;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.App_CC;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.Properties;

import static am.shared.enums.Phase.CONFIG_MANAGER;
import static am.shared.enums.Source.AM;

/**
 * Created by ahmed.motair on 1/8/2018.
 */
@Singleton
public class ConfigManager {
    @Inject private AppLogger logger;
    private static final String CLASS = ConfigManager.class.getSimpleName();

    private static Properties APP_CONFIGURATION = new Properties();
    private static Properties AM_CONFIGURATION = new Properties();
    private static String APP_CONFIG_FN, AM_CONFIG_FN;

    private static ConfigManager instance;
    private static final AppSession appSession = new AppSession(AM, CONFIG_MANAGER, CLASS);

    private ConfigManager() {

    }

    public static ConfigManager getInstance() throws Exception{
        if (instance == null){
            synchronized(ConfigManager.class){
                if (instance == null){
                    instance = new ConfigManager();
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
            AM_CONFIGURATION = ConfigUtils.readResourceFiles(session, logger,
                    ConfigParam.FILE.AM_CONFIG_PROPERTIES, ConfigParam.COMPONENT.CONFIG_MANAGER);

            APP_CONFIG_FN = getConfigValue(AM_CC.APP_CONFIG);
            ConfigParam.FILE.APP_CONFIG_PROPERTIES = ConfigParam.APP_CONFIG_PATH + APP_CONFIG_FN;
            APP_CONFIGURATION = ConfigUtils.loadPropertySystemComponent(session, logger, ConfigParam.FILE.APP_CONFIG_PROPERTIES, ConfigParam.COMPONENT.CONFIG_MANAGER);
        }catch (Exception ex){
            logger.FAILURE_LOGGER.error(MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.CONFIG_MANAGER), ex);
            throw new IllegalStateException(MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.CONFIG_MANAGER), ex);
        }
    }

    public <T> T getConfigValue(App_CC systemProperty, Class<T> className) throws Exception {
        String METHOD = "getConfigValue";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, systemProperty, className.getSimpleName());

            if(systemProperty == null || systemProperty.toString() == null || systemProperty.toString().isEmpty())
                throw new GeneralException(session, AME.SYS_007, "App Config Property");
            else if(APP_CONFIGURATION == null || APP_CONFIGURATION.isEmpty())
                throw new GeneralException(session, AME.IO_005, APP_CONFIG_FN);
            else if(className == null)
                throw new GeneralException(session, AME.SYS_008);

            T retValue;
            String value = ConfigUtils.readValueFromPropertyFile(session, logger, APP_CONFIGURATION, systemProperty.value(), APP_CONFIG_FN);

            if (className == String.class) {
                retValue = (T) value;
            }else if (className == Integer.class)
                retValue = (T) new Integer(Integer.parseInt(value));
            else if (className == Float.class)
                retValue = (T) new Float(Float.parseFloat(value));
            else if (className == Boolean.class)
                retValue = (T) new Boolean(Boolean.parseBoolean(value));
            else
                throw new GeneralException(session, AME.SYS_009, className.getSimpleName());

            logger.info(session, AMI.IO_003, "App Config Property", systemProperty);
            logger.endDebug(session, retValue);
            return retValue;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_008, "App Config Property", systemProperty);
        }
    }

    public boolean updateConfigValue(App_CC systemProperty, String value) throws Exception {
        String METHOD = "updateConfigValue";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, systemProperty, value);

            if(systemProperty == null || systemProperty.toString() == null || systemProperty.toString().isEmpty())
                throw new GeneralException(session, AME.SYS_007, "App Config Property");
            else if(APP_CONFIGURATION == null || APP_CONFIGURATION.isEmpty())
                throw new GeneralException(session, AME.IO_005, APP_CONFIG_FN);
            else if(value == null)
                throw new GeneralException(session, AME.SYS_016);

            Object retValue = APP_CONFIGURATION.replace(systemProperty.value(), value);

            boolean result = (retValue != null);

            logger.info(session, AMI.IO_004, "App Config Property", systemProperty);
            logger.endDebug(session, result);
            return result;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_011, "App Config Property", systemProperty);
        }
    }


    public String getConfigValue(AM_CC code){
        String METHOD = "getConfigValue";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, code);

            if(code == null || code.toString() == null || code.toString().isEmpty()) {
                load();
                throw new GeneralException(session, AME.SYS_007, "AM Config Property");
            }else if(AM_CONFIGURATION == null || AM_CONFIGURATION.isEmpty())
                throw new GeneralException(session, AME.IO_005, ConfigParam.FILE.AM_CONFIG_PROPERTIES);

            String value = AM_CONFIGURATION.getProperty(code.value());

            logger.info(session, AMI.IO_003, "AM Config Property", code.toString());
            logger.endDebug(session, value);
            return value;
        }catch (Exception ex){
            logger.error(session, ex, AME.IO_008, "AM Config Property", code);
            return "";
        }
    }

    public Properties getAM_CONFIGURATION() {
        return AM_CONFIGURATION;
    }
    public void setAM_CONFIGURATION(Properties AM_CONFIGURATION) {
        AM_CONFIGURATION = AM_CONFIGURATION;
    }
}
