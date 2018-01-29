package am.main.common;

import am.main.spi.AMSource;

/**
 * Created by ahmed.motair on 9/7/2017.
 */
public class ConfigParam {
    public static String APP_NAME;
    public static String APP_TYPE;
    public static String AM_RESOURCE_NAME = "AM-Resources";
    private static String APP_CONFIG_PATH;

    public static AMComponent ERROR_MSG_CONFIG, INFO_MSG_CONFIG, WARN_MSG_CONFIG, LOGGER_CONFIG;
    public static AMComponent JMS_MANGER_CONFIG, NOTIFICATION_CONFIG, MAIN_APP_CONFIG;

    public static final String ASC_ORDER = "ASC";
    public static final String DESC_ORDER = "DESC";

    public static void setConfiguration(String appConfigPath, AMSource app){
        APP_CONFIG_PATH = appConfigPath;
        APP_NAME = app.getName();

        ERROR_MSG_CONFIG = new AMComponent(appConfigPath, "AM-Error-Msg.properties", "Message Handler");
        INFO_MSG_CONFIG = new AMComponent(appConfigPath, "AM-Info-Msg.properties", "Message Handler");
        WARN_MSG_CONFIG = new AMComponent(appConfigPath, "AM-Warning-Msg.properties", "Message Handler");
        LOGGER_CONFIG = new AMComponent(appConfigPath, "AM-Logger-Config.xml", "App LoggerData");
        JMS_MANGER_CONFIG = new AMComponent(appConfigPath, "AM-JMS-Config.xml", "JMS Manager");
        NOTIFICATION_CONFIG = new AMComponent(appConfigPath, "AM-Notification-Config.properties", "AM Notification Manger");
        MAIN_APP_CONFIG = new AMComponent(appConfigPath, "Main-App-Config.properties", "Configuration Manager");
    }

    public static class COMPONENT{
        public static final String APP_LOGGER = "Application Logger";
        public static final String JMS_MANAGER = "JMS Manager";
        public static final String MESSAGE_HANDLER = "Message Handler";
        public static final String CONFIG_MANAGER = "Configuration Manager";
        public static final String NOTIFICATION_MANAGER = "AMNotification Manager";
        public static final String TIMER_CALCULATOR = "Timer Calculator";
        public static final String PUBLIC_HOLIDAYS = "Public Holidays";
    }

    public static class AMComponent {
        public final String FN;
        public final String FN_PATH;
        public final String COMPONENT_NAME;

        public AMComponent(String appConfigPath, String FN, String COMPONENT_NAME) {
            this.FN = FN;
            this.FN_PATH = appConfigPath + FN;
            this.COMPONENT_NAME = COMPONENT_NAME;
        }
    }
}
