package am.queues;

import am.main.api.AppLogger;
import am.main.api.MessageHandler;
import am.main.data.dto.logger.AMFunLogData;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.services.BusinessService;
import am.shared.enums.EC;
import am.main.data.enums.Interface;
import am.shared.enums.Phase;
import am.shared.enums.Source;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.shared.common.JMSParams.BUSINESS_Q;
import static am.shared.common.JMSParams.QUEUE;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
@MessageDriven(mappedName = QUEUE + BUSINESS_Q)
public class BusinessListener implements MessageListener {
    private final String CLASS = BusinessListener.class.getSimpleName();

    @Resource private MessageDrivenContext context;
    @Inject private MessageHandler messageHandler;
    @Inject private BusinessService businessService;
    @Inject private AppLogger logger;

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

            }else
                throw new GeneralException(session, EC.AMT_0050, BUSINESS_Q);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}
