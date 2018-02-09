package am.data.enums.am.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/29/2018.
 */
public class ANI extends AMCode {
    private static final String RECEIVE = "NTF-REC";
    private static final String VALIDATE = "NTF-VAL";
    private static final String PROCESS = "NTF-PROC";
    private static final String MANAGER = "NTF-MGR";
    private static final String SEND = "SND-NTF";

    public static ANI I_REC_1 = new ANI(1, RECEIVE, "Start authenticating for the new Notification: ''{0}'' successfully");
    public static ANI I_REC_2 = new ANI(2, RECEIVE, "LoginData of App: ''{0}'' is authenticated successfully");
    public static ANI I_REC_3 = new ANI(3, RECEIVE, "Notification Num: ''{0}'' from App: ''{1}'' is received successfully");

    public static ANI I_VN_1 = new ANI(1, VALIDATE, "Start validating Notification Num: ''{0}'' from App: ''{1}''");
    public static ANI I_VN_2 = new ANI(2, VALIDATE, "Notification ''{0}'' from App: ''{1}'' is validated successfully");
    public static ANI I_VN_3 = new ANI(3, VALIDATE, "Notification ''{0}'' from App: ''{1}'' is saved in AM-Notification System successfully");
    public static ANI I_VN_4 = new ANI(4, VALIDATE, "Notification ''{0}'' is sent to ''{1}'' Queue successfully");
    public static ANI I_VN_5 = new ANI(5, VALIDATE, "Event Received successfully");
    public static ANI I_VN_6 = new ANI(6, VALIDATE, "");

    public static ANI I_PROC_1 = new ANI(1, PROCESS, "Start Processing Notification ''{0}''");
    public static ANI I_PROC_2 = new ANI(2, PROCESS, "Notification ''{0}'' is processed successfully");

    public static ANI I_IP_5 = new ANI(5, VALIDATE, "");

    public static ANI I_SN_1 = new ANI(1, SEND, "''{0}'' Notification of ID: ''{1}'' is sent successfully to ''{2}''");
    public static ANI I_SN_2 = new ANI(2, SEND, "''{0}'' Notification of ID: ''{1}'' is updated as not sent in Database successfully");

    public static ANI I_MGR_1 = new ANI(1, MANAGER, "Notification Manager Properties are loaded successfully in the system");
    public static ANI I_MGR_2 = new ANI(2, MANAGER, "Mail Session initiated Successfully");
    public static ANI I_MGR_3 = new ANI(3, MANAGER, "Email: ''{0}'' is sent successfully To: ''{1}''");
    public static ANI I_MGR_4 = new ANI(4, MANAGER, "Template: ''{0}'' is loaded successfully");

    private ANI(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.INFO, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AMN");
    }
}
