package am.main.api;

import am.main.common.ConfigParam;
import am.main.common.ConfigUtils;
import am.main.data.jaxb.am.jms.AMJMSConfigManager;
import am.main.data.jaxb.am.jms.AMQueue;
import am.main.data.jaxb.am.jms.JMSConfig;
import am.main.data.jaxb.am.jms.JMSProperty;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.JMSQueues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.lang.IllegalStateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static am.main.data.enums.impl.AMP.JMS_MANAGER;
import static am.main.data.enums.impl.AMS.AM;
import static am.main.data.enums.impl.IEC.*;
import static am.main.data.enums.impl.IIC.*;

/**
 * Created by mohamed.elewa on 26/06/2017.
 */
public class JMSManager {
    @Inject private AppLogger logger;
    private static final String CLASS = JMSManager.class.getSimpleName();

    public Map<String, String> jmsProperties = new HashMap<>();

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
    private void load() {
        String METHOD = "load";
        AppSession session = appSession.updateSession(METHOD);
        String componentName = ConfigParam.COMPONENT.JMS_MANAGER;
        try {
            INITIAL_LOGGER.info(session + I_SYS_1.getFullMsg(componentName));

            AMJMSConfigManager amJMS = ConfigUtils.readRemoteXMLFile(session, logger, AMJMSConfigManager.class,
                    ConfigParam.JMS_MANGER_CONFIG.FN_PATH);
            INITIAL_LOGGER.info(session + I_JMS_1.getFullMsg());

            JMSConfig jmsConfig = amJMS.getJMSConfig();
            List<JMSProperty> properties = jmsConfig.getJMSProperty();
            for (JMSProperty property : properties)
                jmsProperties.put(property.getName(), property.getValue());

            List<AMQueue> queueList = amJMS.getJMSQueues().getAMQueue();
            InitialContext ctx = new InitialContext();

            for (AMQueue queue : queueList) {
                try {
                    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup(queue.getFactory());
                    QueueConnection queueConnection = connectionFactory.createQueueConnection();

                    queueConnection.start();
                    QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

                    Queue inQueue = (Queue) ctx.lookup(queue.getName());
                    MessageProducer sender = queueSession.createProducer(inQueue);
                    sender.setDeliveryMode(DeliveryMode.PERSISTENT);

                    senders.put(queue.getName(), sender);
                    sessions.put(queue.getName(), queueSession);

                    INITIAL_LOGGER.info(session + I_JMS_2.getFullMsg(queue.getName()));
//                    INITIAL_LOGGER.info(session + MessageFormat.format(AMI.JMS_000.value(), queue.getName()));
                } catch (Exception ex) {
                    throw new GeneralException(session, ex, E_JMS_1, queue);
                }
            }

            INITIAL_LOGGER.info(session + I_SYS_2.getFullMsg(componentName));
        } catch (Exception ex) {
//            this.error(session, ex, E_SYS_1, LOGGER_CONFIG.COMPONENT_NAME, ex.getMessage());
            FAILURE_LOGGER.error(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()), ex);
            throw new IllegalStateException(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()));
        }
    }

    public void sendMessage(JMSQueues queue, Serializable object) throws Exception {
        sendMessage(queue, object, 0L);
    }

    public void sendMessage(JMSQueues queue, Serializable object, Long deliveryDelay) throws Exception{
        String METHOD = "sendMessage";
        AppSession session = appSession.updateSession(METHOD);
        try {
            INITIAL_LOGGER.debug(session + "Started with " + queue.getQueueName() + ", " + object.toString() + ", " + deliveryDelay);

            INITIAL_LOGGER.info(session + I_JMS_3.getFullMsg(object.toString(), queue.description()));

            QueueSession queueSession = sessions.get(queue.getQueueName());
            if (queueSession == null)
                throw new GeneralException(session, E_JMS_2, queue.getQueueName());

            ObjectMessage message;
            message = queueSession.createObjectMessage(object);
            MessageProducer sender = senders.get(queue.getQueueName());

            if (sender == null)
                throw new GeneralException(session, E_JMS_3, queue.getQueueName());

            sender.setDeliveryDelay(deliveryDelay);
            sender.send(message);

            INITIAL_LOGGER.info(session + I_JMS_4.getFullMsg(object.toString(), queue.description()));

            INITIAL_LOGGER.debug(session + "Ended: [Void Function]");
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_JMS_4, object.toString(), queue.getQueueName());
        }
    }
}
