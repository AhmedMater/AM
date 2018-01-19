package am.services;

import am.main.api.AppLogger;
import am.main.api.MessageHandler;
import am.main.api.db.DBManager;
import am.main.data.dto.AMLogData;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.Interface;
import am.shared.enums.Phase;
import am.shared.enums.Source;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.shared.common.JMSParams.LOG4J2_Q;
import static am.shared.common.JMSParams.NOTIFICATION_INPUT_Q;
import static am.shared.common.JMSParams.QUEUE;

/**
 * Created by ahmed.motair on 1/5/2018.
 */
@MessageDriven(mappedName = QUEUE + LOG4J2_Q)
public class Log4j2Listener implements MessageListener {
    @Resource private MessageDrivenContext context;
    @Inject private DBManager dbManager;

    private final String CLASS = Log4j2Listener.class.getSimpleName();
    AppSession session = new AppSession(Source.AM_LOGGER, Interface.JMS, Phase.AM_LOGGING, CLASS, "onMessage");

    @Inject private MessageHandler messageHandler;
    @Inject private AppLogger logger;

    @Override
    public void onMessage(Message m) {
        String METHOD = "onMessage";

        try {
            String jmsID = m.getJMSMessageID();

            if (m instanceof ObjectMessage) {
                ObjectMessage message = (ObjectMessage) m;
                AMLogData logData = message.getBody(AMLogData.class);

                logData.getSession().setMessageHandler(messageHandler);
                logger.log(session, logData);

//                logger.endDebug(session);
            }else
                throw new GeneralException(session, EC.AMT_0050, NOTIFICATION_INPUT_Q);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}