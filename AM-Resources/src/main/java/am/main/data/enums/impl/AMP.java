package am.main.data.enums.impl;

import am.main.data.enums.logger.LoggerLevels;
import am.main.spi.AMPhase;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class AMP extends AMPhase {
    private static final String AM = "AM";

    public static final AMP AM_INITIALIZATION = new AMP(AM, "AM-Initializing");
    public static final AMP SECURITY_MANAGER = new AMP(AM, "Security-Manager");
    public static final AMP MESSAGE_HANDLER = new AMP(AM, "Message-Handler");
    public static final AMP XML_HANDLER = new AMP(AM, "XML-Handler");
    public static final AMP JMS_MANAGER = new AMP(AM, "JMS-Manager");
    public static final AMP DB_MANAGER = new AMP(AM, "DB-Manager");
    public static final AMP QUERY_BUILDER = new AMP(AM, "Query-Builder");
    public static final AMP CONFIG_MANAGER = new AMP(AM, "Config-Manager");
    public static final AMP APP_LOGGER = new AMP(AM, "App-Logger");

//    public static final AMP INTEGRATION_TEST = new AMP(AM, "Integration-Test");
//
//    public static final AMP AM_NOTIFICATION = new AMP(AM, "AM-Notification");
//    public static final AMP AM_LOGGING = new AMP(AM, "AM-LoggerData");
//    public static final AMP AM_FILE_MANAGER = new AMP(AM, "AM-File-Manager");

    public AMP(String CATEGORY, String NAME) {
        super(CATEGORY, NAME, LoggerLevels.INFO);
    }

}
