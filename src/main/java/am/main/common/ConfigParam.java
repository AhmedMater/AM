package am.main.common;

/**
 * Created by ahmed.motair on 9/7/2017.
 */
public class ConfigParam {
    public static String APP_CONFIG_PATH;

    public static class FILE{
        public static final String AM_CONFIG_PROPERTIES = "AM-Config.properties";
        public static String ERROR_MESSAGES;
        public static String INFO_MESSAGES;
        public static String EMAIL_NOTIFICATION;
        public static String APP_CONFIG_PROPERTIES;
        public static String SYSTEM_LOGGER;
        public static String VALIDATOR_CONFIG;
        public static String LOGGER;
    }

    public static class COMPONENT{
        public static final String ERROR_HANDLER = "Error Handler";
        public static final String INFO_HANDLER = "Info Handler";
        public static final String EMAIL_NOTIFICATION_MANAGER = "Email Notification Manager";
        public static final String APP_CONFIG_MANAGER = "Application Configuration Manager";
        public static final String VALIDATION_HANDLER = "Validator Handler";
        public static final String LOGGER = "Application Logger";
        public static final String SYSTEM_LOGGER = "System Logger";
        public static final String AM_CONFIG_MANAGER = "AM Configuration Manger";
    }
}
