package am.data.enums;

import am.main.spi.JMSQueues;

/**
 * Created by ahmed.motair on 2/3/2018.
 */
public class ALQ extends JMSQueues{
    private static String PREFIX = "AM";

    public static final ALQ FUNCTION_LOG = new ALQ("Fun-Log", "Queue for Functional Logging for performance");

    public ALQ(String queueName, String description) {
        super(PREFIX, queueName, description);
    }
}
