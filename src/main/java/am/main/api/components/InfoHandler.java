package am.main.api.components;

import am.shared.enums.IC;
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
        AppSession session = new AppSession(Source.AM, Interface.INITIALIZING, Phase.INFO, CLASS, FN_NAME);

        FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.INFO_HANDLER);
        FILE.INFO_MESSAGES = ConfigParam.APP_CONFIG_PATH + FILE_NAME;
        INFO_MESSAGES = ConfigUtils.loadSystemComponent(session, FILE.INFO_MESSAGES, COMPONENT.INFO_HANDLER);

        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
    }

    public String getMsg(AppSession appSession, IC infoCode, Object ... arguments){
        String FN_NAME = "getMsg";
        AppSession session = appSession.updateSession(Phase.INFO, CLASS, FN_NAME);
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
            logger.error(session, ex, AME.IO_008, "Info Message", infoCode.toString());
            return null;
        }
    }
}
