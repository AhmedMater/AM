package am.queues.senders;

import am.data.hibernate.model.Notification;
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
import javax.jms.ObjectMessage;

import static am.common.NotificationParam.WEB_NOTIFICATION_QUEUE;
import static am.data.enums.am.impl.ANP.WEB_NOTIFICATION;
import static am.data.enums.am.impl.ANS.AM_NOTIFICATION;
import static am.main.data.enums.impl.IEC.E_JMS_5;
import static am.shared.common.JMSParams.QUEUE;
import static am.shared.common.JMSParams.WEB_NOTIFICATION_Q;

/**
 * Created by ahmed.motair on 1/17/2018.
 */
@MessageDriven(mappedName = WEB_NOTIFICATION_QUEUE)
public class WebNotificationListener implements MessageListener {
    private final String CLASS = WebNotificationListener.class.getSimpleName();

    @Resource private MessageDrivenContext context;
    @Inject private AppLogger logger;
    @Inject private NotificationService notificationService;

    @Override
    public void onMessage(Message message) {
        String METHOD = "onMessage";
        AppSession session = new AppSession(AM_NOTIFICATION, Interface.JMS, WEB_NOTIFICATION);
        try {
            String jmsID = message.getJMSMessageID();
            session.setId(jmsID);

            if (message instanceof ObjectMessage) {
                Notification notification = message.getBody(Notification.class);
                logger.startDebug(session, notification);

                notificationService.sendWebNotification(session, notification);

                logger.endDebug(session);
            }else
                throw new GeneralException(session, E_JMS_5, WEB_NOTIFICATION_Q);

        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }

    }
}
