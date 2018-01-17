package am.queues.senders;

import am.data.hibernate.model.Notification;
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

import static am.shared.common.JMSParams.*;

/**
 * Created by ahmed.motair on 1/17/2018.
 */
@MessageDriven(mappedName = QUEUE + EMAIL_NOTIFICATION_Q)
public class EmailListener implements MessageListener{
    private final String CLASS = EmailListener.class.getSimpleName();

    @Resource private MessageDrivenContext context;
    @Inject private AppLogger logger;
    @Inject private NotificationService notificationService;

    @Override
    public void onMessage(Message message) {
        String METHOD = "onMessage";
        AppSession session = new AppSession(Source.AM_NOTIFICATION, Interface.JMS, Phase.AM_NOTIFICATION);
        try {
            String jmsID = message.getJMSMessageID();
            session.setId(jmsID);

            if (message instanceof ObjectMessage) {
                Notification notification = ((ObjectMessage) message).getBody(Notification.class);
                logger.startDebug(session, notification);

                notificationService.sendEmailNotification(session, notification);

                logger.endDebug(session);
            }else
                throw new GeneralException(session, EC.AMT_0050, NOTIFICATION_PROCESS_Q);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }

    }
}
