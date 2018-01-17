package am.main.api;

import am.main.common.ConfigParam;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.JMSQueues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.lang.IllegalStateException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import static am.shared.enums.Phase.JMS_MANAGER;
import static am.shared.enums.Source.AM;

/**
 * Created by mohamed.elewa on 26/06/2017.
 */
public class JMSManager {
    private static final String CLASS = JMSManager.class.getSimpleName();

    private static Map<String, MessageProducer> senders = new HashMap<>();
    private static Map<String, QueueSession> sessions = new HashMap<>();

    public static JMSManager instance;
    private Logger INITIAL_LOGGER = LogManager.getLogger("Initial");
    private Logger FAILURE_LOGGER = LogManager.getLogger("Failure");
    private static final AppSession appSession = new AppSession(AM, JMS_MANAGER, CLASS);

    public JMSManager() {
        load();
    }

    public static JMSManager getInstance(){
        if (instance == null){
            synchronized(JMSManager.class){
                if (instance == null){
                    instance = new JMSManager();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String METHOD = "load";
        AppSession session = appSession.updateSession(METHOD);
        try {
            INITIAL_LOGGER.info(session + AMI.JMS_002.value());

            JMSQueues[] queues = JMSQueues.values();
            InitialContext ctx = new InitialContext();

            for (JMSQueues queue : queues) {
                try {
                    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup(queue.getConnectionFactory());
                    QueueConnection queueConnection = connectionFactory.createQueueConnection();

                    queueConnection.start();
                    QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

                    Queue inQueue = (Queue) ctx.lookup(queue.getQueueName());
                    MessageProducer sender = queueSession.createProducer(inQueue);
                    sender.setDeliveryMode(DeliveryMode.PERSISTENT);

                    senders.put(queue.getQueueName(), sender);
                    sessions.put(queue.getQueueName(), queueSession);

                    INITIAL_LOGGER.info(session + MessageFormat.format(AMI.JMS_000.value(), queue.getQueueName()));
                }catch (Exception ex){
                    throw new GeneralException(session, ex, AME.JMS_000, queue);
                }
            }

            INITIAL_LOGGER.info(session + AMI.JMS_003.value());
        }catch (Exception ex){
            FAILURE_LOGGER.error(session + MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.JMS_MANAGER), ex);
            throw new IllegalStateException(MessageFormat.format(AME.SYS_006.value(), ConfigParam.COMPONENT.JMS_MANAGER));
        }
    }

    public void sendMessage(JMSQueues queue, Serializable object) throws Exception {
        sendMessage(queue, object, 0L);
    }

    public void sendMessage(JMSQueues queue, Serializable object, Long deliveryDelay) throws Exception{
        String METHOD = "sendMessage";
        AppSession session = appSession.updateSession(METHOD);
        INITIAL_LOGGER.debug(session + "Started with " + queue.getQueueName() + ", " + object.toString() + ", " + deliveryDelay);

        QueueSession queueSession = sessions.get(queue.getQueueName());
        if(queueSession == null)
            throw new GeneralException(session, AME.JMS_001, queue.getQueueName());

        ObjectMessage message;
        message = queueSession.createObjectMessage(object);
        MessageProducer sender = senders.get(queue.getQueueName());

        if(sender == null)
            throw new GeneralException(session, AME.JMS_002, queue.getQueueName());

        sender.setDeliveryDelay(deliveryDelay);
        sender.send(message);

        INITIAL_LOGGER.info(session + MessageFormat.format(AMI.JMS_001.value(), message.toString(), queue.description()));

        INITIAL_LOGGER.debug(session + "Ended: [Void Function]");
    }
}
