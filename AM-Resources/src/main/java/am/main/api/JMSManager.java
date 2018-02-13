package am.main.api;

import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.JMSQueues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.jms.*;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static am.main.data.enums.impl.AME.E_JMS_4;
import static am.main.data.enums.impl.AMI.I_JMS_3;
import static am.main.data.enums.impl.AMI.I_JMS_4;
import static am.main.data.enums.impl.AMP.JMS_MANAGER;

/**
 * Created by mohamed.elewa on 26/06/2017.
 */
public class JMSManager {
    @Inject private AppLogger logger;
    private static final String CLASS = JMSManager.class.getSimpleName();

    public Map<String, String> jmsProperties = new HashMap<>();

//    private static Map<String, MessageProducer> senders = new HashMap<>();
//    private static Map<String, QueueSession> sessions = new HashMap<>();

    public static JMSManager instance;
    private Logger INITIAL_LOGGER = LogManager.getLogger("Initial");
    private Logger FAILURE_LOGGER = LogManager.getLogger("Failure");
//    private static final AppSession appSession = new AppSession(AM, JMS_MANAGER, CLASS);

    public JMSManager() {
//        load();
    }

    public static JMSManager getInstance(){
        if (instance == null){
            synchronized(JMSManager.class){
                if (instance == null){
                    instance = new JMSManager();
//                    instance.load();
                }
            }
        }
        return instance;
    }
//
//    @PostConstruct
//    private void load() {
//        String METHOD = "load";
//        AppSession session = appSession.updateSession(METHOD);
//        String componentName = ConfigParam.COMPONENT.JMS_MANAGER;
//        try {
//            INITIAL_LOGGER.info(session + I_SYS_1.getFullMsg(componentName));
//
//            AMJMSConfigManager amJMS = ConfigUtils.readRemoteXMLFile(session, logger, AMJMSConfigManager.class,
//                    ConfigParam.JMS_MANGER_CONFIG.FN_PATH);
//            INITIAL_LOGGER.info(session + I_JMS_1.getFullMsg());
//
//            JMSConfig jmsConfig = amJMS.getJMSConfig();
//            List<JMSProperty> properties = jmsConfig.getJMSProperty();
//            for (JMSProperty property : properties)
//                jmsProperties.put(property.getName(), property.getValue());
//
//            List<AMQueue> queueList = amJMS.getJMSQueues().getAMQueue();
//            InitialContext ctx = new InitialContext();
//
//            for (AMQueue queue : queueList) {
//                try {
//                    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup(queue.getFactory());
//                    QueueConnection queueConnection = connectionFactory.createQueueConnection();
//
//                    queueConnection.start();
//                    QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
//
//                    Queue inQueue = (Queue) ctx.lookup(queue.getName());
//                    MessageProducer sender = queueSession.createProducer(inQueue);
//                    sender.setDeliveryMode(DeliveryMode.PERSISTENT);
//
//                    senders.put(queue.getName(), sender);
//                    sessions.put(queue.getName(), queueSession);
//
//                    INITIAL_LOGGER.info(session + I_JMS_2.getFullMsg(queue.getName()));
////                    INITIAL_LOGGER.info(session + MessageFormat.format(AMI.JMS_000.value(), queue.getName()));
//                } catch (Exception ex) {
//                    throw new GeneralException(session, ex, E_JMS_1, queue);
//                }
//            }
//
//            INITIAL_LOGGER.info(session + I_SYS_2.getFullMsg(componentName));
//        } catch (Exception ex) {
////            this.error(session, ex, E_SYS_1, LOGGER_CONFIG.COMPONENT_NAME, ex.getMessage());
//            FAILURE_LOGGER.error(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()), ex);
//            throw new IllegalStateException(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()));
//        }
//    }

    public void sendObjMessage(AppSession appSession, JMSQueues queue, Serializable object) throws Exception {
        sendMessage(appSession, queue, object, 0L, false);
    }
    public void sendObjMessage(AppSession appSession, JMSQueues queue, Serializable object, Long deliveryDelay) throws Exception {
        sendMessage(appSession, queue, object, deliveryDelay, false);
    }

    public void sendTxtMessage(AppSession appSession, JMSQueues queue, String object) throws Exception {
        sendMessage(appSession, queue, object, 0L, true);
    }
    public void sendTxtMessage(AppSession appSession, JMSQueues queue, String object, Long deliveryDelay) throws Exception {
        sendMessage(appSession, queue, object, deliveryDelay, true);
    }

    private void sendMessage(AppSession appSession, JMSQueues queue, Serializable object, Long deliveryDelay, boolean isText) throws Exception{
        String METHOD = "sendMessage";
        AppSession session = appSession.updateSession(JMS_MANAGER, CLASS, METHOD);

        QueueConnection queueConn = null;
        QueueSession queueSession = null;
        MessageProducer sender = null;

        try {
            logger.startDebug(session, queue.getQueueName(), object.toString(), deliveryDelay, isText);

            logger.info(session, I_JMS_3, object.toString(), queue.description());

            queueConn = ((QueueConnectionFactory) new InitialContext().
                    lookup(queue.getConnectionFactory())).createQueueConnection();

            queueConn.start();
            queueSession = queueConn.createQueueSession(true, Session.SESSION_TRANSACTED);

            Queue jmsQueue = (Queue) new InitialContext().lookup(queue.getQueueName());

            sender = queueSession.createProducer(jmsQueue);
            sender.setDeliveryDelay(deliveryDelay);

            if (isText) {
                TextMessage txtMsg = queueSession.createTextMessage((String) object);
                sender.send(txtMsg);
            } else{
                ObjectMessage objMsg = queueSession.createObjectMessage(object);
                sender.send(objMsg);
            }

            queueConn.close();
            queueSession.close();
            sender.close();

            logger.info(session, I_JMS_4, object.toString(), queue.description());
            logger.endDebug(session);
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_JMS_4, object.toString(), queue.getQueueName());
        }
    }
}
