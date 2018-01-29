package am.main.spi;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public abstract class JMSQueues {
    protected String PREFIX;
    private final String queueName;
    private final String description;

    public JMSQueues(String prefix, String queueName, String description){
        this.queueName = queueName;
        this.description = description;
    }

    public String getQueueName() {
        return PREFIX + "_Q_" + queueName;
    }
    public String getConnectionFactory() {
        return PREFIX + "_QCF_" + queueName;
    }
    public String description() {
        return this.description;
    }

}
