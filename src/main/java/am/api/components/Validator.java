package am.api.components;


import am.common.ConfigParam;
import am.common.ConfigParam.COMPONENT;
import am.common.ConfigParam.FILE;
import am.common.ConfigUtils;
import am.core.config.AMConfigurationManager;
import am.core.config.AM_CC;
import am.session.AppSession;
import am.session.Interface;
import am.session.Phase;
import am.session.Source;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ahmed.motair on 9/14/2017.
 */
@Singleton
public class Validator {
    @Inject private AMConfigurationManager amConfigManager;
    private static Properties VALIDATOR_CONFIGURATION = new Properties();
    private static final String CLASS = "Validator";
    private static String FILE_NAME;

    private static Validator instance;
    private Validator() {

    }

    public static Validator getInstance() throws Exception{
        if (instance == null){
            synchronized(Validator.class){
                if (instance == null){
                    instance = new Validator();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        AppSession session = new AppSession(Source.AM, Interface.INITIALIZING, Phase.VALIDATION, CLASS, FN_NAME);

        FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.VALIDATION_HANDLER);
        FILE.VALIDATOR_CONFIG = ConfigParam.APP_CONFIG_PATH + FILE_NAME;
        VALIDATOR_CONFIGURATION = ConfigUtils.loadSystemComponent(session, FILE.VALIDATOR_CONFIG, COMPONENT.VALIDATION_HANDLER);

        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
    }

    public List<String> validateForm(AppSession appSession) throws Exception{
        String FN_NAME = "validateForm";
        AppSession session = appSession.updateSession(Phase.VALIDATION, CLASS, FN_NAME);
        return new ArrayList<>();
    }
}
