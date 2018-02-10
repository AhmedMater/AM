package am.queues;

import am.data.enums.am.impl.ANQ;
import am.data.hibernate.model.ReceivedEvent;
import am.data.hibernate.model.apps.RegisteredApplication;
import am.data.hibernate.model.valid.event.ValidEvent;
import am.main.api.AppLogger;
import am.main.api.JMSManager;
import am.main.api.db.DBManager;
import am.main.data.dto.notification.AMNotificationData;
import am.main.data.enums.Interface;
import am.main.exception.BusinessException;
import am.main.session.AppSession;
import am.repository.ApplicationRepository;
import am.services.NotificationService;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static am.common.NotificationParam.*;
import static am.data.enums.am.impl.ANE.E_VN_8;
import static am.data.enums.am.impl.ANI.I_VN_4;
import static am.data.enums.am.impl.ANP.VALIDATE_NTF;
import static am.main.data.enums.impl.AME.E_JMS_5;

/**
 * Created by ahmed.motair on 1/16/2018.
 */
@MessageDriven(mappedName = VALIDATE_NTF_QUEUE)
public class ValidationListener implements MessageListener{

    private final String CLASS = ValidationListener.class.getSimpleName();

    @Inject private AppLogger logger;
    @Inject private NotificationService notificationService;
    @Inject private ApplicationRepository appRepository;
    @Inject private DBManager dbManager;
    @Inject private JMSManager jmsManager;
    @Resource private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        String METHOD = "onMessage";
        AppSession session = new AppSession(SOURCE, Interface.JMS, VALIDATE_NTF);
        try {
            String jmsID = message.getJMSMessageID();
            session.setId(jmsID);

            if (message instanceof TextMessage) {
//                if(!message.isBodyAssignableTo(S.class))
//                    logger.error(session, E_JMS_5, VALIDATE_NTF_QUEUE, AMNotificationData.class.getSimpleName(), message.getJMSType());
//                else {
                    Integer notificationID = Integer.parseInt(((TextMessage) message).getText());

                    logger.startDebug(session, notificationID);

                    ReceivedEvent receivedEvent = dbManager.find(session, ReceivedEvent.class, notificationID, false);
                    AMNotificationData notification = new Gson().fromJson(receivedEvent.getEventContent(), AMNotificationData.class);
                    RegisteredApplication app = appRepository.getRegAppByUsername(session, notification.getLoginData().getUsername());

                    try {
                        ValidEvent validEvent = notificationService.validateNotification(session,
                                receivedEvent, notification, app);

                        if(!validEvent.isQuarantined()) {
                            jmsManager.sendTxtMessage(ANQ.PROCESS_NOTIFICATION, validEvent.getValidEventID());
                            logger.info(session, I_VN_4, PROCESS_NTF_QUEUE, notification.getNotificationID());
                        }
                    }catch (BusinessException exc) {
                        logger.error(session, exc, E_VN_8, notificationID, app.getAppName());

                        jmsManager.sendTxtMessage(ANQ.INVALID_NOTIFICATION, Integer.toString(notificationID));
                        logger.info(session, I_VN_4, INVALID_NTF_QUEUE, notification.getNotificationID());
                    }
                    logger.endDebug(session);
//                }
            }else
                logger.error(session, E_JMS_5, VALIDATE_NTF_QUEUE, AMNotificationData.class.getSimpleName(), message.getJMSType());
        }catch (Exception ex){
            logger.error(session, ex);
            context.setRollbackOnly();
        }
    }
}