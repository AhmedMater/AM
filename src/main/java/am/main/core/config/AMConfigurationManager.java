package am.main.core.config;

import am.main.api.components.AppLogger;
import am.main.common.ConfigParam.COMPONENT;
import am.main.common.ConfigParam.FILE;
import am.main.common.ConfigUtils;
import am.main.common.enums.AME;
import am.main.common.enums.AMI;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.session.Interface;
import am.shared.session.Phase;
import am.main.session.Source;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.Properties;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
@Singleton
public class AMConfigurationManager {
    private static final String CLASS = "AMConfigurationManager";
    private Properties AM_CONFIG_FILE = new Properties();
    public static AMConfigurationManager instance;
    private AppLogger logger = new AppLogger();

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
        AppSession session = new AppSession(Source.AM, Interface.INITIALIZING, Phase.AM_CONFIG, CLASS, FN_NAME);
        try {
            logger.startDebug(session);
            AM_CONFIG_FILE = ConfigUtils.readResourceFiles(session, FILE.AM_CONFIG_PROPERTIES, COMPONENT.AM_CONFIG_MANAGER);
            logger.endDebug(session);
        }catch (Exception ex){
            AppLogger.failureLogger.error(session, ex);
        }
    }

    public String getConfigValue(AppSession appSession, AM_CC code){
        String FN_NAME = "getConfigValue";
        AppSession session = appSession.updateSession(Phase.AM_CONFIG, CLASS, FN_NAME);
        try {
            logger.startDebug(session, code);

            if(code == null || code.toString() == null || code.toString().isEmpty()) {
                load();
                throw new GeneralException(session, AME.SYS_007, "AM Config Property");
            }else if(AM_CONFIG_FILE == null || AM_CONFIG_FILE.isEmpty())
                throw new GeneralException(session, AME.IO_005, FILE.AM_CONFIG_PROPERTIES);

            String value = AM_CONFIG_FILE.getProperty(code.value());

            logger.info(session, AMI.IO_003, "AM Config Property", code);
            logger.endDebug(session, value);
            return value;
        }catch (Exception ex){
            logger.error(session, ex, AME.IO_008, "AM Config Property", code);
            return "";
        }
    }

    public Properties getAmConfigFile() {
        return AM_CONFIG_FILE;
    }
    public void setAmConfigFile(Properties amConfigFile) {
        AM_CONFIG_FILE = amConfigFile;
    }
}
