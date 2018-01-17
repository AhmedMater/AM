package am.queues;

import am.data.hibernate.model.input.InputEvent;
import am.main.api.AppLogger;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.services.NotificationService;
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

import static am.shared.common.JMSParams.NOTIFICATION_INPUT_Q;
import static am.shared.common.JMSParams.NOTIFICATION_VALIDATION_Q;
import static am.shared.common.JMSParams.QUEUE;

/**
 * Created by ahmed.motair on 1/16/2018.
 */
@MessageDriven(mappedName = QUEUE + NOTIFICATION_VALIDATION_Q)
public class ValidationListener implements MessageListener{

    private final String CLASS = ValidationListener.class.getSimpleName();

    @Inject private AppLogger logger;
    @Inject private NotificationService notificationService;
    @Resource private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        String METHOD = "onMessage";
        AppSession session = new AppSession(Source.AM_NOTIFICATION, Interface.JMS, Phase.AM_NOTIFICATION);
        try {
            String jmsID = message.getJMSMessageID();
            session.setId(jmsID);

            if (message instanceof ObjectMessage) {
                InputEvent notification = ((ObjectMessage) message).getBody(InputEvent.class);
                logger.startDebug(session, notification);

                boolean succeeded = notificationService.validateNotification(session, notification);

                if (!succeeded)
                    context.setRollbackOnly();
                logger.endDebug(session);
            } else
                throw new GeneralException(session, EC.AMT_0050, NOTIFICATION_INPUT_Q);

        } catch (Exception ex) {
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}