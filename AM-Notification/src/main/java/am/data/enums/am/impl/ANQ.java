package am.data.enums.am.impl;

import am.main.spi.JMSQueues;

/**
 * Created by ahmed.motair on 2/3/2018.
 */
public class ANQ extends JMSQueues {
    private static String PREFIX = "AM";

    public static final ANQ VALIDATE_NOTIFICATION = new ANQ("ValNot", "Queue for validating new Events");
    public static final ANQ INVALID_NOTIFICATION = new ANQ("InValNot", "Queue for Processing invalid Events");
    public static final ANQ PROCESS_NOTIFICATION = new ANQ("ProcNot", "Queue for Processing the new Events");

    public static final ANQ EMAIL_NOTIFICATION = new ANQ("EMNot", "Queue for send the Email notifications");
    public static final ANQ SMS_NOTIFICATION = new ANQ("SMSNot", "Queue for sending the SMS notifications");
    public static final ANQ WEB_NOTIFICATION = new ANQ("WebNot", "Queue for sending the Web Notifications");

    public ANQ(String queueName, String description) {
        super(PREFIX, queueName, description);
    }
}
