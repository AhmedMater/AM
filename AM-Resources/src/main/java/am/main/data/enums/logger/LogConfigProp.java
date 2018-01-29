package am.main.data.enums.logger;

/**
 * Created by ahmed.motair on 1/25/2018.
 */
public enum LogConfigProp {
    MAIN_LOG_PATH("MainLogPath", "^.*$", "../logs/AM-Logs"),
    MAX_FILE_SIZE("MaxFileSize", "^[0-9]+ (KB|MB)$", "25 MB"),
    USE_AM_LOGGER("UseAMLogger", "^(true|false)$", "false"),
    LOG_PERFORMANCE("UsePerformanceLogger", "^(true|false)$", "false"),
    LOG_LEVEL_FOR_ALL("LogLevelForAll", "^(none|debug|info|error)$", "none");

    private final String name;
    private final String regex;
    private final String defaultValue;

    LogConfigProp(String name, String regex, String defaultValue){
        this.regex = regex;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static LogConfigProp getLogConfigProp(String name) throws IllegalArgumentException{
        LogConfigProp[] props = values();
        for (LogConfigProp prop : props)
            if (prop.getName().equals(name))
                return prop;
        throw new IllegalArgumentException("No Logger Configuration Property with this name: " + name);
    }
}
