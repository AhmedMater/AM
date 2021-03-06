package am.queues;

import am.data.enums.ALQ;
import am.main.api.AppLogger;
import am.main.api.JMSManager;
import am.main.api.MessageHandler;
import am.main.data.dto.logger.AMFunLogData;
import am.main.data.enums.Interface;
import am.main.session.AppSession;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import static am.common.LoggerParam.FILE_LOG_QUEUE;
import static am.common.LoggerParam.SOURCE;
import static am.data.enums.ALP.FILE_LOG;
import static am.main.data.enums.impl.AME.E_JMS_5;
import static am.main.data.enums.impl.AME.E_LOG_7;

/**
 * Created by ahmed.motair on 1/5/2018.
 */
@MessageDriven(mappedName = FILE_LOG_QUEUE)
public class FileListener implements MessageListener {
    private final String CLASS = FileListener.class.getSimpleName();

    @Inject private MessageHandler messageHandler;
    @Inject private JMSManager jmsManager;
    @Inject private AppLogger logger;
    @Resource private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        AppSession session = new AppSession(SOURCE, Interface.JMS, FILE_LOG, CLASS, "onMessage");
        session.setMessageHandler(messageHandler);
        try {
            String jmsID = message.getJMSMessageID();

            if (message instanceof ObjectMessage) {
                if (message.isBodyAssignableTo(AMFunLogData.class)){
                    AMFunLogData logData = message.getBody(AMFunLogData.class);
                    logger.log(session, logData);

                    if(logger.getUsePerformanceLogger()){
                        try {
                            jmsManager.sendObjMessage(session, ALQ.FUNCTION_LOG, logData);
                        } catch (Exception exc) {
                            logger.error(session, exc, E_LOG_7);
                        }
                    }
                }else
                    logger.error(session, E_JMS_5, FILE_LOG_QUEUE, AMFunLogData.class.getSimpleName(), message.getJMSType());
            }else
                logger.error(session, E_JMS_5, FILE_LOG_QUEUE, AMFunLogData.class.getSimpleName(), message.getJMSType());

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}