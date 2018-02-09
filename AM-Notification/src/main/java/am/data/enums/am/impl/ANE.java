package am.data.enums.am.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/29/2018.
 */
public class ANE extends AMCode {
    private static final String RECEIVE = "NTF-REC";
    private static final String VALIDATE = "NTF-VAL";
    private static final String PROCESS = "NTF-PROC";
    private static final String SEND = "NTF-SND";
    private static final String MANAGER = "NTF-MGR";

    public static ANE E_REC_1 = new ANE(1, RECEIVE, "Username: ''{0}'' isn''t found in the System");
    public static ANE E_REC_2 = new ANE(2, RECEIVE, "Wrong Password for Username: ''{0}''");
    public static ANE E_REC_3 = new ANE(3, RECEIVE, "Password isn''t Base64 encoded");
    public static ANE E_REC_4 = new ANE(4, RECEIVE, "App: ''{0}'' has reached the max Quota for the current day");

//    public static ANE E_VN_4 = new ANE(1, VALIDATE, "Notification Type: ''{0}'' isn't supported yet by the system");
    public static ANE E_VN_1 = new ANE(1, VALIDATE, "EventID: ''{0}'' isn't found in the System");
    public static ANE E_VN_2 = new ANE(2, VALIDATE, "CategoryID: ''{0}'' for App: ''{1}'' isn't found in the System");
    public static ANE E_VN_3 = new ANE(3, VALIDATE, "EventNotificationID: ''{0}'' for EventID: ''{1}'' isn't found in the System");
    public static ANE E_VN_4 = new ANE(4, VALIDATE, "EventParameter: ''{0}'' for EventID: ''{1}'' isn't found in the System");
    public static ANE E_VN_5 = new ANE(5, VALIDATE, "EventParameter can't be empty String");
    public static ANE E_VN_6 = new ANE(6, VALIDATE, "EventParameter: {0}''s Value can''t be null");
    public static ANE E_VN_7 = new ANE(7, VALIDATE, "EventParameter: {0}''s Value can''t be Empty String");
    public static ANE E_VN_8 = new ANE(8, VALIDATE, "Failed while validating Notification ''{0}'' from ''{1}'', and Message will be quarantined");

    public static ANE E_PN_1 = new ANE(1, PROCESS, "ValidEventID: ''{0}'' isn't found in the System");
    public static ANE E_PN_2 = new ANE(2, PROCESS, "''{0}'' invalid Notification Type");
    public static ANE E_PN_3 = new ANE(3, PROCESS, "Not Enough Parameters for the Template");

    public static ANE E_SN_1 = new ANE(1, SEND, "Failed to send ''{0}'' Notification to ''{1}''");

    public static ANE E_MGR_1 = new ANE(1, MANAGER, "Parameter: ''{0}'' is missing from Notification Manager Properties");
    public static ANE E_MGR_2 = new ANE(2, MANAGER, "Invalid ''{0}'' E-Mail Address");
    public static ANE E_MGR_3 = new ANE(3, MANAGER, "Failed while sending Email: ''{0}'' To: ''{1}''");
    public static ANE E_MGR_4 = new ANE(4, MANAGER, "Folder Name is null");
    public static ANE E_MGR_5 = new ANE(5, MANAGER, "File Name is null");
    public static ANE E_MGR_6 = new ANE(6, MANAGER, "Failed while reading template: ''{0}''");

    private ANE(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.ERROR, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM-Notification");
    }
}
