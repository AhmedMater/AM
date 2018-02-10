package am.queues;

import am.main.api.AppLogger;
import am.main.api.MessageHandler;
import am.main.data.dto.logger.AMBusLogData;
import am.main.data.enums.Interface;
import am.main.session.AppSession;
import am.services.BusinessService;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.common.LoggerParam.BUS_LOG_QUEUE;
import static am.common.LoggerParam.SOURCE;
import static am.data.enums.ALP.BUSINESS_LOG;
import static am.main.data.enums.impl.AME.E_JMS_5;


/**
 * Created by ahmed.motair on 1/20/2018.
 */
@MessageDriven(mappedName = BUS_LOG_QUEUE)
public class BusinessListener implements MessageListener {
    private final String CLASS = BusinessListener.class.getSimpleName();

    @Resource private MessageDrivenContext context;
    @Inject private MessageHandler messageHandler;
    @Inject private BusinessService businessService;
    @Inject private AppLogger logger;

    @Override
    public void onMessage(Message message) {
        AppSession session = new AppSession(SOURCE, Interface.JMS, BUSINESS_LOG, CLASS, "onMessage");
        try {
            String jmsID = message.getJMSMessageID();

            if (message instanceof ObjectMessage) {
                if (message.isBodyAssignableTo(AMBusLogData.class)){
                    AMBusLogData logData = message.getBody(AMBusLogData.class);
//                logData.get().setMessageHandler(messageHandler);
                }else
                    logger.error(session, E_JMS_5, BUS_LOG_QUEUE, AMBusLogData.class.getSimpleName(), message.getJMSType());
            }else
                logger.error(session, E_JMS_5, BUS_LOG_QUEUE, AMBusLogData.class.getSimpleName(), message.getJMSType());
//                throw new GeneralException(session, EC.AMT_0050, BUS_LOG_QUEUE);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}
