package am.data.enums.am.impl;

import am.main.spi.JMSQueues;

/**
 * Created by ahmed.motair on 2/3/2018.
 */
public class ANQ extends JMSQueues {
    private static String PREFIX = "AM";

    public static final ANQ VALIDATE_NOTIFICATION = new ANQ("Val-Not", "Queue for validating new Events");
    public static final ANQ INVALID_NOTIFICATION = new ANQ("InVal-Not", "Queue for Processing invalid Events");
    public static final ANQ PROCESS_NOTIFICATION = new ANQ("Proc-Not", "Queue for Processing the new Events");

    public static final ANQ EMAIL_NOTIFICATION = new ANQ("EM-Not", "Queue for send the Email notifications");
    public static final ANQ SMS_NOTIFICATION = new ANQ("SMS-Not", "Queue for sending the SMS notifications");
    public static final ANQ WEB_NOTIFICATION = new ANQ("Web-Not", "Queue for sending the Web Notifications");

    public ANQ(String queueName, String description) {
        super(PREFIX, queueName, description);
    }
}
