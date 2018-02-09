package am.common;

import am.data.enums.am.impl.ANS;
import am.main.spi.AMSource;

/**
 * Created by ahmed.motair on 2/3/2018.
 */
public class NotificationParam {
    public static final String INVALID_NTF_QUEUE = "AM_InVal_Not_Q";
    public static final String VALIDATE_NTF_QUEUE = "AM_Val_Not_Q";
    public static final String PROCESS_NTF_QUEUE = "AM_Proc_NOT_Q";
    public static final String EMAIL_NTF_QUEUE = "AM_EM_Not_Q";
    public static final String SMS_NTF_QUEUE = "AM_SMS_Not_Q";
    public static final String WEB_NTF_QUEUE = "AM_Web_Not_Q";

    public static final AMSource SOURCE = new ANS("AM-Notification");
}
