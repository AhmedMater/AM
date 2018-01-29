package am.main.data.enums.impl;


import am.main.spi.JMSQueues;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public class AMQ extends JMSQueues {
    private static String PREFIX = "AM";

    public static final JMSQueues LOG4J2 = new AMQ("Log4j2", "Queue for Functional Logging using Log4j2");
    public static final JMSQueues BUSINESS = new AMQ("Business", "Queue for Business Data Logging");
    public static final JMSQueues PERFORMANCE = new AMQ("Log4j2", "Queue for Functional Logging for performance");

    public static final JMSQueues NOTIFICATION_INPUT = new AMQ("Notification_Input", "Queue for Receiving new events and save them in Database");
    public static final JMSQueues NOTIFICATION_VALID = new AMQ("Notification_Valid", "Queue for Validating the new Events");
    public static final JMSQueues NOTIFICATION_PROCESS = new AMQ("Notification_Process", "Queue for Processing the new Events");

    public static final JMSQueues EMAIL_NOTIFICATION = new AMQ("Email_Notification", "Queue for send the Email notifications");
    public static final JMSQueues SMS_NOTIFICATION = new AMQ("SMS_Notification", "Queue for sending the SMS notifications");
    public static final JMSQueues WEB_NOTIFICATION = new AMQ("Web_Notification", "Queue for sending the Web Notifications");

    private AMQ(String queueName, String description) {
        super(PREFIX, queueName, description);
    }
}
