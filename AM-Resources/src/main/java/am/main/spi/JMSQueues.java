package am.main.spi;

import java.text.MessageFormat;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public abstract class JMSQueues {
    private static final String Q_FORMAT = "{0}_{1}_Q";
    private static final String QCF_FORMAT = "{0}_{1}_QCF";
    private String PREFIX;
    private final String queueName;
    private final String description;

    public JMSQueues(String prefix, String queueName, String description){
        this.PREFIX = prefix;
        this.queueName = queueName;
        this.description = description;
    }

    public String getQueueName() {
        return MessageFormat.format(Q_FORMAT, PREFIX, queueName);
    }
    public String getConnectionFactory() {
        return MessageFormat.format(QCF_FORMAT, PREFIX, queueName);
    }
    public String description() {
        return this.description;
    }

}
