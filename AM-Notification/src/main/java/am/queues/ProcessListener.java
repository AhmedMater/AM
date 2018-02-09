package am.queues;

import am.main.api.AppLogger;
import am.main.data.enums.Interface;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.services.NotificationService;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static am.common.NotificationParam.PROCESS_NTF_QUEUE;
import static am.common.NotificationParam.SOURCE;
import static am.data.enums.am.impl.ANP.PROCESS_NTF;
import static am.main.data.enums.impl.IEC.E_JMS_5;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
@MessageDriven(mappedName = PROCESS_NTF_QUEUE)
public class ProcessListener implements MessageListener {
    private final String CLASS = ProcessListener.class.getSimpleName();

    @Inject private AppLogger logger;
    @Inject private NotificationService notificationService;
    @Resource private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        String METHOD = "onMessage";
        AppSession session = new AppSession(SOURCE, Interface.JMS, PROCESS_NTF);
        try {
            String jmsID = message.getJMSMessageID();
            session.setId(jmsID);

            if (message instanceof TextMessage) {
                String validEventID = ((TextMessage) message).getText();
                logger.startDebug(session, validEventID);

                boolean succeeded = notificationService.processNotification(session, validEventID);

                if(!succeeded)
                    context.setRollbackOnly();
                logger.endDebug(session);
            }else
                throw new GeneralException(session, E_JMS_5, PROCESS_NTF_QUEUE);
        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}
