package am.main.common;

import static am.shared.common.SharedParam.LOGGER_FOLDER;

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
        public static String LOGGERS_CONFIG;

        public static String TIMER_CALCULATOR_CONFIG;
        public static String PUBLIC_HOLIDAYS;
    }

    public static class COMPONENT{
        public static final String ERROR_HANDLER = "Error Handler";
        public static final String INFO_HANDLER = "Info Handler";
        public static final String EMAIL_NOTIFICATION_MANAGER = "Email Notification Manager";
        public static final String APP_CONFIG_MANAGER = "Application Configuration Manager";
        public static final String AM_CONFIG_MANAGER = "AM Configuration Manger";
        public static final String LOGGERS_MANAGER = "AM Loggers Manger";
        public static final String TIMER_CALCULATOR = "Timer Calculator";
        public static final String PUBLIC_HOLIDAYS = "Public Holidays";
    }

    public static final String LOG4J2_FILE_NAME = "/log4j2.xml";
    public static final String AM_LOGGERS_FILE_NAME = "/AM-Loggers.xml";
    public static final String LOGGER_FILE_NAME = "../logs/" + LOGGER_FOLDER + "/{0}.log";
    public static final String LOGGER_FILE_PATTERN = "../logs/" + LOGGER_FOLDER + "/{0}/{0}-%i-%d'{yyyy-MM-dd HH-mm-ss.SSS'}.log";
    public static final String TEMPLATE = "Template";
}
