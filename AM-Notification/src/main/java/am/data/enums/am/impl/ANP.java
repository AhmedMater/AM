package am.data.enums.am.impl;

import am.main.data.enums.logger.LoggerLevels;
import am.main.spi.AMPhase;

/**
 * Created by ahmed.motair on 1/29/2018.
 */
public class ANP extends AMPhase {
    private static final String AM_NTF = "AMN";

    public static final ANP RECEIVE_NTF = new ANP(AM_NTF, "Receive-Notification");
    public static final ANP VALIDATE_NTF = new ANP(AM_NTF, "Validate-Notification");
    public static final ANP PROCESS_NTF = new ANP(AM_NTF, "Process-Notification");

    public static final ANP EMAIL_NTF = new ANP(AM_NTF, "Email-Notification");
    public static final ANP SMS_NTF = new ANP(AM_NTF, "SMS-Notification");
    public static final ANP WEB_NTF = new ANP(AM_NTF, "Web-Notification");

    public static final ANP NTF_MANAGER = new ANP(AM_NTF, "Notification-Manager");
    public ANP(String CATEGORY, String NAME) {
        super(CATEGORY, NAME, LoggerLevels.ST_DEBUG);
    }
}
