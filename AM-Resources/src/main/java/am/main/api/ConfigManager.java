package am.main.api;

import am.main.common.ConfigParam;
import am.main.common.ConfigUtils;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.AMCode;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;

import static am.main.common.ConfigParam.MAIN_APP_CONFIG;
import static am.main.data.enums.Interface.INITIALIZING_COMPONENT;
import static am.main.data.enums.impl.AMP.AM_INITIALIZATION;
import static am.main.data.enums.impl.AMP.CONFIG_MANAGER;
import static am.main.data.enums.impl.AMS.AM;
import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;

/**
 * Created by ahmed.motair on 1/8/2018.
 */
@Singleton
public class ConfigManager {
    @Inject private AppLogger logger;
    private static final String CLASS = ConfigManager.class.getSimpleName();

    private static Properties APP_CONFIGURATION = new Properties();

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
        AppSession session = new AppSession(AM, INITIALIZING_COMPONENT, AM_INITIALIZATION, CLASS, METHOD);
        String componentName = ConfigParam.COMPONENT.CONFIG_MANAGER;
        try {
            logger.info(session, I_SYS_1, componentName);

            APP_CONFIGURATION = ConfigUtils.readRemotePropertyFiles(session, logger, MAIN_APP_CONFIG.FN_PATH);
            logger.info(session, I_CFG_1);

            logger.info(session, I_SYS_2, componentName);
        } catch (Exception ex) {
            logger.error(session, ex, E_SYS_1, componentName, ex.getMessage());
            throw new IllegalStateException(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()));
        }
    }

    public <T> T getConfigValue(AppSession appSession, AMCode amCode, Class<T> className) throws Exception {
        String METHOD = "getConfigValue";
        AppSession session = appSession.updateSession(CONFIG_MANAGER, CLASS, METHOD);
        try {
            logger.startDebug(session, amCode, className.getSimpleName());

            if(amCode == null)
                throw new GeneralException(session, E_SYS_4);

            logger.info(session, I_CFG_2, amCode.getFullCode());

            String value = ConfigUtils.readValueFromPropertyFile(session, logger, APP_CONFIGURATION,
                    amCode.getFullCode(), "Application Configuration");

            if(className == null)
                throw new GeneralException(session, E_CFG_1);

            T retValue;

            if (className == String.class) {
                retValue = (T) value;
            }else if (className == Integer.class)
                retValue = (T) new Integer(Integer.parseInt(value));
            else if (className == Float.class)
                retValue = (T) new Float(Float.parseFloat(value));
            else if (className == Boolean.class)
                retValue = (T) new Boolean(Boolean.parseBoolean(value));
            else
                throw new GeneralException(session, E_CFG_2, className.getSimpleName());

            logger.info(session, I_CFG_3, amCode.getFullCode());
            logger.endDebug(session, retValue);
            return retValue;
        }catch (GeneralException ex){
            throw ex;
        }catch (Exception ex){
            throw new GeneralException(session, ex, E_CFG_3, amCode.getFullCode());
        }
    }

    public boolean updateConfigValue(AppSession appSession, AMCode amCode, String newValue) throws Exception {
        String METHOD = "updateConfigValue";
        AppSession session = appSession.updateSession(CONFIG_MANAGER, CLASS, METHOD);
        try {
            logger.startDebug(session, amCode, newValue);

            if(amCode == null)
                throw new GeneralException(session, E_SYS_4);

            logger.info(session, I_CFG_4, amCode.getFullCode());

            String value = ConfigUtils.readValueFromPropertyFile(session, logger, APP_CONFIGURATION,
                    amCode.getFullCode(), "Application Configuration");

            Object retValue = APP_CONFIGURATION.replace(amCode.getFullCode(), value);

            boolean result = (retValue != null);

            logger.info(session, I_CFG_5, amCode.getFullCode());
            logger.endDebug(session, result);
            return result;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_CFG_4, amCode.getFullCode());
        }
    }
}
