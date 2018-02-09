package am.services;

import am.data.enums.NotificationTypes;
import am.data.hibernate.model.Notification;
import am.data.hibernate.model.ReceivedEvent;
import am.data.hibernate.model.apps.ApplicationQuota;
import am.data.hibernate.model.apps.RegisteredApplication;
import am.data.hibernate.model.configuration.Event;
import am.data.hibernate.model.configuration.EventNotification;
import am.data.hibernate.model.configuration.EventParameter;
import am.data.hibernate.model.configuration.Template;
import am.data.hibernate.model.lookup.Category;
import am.data.hibernate.model.valid.event.EventNtfStart;
import am.data.hibernate.model.valid.event.ValidEvent;
import am.data.hibernate.model.valid.event.ValidEventDestination;
import am.data.hibernate.model.valid.event.ValidEventParameter;
import am.main.api.AppLogger;
import am.main.api.JMSManager;
import am.main.api.db.DBManager;
import am.main.api.validation.FormValidation;
import am.main.data.dto.notification.AMDestination;
import am.main.data.dto.notification.AMNotificationData;
import am.main.exception.BusinessException;
import am.main.exception.DBException;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.repository.ApplicationRepository;
import am.repository.NotificationRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static am.common.NotificationParam.VALIDATE_NTF_QUEUE;
import static am.data.enums.NotificationTypes.*;
import static am.data.enums.am.impl.ANE.*;
import static am.data.enums.am.impl.ANF.DESTINATION_DTO;
import static am.data.enums.am.impl.ANI.*;
import static am.data.enums.am.impl.ANQ.VALIDATE_NOTIFICATION;
import static am.data.enums.am.impl.ANW.*;
import static am.main.data.enums.Folders.*;
import static am.main.data.enums.impl.IEC.E_DB_16;
import static am.main.data.enums.impl.IEC.E_VAL_13;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
public class NotificationService {
    private final String CLASS = NotificationService.class.getSimpleName();

    @Inject private DBManager dbManager;
    @Inject private NotificationManager notificationManager;
    @Inject private AppLogger logger;
    @Inject private NotificationRepository ntfRepository;
    @Inject private ApplicationRepository appRepository;
    @Inject private ApplicationService appService;
    @Inject private JMSManager jmsManager;

    public void receiveNotification(AppSession appSession, AMNotificationData data) throws Exception {
        String METHOD = "receiveNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, data);

        RegisteredApplication application = appService.authenticate(session, data.getLoginData(), data.getNotificationID());

        ReceivedEvent receivedEvent = new ReceivedEvent(application, new Gson().toJson(data));
        receivedEvent = dbManager.persist(session, receivedEvent, false);

        jmsManager.sendTxtMessage(VALIDATE_NOTIFICATION, Integer.toString(receivedEvent.getReceivedEventID()));
        logger.info(session, I_VN_4, VALIDATE_NTF_QUEUE, data.getNotificationID());
    }

    public ValidEvent validateNotification(AppSession appSession, ReceivedEvent receivedEvent, AMNotificationData notification, RegisteredApplication app) throws Exception{
        String METHOD = "validateNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);

        logger.startDebug(session, receivedEvent, notification, app);
        logger.info(session, I_VN_1, notification.getNotificationID());

        ValidEvent validEvent = new ValidEvent();

        try{
            Event event = ntfRepository.getEventByID(session, notification.getEventID());
            validEvent.setEvent(event);
        }catch (DBException exc){
            if(exc.getCode().equals(E_DB_16))
                throw new BusinessException(session, E_VN_1, notification.getEventID());
            else
                throw exc;
        }

        try{
            Category category = ntfRepository.getCategoryByID(session, notification.getCategory(), app.getAppID());
            validEvent.setCategory(category);
            validEvent.setCategoryRelatedID(notification.getCategoryRelatedID());
        }catch (DBException exc){
            if(exc.getCode().equals(E_DB_16))
                throw new BusinessException(session, E_VN_2, notification.getCategory(), app.getAppName());
            else
                throw exc;
        }

        validEvent.setJmsID(session.getId());

        Set<ValidEventDestination> eventDestinations = new HashSet<>();

        for (AMDestination destination :notification.getDestinations()) {
            new FormValidation<AMDestination>(session, logger, destination, E_VAL_13, DESTINATION_DTO);

            for (String eventNtfID : destination.getEventNtfIDs()) {
                ValidEventDestination validEventDest = new ValidEventDestination(destination.getFullName(), destination.getEmail(), destination.getPhone(), destination.getUserID());

                try{
                    EventNotification eventNtf = ntfRepository.getEventNtfByID(session, eventNtfID, notification.getEventID());
                    validEventDest.setEvent(validEvent);
                    validEventDest.setEventNotification(eventNtf);

                    eventDestinations.add(validEventDest);
                }catch (DBException exc){
                    if(exc.getCode().equals(E_DB_16))
                        throw new BusinessException(session, E_VN_3, eventNtfID, notification.getEventID());
                    else
                        throw exc;
                }
            }
        }
        validEvent.setDestinationSet(eventDestinations);

        Set<ValidEventParameter> eventParameters = new HashSet<>();
        for (String key :notification.getParameters().keySet()) {
            if(key.trim().isEmpty())
                throw new BusinessException(session, E_VN_5);
            try{
                EventParameter eventParameter = ntfRepository.getEventParam(session, key, notification.getEventID());

                String value = notification.getParameters().get(key);

                if(value == null)
                    throw new BusinessException(session, E_VN_6, key);

                if(value.trim().isEmpty())
                    throw new BusinessException(session, E_VN_7, key);

                ValidEventParameter parameter = new ValidEventParameter(validEvent, key, value);

                eventParameters.add(parameter);
            }catch (DBException exc){
                if(exc.getCode().equals(E_DB_16))
                    throw new BusinessException(session, E_VN_4, key, notification.getEventID());
                else
                    throw exc;
            }
        }
        validEvent.setParameterSet(eventParameters);
        logger.info(session, I_VN_2, notification.getNotificationID(), app.getAppName());

        ApplicationQuota quota = appRepository.getApplicationTodayQuota(session, app.getAppID());

        validEvent.generateValidEventID(quota.getEventCounter());

        EventNtfStart eventNtfStart = new EventNtfStart(validEvent, quota.getNumOfNotification() + 1);
        dbManager.persist(session, eventNtfStart, false);

        quota.incEventCounter();
        quota.incNumOfNotification(validEvent.getNotificationNumber());
        dbManager.merge(session, quota, false);

        if(quota.getNumOfNotification() >= app.getQuotaPerDay()) {
            validEvent.setQuarantined(true);
            logger.warn(session, W_VN_1, app.getAppName(), validEvent.getValidEventID());
        }

        validEvent = dbManager.persist(session, validEvent, false);
        logger.info(session, I_VN_3, notification.getNotificationID(), app.getAppName());

        logger.endDebug(session, validEvent);
        return validEvent;
    }

    public boolean processNotification(AppSession appSession, String validEventID) {
        String METHOD = "processNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        boolean result = false;
        try {
            logger.startDebug(session, validEventID);

            ValidEvent validEvent = dbManager.find(session, ValidEvent.class, validEventID, false);

            if(validEvent != null)
                throw new BusinessException(session, E_PN_1, validEventID);

            List<Notification> notificationList = new ArrayList<>();
            for (ValidEventDestination destination : validEvent.getDestinationSet()) {
                for (Template template : destination.getEventNotification().getTemplates()) {
                    Notification notification = generateNotification(session, destination, template);
                    notification.generateNotificationID(2);
                    if(notification != null)
                        notificationList.add(notification);
                }
            }

            EventNtfStart eventNtfStart = dbManager.find(session, EventNtfStart.class, validEvent, false);

            int counter = eventNtfStart.getStartNtfID();
            for (Notification notification : notificationList) {
                notification.generateNotificationID(counter++);
                dbManager.persist(session, notification, false);
            }

            dbManager.remove(session, eventNtfStart, false);




//            EventType eventType = dbManager.find(session, EventType.class, inputEvent.getEventName(), true);
//            Set<EventNotification> eventNotifications = eventType.getEventNotifications();
//
//            Event event = new Event();
//            event.setType(eventType);
//            event.setCategoryRelatedID(inputEvent.getCategoryRelatedID());
//
//            Set<EventParameter> parameters = new HashSet<>();
//            for (InputParameter parameter: inputEvent.getParameters())
//                parameters.add(new EventParameter(event, parameter.getKey(), parameter.getValue()));
//
//            Map<String, Set<NotificationTemplate>> allEventTemplates = new HashMap<>();
//
//            for (EventNotification eventNotification : eventNotifications)
//                allEventTemplates.put(eventNotification.getEventNotificationID(), eventNotification.getNotificationTemplates());
//
//            for (InputDestination destination :inputEvent.getDestinations()) {
//                String fullName = destination.getFullName();
//                Set<NotificationTemplate> templateSets = allEventTemplates.get(destination.getEventNotificationID());
//
//                for (NotificationTemplate template : templateSets) {
//                    String templateMsg = readTemplateFile(session, template);
//                    String fullMsgFormatted = formatMessage(session, fullName, templateMsg, parameters);
//
//                    Notification notification = new Notification(session, event, template, destination);
//                    notification.setFullMessage(fullMsgFormatted);
//                    notification.setSender(repository.getRandomSender(session, template.getType()));
//
//                    event.addNotification(notification);
//                    logger.info(session, IC.AMT_0035, template.getType().getDescription(), fullName);
//                }
//            }
//
//            dbManager.persist(session, event, false);
//            logger.info(session, IC.AMT_0036, event.getType().getType());
//
//            for (Notification notification : event.getNotifications()) {
//                switch (NotificationTypes.getNotificationType(notification.getTemplate().getType().getType())){
//                    case EMAIL: jmsManager.sendMessage(EMAIL_NOTIFICATION, notification); break;
//                    case SMS: jmsManager.sendMessage(SMS_NOTIFICATION, notification); break;
//                    case WEB_NOTIFICATION: jmsManager.sendMessage(WEB_NOTIFICATION, notification); break;
//                    default:
//                        throw new GeneralException(session, E_NOT_4, notification.getTemplate().getType().getDescription());
//                }
//            }
//
//            logger.info(session, IC.AMT_0037, event.getType().getType());
            result = true;
            logger.endDebug(session, result);
        } catch (Exception ex){
            logger.error(session, ex);
        }
        return result;
    }

    private Notification generateNotification(AppSession appSession, ValidEventDestination destination, Template template) throws Exception{
        String METHOD = "generateNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, destination, template);

        Notification notification = new Notification(destination.getEvent(), template.getType());
        notification.setUserID(destination.getUserID());
        notification.setMessageSubject(template.getSubject());

        String msgBody = "";
        if(template.getType().getType().equals(EML.toString())){
            if(destination.getEmail() == null){
                logger.warn(session, W_PN_1, destination.getFullName());
                notification = null;
            }else{
                msgBody = readTemplateFile(session, template, EML);
                notification.setAddress(destination.getEmail());
            }
        } else if(template.getType().getType().equals(SMS.toString())){
            if(destination.getPhone() == null){
                logger.warn(session, W_PN_2, destination.getFullName());
                notification = null;
            }else{
                msgBody = readTemplateFile(session, template, SMS);
                notification.setAddress(destination.getPhone());
            }
        } else if(template.getType().getType().equals(WEB.toString())){
            if(destination.getUserID() == null){
                logger.warn(session, W_PN_3, destination.getFullName());
                notification = null;
            }else{
                msgBody = readTemplateFile(session, template, WEB);
                notification.setAddress(destination.getUserID());
            }
        }else
            throw new GeneralException(session, E_PN_2, template.getType().getType());

        if(notification != null) {
            msgBody = formatMessage(session, destination.getFullName(), msgBody, destination.getEvent().getParameterSet());
            notification.setMessageBody(msgBody);
        }

        logger.endDebug(session, notification);
        return notification;
    }

    private String readTemplateFile(AppSession appSession, Template template, NotificationTypes type) throws Exception{
        String METHOD = "readTemplateFile";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, template);

        String fileName = template.getFileName();

        String templateMsg = "";
        switch (type){
            case EML:
                templateMsg = notificationManager.readFile(EMAIL_TEMPLATES, fileName);
                break;
            case SMS:
                templateMsg = notificationManager.readFile(SMS_TEMPLATES, fileName);
                break;
            case WEB:
                templateMsg = notificationManager.readFile(WEB_NOTIFICATION_TEMPLATES, fileName);
                break;
            default:
                throw new GeneralException(session, E_PN_2, template.getType().getDescription());
        }

        logger.endDebug(session, templateMsg);
        return templateMsg;
    }

    private String formatMessage(AppSession appSession, String fullName, String templateMsg, Set<ValidEventParameter> parameters) throws Exception{
        String METHOD = "formatMessage";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, fullName, templateMsg, parameters);

        String fullMessage = templateMsg.replaceAll("(\\{\\{USER_FULL_NAME}})", fullName);

        for (ValidEventParameter parameter : parameters)
            fullMessage = fullMessage.replaceAll("(\\{\\{"+ parameter.getKey() +"}})", parameter.getValue());

        if(fullMessage.matches(".*\\{\\{[A-Za-z_]+}}.*"))
            throw new GeneralException(session, E_PN_3);

        logger.endDebug(session, fullMessage);
        return fullMessage;
    }

//    public boolean validateNotification(AppSession appSession, InputEvent notification) throws Exception{
//        String METHOD = "validateNotification";
//        AppSession session = appSession.updateSession(CLASS, METHOD);
//        try {
//            logger.startDebug(session, notification);
//
//            new FormValidation<InputEvent>(session, logger, notification, EC.AMT_0001, Forms.NOTIFICATION_EVENT);
//
//            Set<NotificationTemplate> templates = new HashSet<>();
//
//            Set<InputDestination> destinations = notification.getDestinations();
//            for (InputDestination input : destinations) {
//                new FormValidation<InputDestination>(session, logger, input, EC.AMT_0001, Forms.NOTIFICATION_EVENT);
//
//                EventNotification eventNot = dbManager.find(session, EventNotification.class, input.getEventNotificationID(), true);
//                if(eventNot == null)
//                    throw new BusinessException(session, EC.AMT_0078, input.getEventNotificationID());
//                else
//                    templates.addAll(eventNot.getNotificationTemplates());
//            }
//
//            Set<InputParameter> parameters = notification.getParameters();
//            for (NotificationTemplate template : templates)
//                checkParametersEnough(session, template, parameters);
//
//            logger.info(session, IC.AMT_0032, notification.getEventName());
//
//            notification.setValid(true);
//            notification.setValidationDate(new Date());
//            dbManager.merge(session, notification, false);
//            logger.info(session, IC.AMT_0033, notification.getEventName());
//
//            jmsManager.sendMessage(NOTIFICATION_PROCESS, notification);
//            logger.endDebug(session);
//            return true;
//        }catch (Exception ex){
//            logger.error(session, ex, EC.AMT_0079);
//
//            notification.setValid(false);
//            notification.setValidationDate(new Date());
//            notification.setFailureReason(ex.getMessage());
//            dbManager.merge(session, notification, false);
//            logger.info(session, IC.AMT_0034, notification.getEventName());
//
//            // Create new Error Event for the Admin to Check the Error
//            return false;
//        }
//    }
//
//    private void checkParametersEnough(AppSession appSession, NotificationTemplate template, Set<InputParameter> parameters) throws Exception{
//        String METHOD = "validateNotification";
//        AppSession session = appSession.updateSession(CLASS, METHOD);
//        logger.startDebug(session, template, parameters);
//
//        String type = template.getType().getType();
//        String fileName = template.getTemplateName();
//
//        String templateString = readTemplateFile(session, template);
//
//        Pattern pattern = Pattern.compile("\\{\\{[A-Za-z]+}}");
//        Matcher matcher = pattern.matcher(templateString);
//
//        Set<String> templateAttributes = new HashSet<>();
//        while (matcher.find())
//            templateAttributes.add(matcher.group());
//
//        if(templateAttributes.size() != template.getNumOfPlaceHolders())
//            throw new GeneralException(session, EC.AMT_0075);
//
//        for (String tempAttribute : templateAttributes) {
//            String _tempAttribute = tempAttribute.replaceAll("[{}]+", "");
//            boolean found = false;
//            for (InputParameter parameter : parameters) {
//                if(parameter.getKey().equals(_tempAttribute)){
//                    found = true;
//                    break;
//                }
//            }
//            if(found)
//                throw new GeneralException(session, EC.AMT_0076, _tempAttribute);
//        }
//
//        logger.endDebug(session);
//    }

    public void sendEmailNotification(AppSession appSession, Notification notification) throws Exception{
        String METHOD = "sendEmailNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        try {
            logger.startDebug(session, notification);

            notificationManager.sendEmail(session, notification);
            logger.info(session, I_SN_1, notification.getNotificationType().getDescription(), notification.getNotificationID(), notification.getAddress());

            logger.endDebug(session);
        }catch (Exception ex){
            logger.error(session, ex, E_SN_1, notification.getNotificationType().getDescription(), notification.getAddress());

            notification.setSent(false);
            notification.incrementTrails();
            notification.setFailureReason(ex.getMessage());
            dbManager.merge(session, notification, false);
            logger.info(session, I_SN_2, notification.getNotificationType().getDescription(), notification.getAddress());

            // Create new Error Event for the Admin to Check the Error
        }
    }

    public void sendWebNotification(AppSession session, Notification notification) throws Exception{
        notification.setSent(false);
        notification.incrementTrails();
        notification.setFailureReason("Not Supported Yet");
        dbManager.merge(session, notification, false);
    }

    public void sendSMSNotification(AppSession session, Notification notification) throws Exception{
        notification.setSent(false);
        notification.incrementTrails();
        notification.setFailureReason("Not Supported Yet");
        dbManager.merge(session, notification, false);
    }

}
