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

    private AMP(String CATEGORY, String NAME) {
        super(CATEGORY, NAME, LoggerLevels.INFO);
    }
}
