package am.queues;

import am.main.api.AppLogger;
import am.main.api.JMSManager;
import am.main.api.MessageHandler;
import am.main.data.dto.logger.AMFunLogData;
import am.main.data.enums.Interface;
import am.main.exception.GeneralException;
import am.main.session.AppSession;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.shared.common.JMSParams.LOG4J2_Q;
import static am.shared.common.JMSParams.QUEUE;

/**
 * Created by ahmed.motair on 1/5/2018.
 */
@MessageDriven(mappedName = QUEUE + LOG4J2_Q)
public class Log4j2Listener implements MessageListener {
    private final String CLASS = Log4j2Listener.class.getSimpleName();

    @Inject private MessageHandler messageHandler;
    @Inject private JMSManager jmsManager;
    @Inject private AppLogger logger;
    @Resource private MessageDrivenContext context;

    @Override
    public void onMessage(Message m) {
        AppSession session = new AppSession(Source.AM_LOGGER, Interface.JMS, Phase.AM_LOGGING,
                CLASS, "onMessage");
        try {
            String jmsID = m.getJMSMessageID();

            if (m instanceof ObjectMessage) {
                ObjectMessage message = (ObjectMessage) m;
                AMFunLogData logData = message.getBody(AMFunLogData.class);

                logData.getSession().setMessageHandler(messageHandler);
                logger.log(session, logData);

                jmsManager.sendMessage(JMSQueues.PERFORMANCE, logData);
            }else
                throw new GeneralException(session, EC.AMT_0050, LOG4J2_Q);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}