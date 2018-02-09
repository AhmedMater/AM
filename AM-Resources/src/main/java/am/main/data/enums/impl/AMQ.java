package am.main.data.enums.impl;


import am.main.spi.JMSQueues;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public class AMQ extends JMSQueues {
    private static String PREFIX = "AM";

    public static final AMQ FILE_LOG = new AMQ("File-Log", "Queue for File Logging using Log4j2");
    public static final AMQ BUSINESS_LOG = new AMQ("Bus-Log", "Queue for Business Data Logging");

    public static final JMSQueues INPUT_NOTIFICATION = new AMQ("In-Not", "Queue for Receiving new events and save them in Database");

    private AMQ(String queueName, String description) {
        super(PREFIX, queueName, description);
    }
}
