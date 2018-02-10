package am.queues;

import am.main.api.AppLogger;
import am.main.api.MessageHandler;
import am.main.data.dto.logger.AMFunLogData;
import am.main.data.enums.Interface;
import am.main.session.AppSession;
import am.services.PerformanceService;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.common.LoggerParam.FUN_LOG_QUEUE;
import static am.common.LoggerParam.SOURCE;
import static am.data.enums.ALP.FUNCTION_LOG;
import static am.main.data.enums.impl.AME.E_JMS_5;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
@MessageDriven(mappedName = FUN_LOG_QUEUE)
public class FunctionListener implements MessageListener{
    private final String CLASS = FunctionListener.class.getSimpleName();

    @Resource private MessageDrivenContext context;
    @Inject private MessageHandler messageHandler;
    @Inject private PerformanceService performanceService;
    @Inject private AppLogger logger;

    @Override
    public void onMessage(Message message) {
        AppSession session = new AppSession(SOURCE, Interface.JMS, FUNCTION_LOG, CLASS, "onMessage");
        try {
            String jmsID = message.getJMSMessageID();


            if (message instanceof ObjectMessage) {
                if (message.isBodyAssignableTo(AMFunLogData.class)){
                    AMFunLogData logData = message.getBody(AMFunLogData.class);
    //                logData.get().setMessageHandler(messageHandler);
                }else
                    logger.error(session, E_JMS_5, FUN_LOG_QUEUE, AMFunLogData.class.getSimpleName(), message.getJMSType());
            }else
            logger.error(session, E_JMS_5, FUN_LOG_QUEUE, AMFunLogData.class.getSimpleName(), message.getJMSType());
//                throw new GeneralException(session, EC.AMT_0050, BUS_LOG_QUEUE);


//            if (message instanceof ObjectMessage) {
//                AMFunLogData logData = ((ObjectMessage) message).getBody(AMFunLogData.class);
//
//                performanceService.processLogPerformance(session, logData);
//            }else
//                throw new GeneralException(session, EC.AMT_0050, FUN_LOG_QUEUE);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}
