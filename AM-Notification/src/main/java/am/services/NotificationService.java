package am.services;

import am.data.hibernate.model.Event;
import am.data.hibernate.model.EventParameter;
import am.data.hibernate.model.Notification;
import am.data.hibernate.model.configuration.EventNotification;
import am.data.hibernate.model.configuration.EventType;
import am.data.hibernate.model.configuration.NotificationTemplate;
import am.data.hibernate.model.input.InputDestination;
import am.data.hibernate.model.input.InputEvent;
import am.data.hibernate.model.input.InputParameter;
import am.main.api.AppLogger;
import am.main.api.JMSManager;
import am.main.api.db.DBManager;
import am.main.api.validation.FormValidation;
import am.main.exception.BusinessException;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.repository.NotificationRepository;
import am.shared.dto.AMDestination;
import am.shared.dto.AMNotification;
import am.shared.enums.EC;
import am.shared.enums.Forms;
import am.shared.enums.IC;
import am.shared.enums.notification.AMEventNotifications;
import am.shared.enums.notification.Attributes;
import am.shared.enums.notification.NotificationTypes;

import javax.inject.Inject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static am.main.data.enums.Folders.*;
import static am.shared.enums.JMSQueues.*;
import static am.shared.enums.notification.Attributes.USER_FULL_NAME;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
public class NotificationService {
    private final String CLASS = NotificationService.class.getSimpleName();

    @Inject private DBManager dbManager;
    @Inject private NotificationManager notificationManager;
    @Inject private AppLogger logger;
    @Inject private NotificationRepository repository;
    @Inject private JMSManager jmsManager;

    public boolean processNotification(AppSession appSession, InputEvent inputEvent) {
        String METHOD = "processNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        boolean result = false;
        try {
            logger.startDebug(session, inputEvent);

            EventType eventType = dbManager.find(session, EventType.class, inputEvent.getEventName(), true);
            Set<EventNotification> eventNotifications = eventType.getEventNotifications();

            Event event = new Event();
            event.setType(eventType);
            event.setCategoryRelatedID(inputEvent.getCategoryRelatedID());

            Set<EventParameter> parameters = new HashSet<>();
            for (InputParameter parameter: inputEvent.getParameters())
                parameters.add(new EventParameter(event, parameter.getKey(), parameter.getValue()));

            Map<String, Set<NotificationTemplate>> allEventTemplates = new HashMap<>();

            for (EventNotification eventNotification : eventNotifications)
                allEventTemplates.put(eventNotification.getEventNotificationID(), eventNotification.getNotificationTemplates());

            for (InputDestination destination :inputEvent.getDestinations()) {
                String fullName = destination.getFullName();
                Set<NotificationTemplate> templateSets = allEventTemplates.get(destination.getEventNotificationID());

                for (NotificationTemplate template : templateSets) {
                    String templateMsg = readTemplateFile(session, template);
                    String fullMsgFormatted = formatMessage(session, fullName, templateMsg, parameters);

                    Notification notification = new Notification(session, event, template, destination);
                    notification.setFullMessage(fullMsgFormatted);
                    notification.setSender(repository.getRandomSender(session, template.getType()));

                    event.addNotification(notification);
                    logger.info(session, IC.AMT_0035, template.getType().getDescription(), fullName);
                }
            }

            dbManager.persist(session, event, false);
            logger.info(session, IC.AMT_0036, event.getType().getType());

            for (Notification notification : event.getNotifications()) {
                switch (NotificationTypes.getNotificationType(notification.getTemplate().getType().getType())){
                    case EMAIL: jmsManager.sendMessage(EMAIL_NOTIFICATION, notification); break;
                    case SMS: jmsManager.sendMessage(SMS_NOTIFICATION, notification); break;
                    case WEB_NOTIFICATION: jmsManager.sendMessage(WEB_NOTIFICATION, notification); break;
                    default:
                        throw new GeneralException(session, EC.AMT_0077, notification.getTemplate().getType().getDescription());
                }
            }

            logger.info(session, IC.AMT_0037, event.getType().getType());
            result = true;
            logger.endDebug(session, result);
        } catch (Exception ex){
            logger.error(session, ex);
        }
        return result;
    }

    private String readTemplateFile(AppSession appSession, NotificationTemplate template) throws Exception{
        String METHOD = "formatMessage";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, template);
        String templateMsg = "";

        String type = template.getType().getType();
        String fileName = template.getTemplateName();

        String templateString = "";
        switch (NotificationTypes.getNotificationType(type)){
            case EMAIL:
                templateString = notificationManager.readFile(EMAIL_TEMPLATES, fileName);
                break;
            case SMS:
                templateString = notificationManager.readFile(SMS_TEMPLATES, fileName);
                break;
            case WEB_NOTIFICATION:
                templateString = notificationManager.readFile(WEB_NOTIFICATION_TEMPLATES, fileName);
                break;
            default:
                throw new GeneralException(session, EC.AMT_0077, template.getType().getDescription());
        }

        logger.endDebug(session, templateMsg);
        return templateMsg;
    }

    private String formatMessage(AppSession appSession, String fullName, String templateMsg, Set<EventParameter> parameters) throws Exception{
        String METHOD = "formatMessage";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, fullName, templateMsg, parameters);
        String fullMessage = templateMsg.replaceAll("({{"+ USER_FULL_NAME.attribute() + "}})", fullName);

        for (EventParameter parameter : parameters)
            fullMessage = fullMessage.replaceAll("({{"+ parameter.getKey() +"}})", parameter.getValue());

        if(fullMessage.matches(".*\\{\\{[A-Za-z]+}}.*"))
            throw new GeneralException(session, EC.AMT_0000);

        logger.endDebug(session, fullMessage);
        return fullMessage;
    }

    public boolean insertNewNotification(AppSession appSession, AMNotification notification) {
        String METHOD = "insertNewNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, notification);
        boolean result = false;
        try{
            InputEvent event = new InputEvent();
            event.setEventName(notification.getEvent().eventName());
            event.setCategoryRelatedID(notification.getCategoryRelatedID());
            event.setJmsID(session.getId());

            Map<AMEventNotifications, List<AMDestination>> destinations = notification.getDestinations();
            Set<AMEventNotifications> eventNotificationsSet = destinations.keySet();
            for (AMEventNotifications eventNotification : eventNotificationsSet) {
                for (AMDestination destination : destinations.get(eventNotification)) {
                    InputDestination inputDestination = new InputDestination(event, eventNotification.eventNotification());
                    inputDestination.setEmail(destination.getEmail());
                    inputDestination.setPhone(destination.getPhone());
                    inputDestination.setUserID(destination.getUserID());
                    event.addDestinations(inputDestination);
                }
            }

            Map<Attributes, String> attributes = notification.getParameters();
            for (Attributes attribute: attributes.keySet())
                event.addParameters(new InputParameter(event, attribute.attribute(), attributes.get(attribute)));

            event = dbManager.persist(session, event, false);
            logger.info(session, IC.AMT_0031, event.getEventName());

            jmsManager.sendMessage(NOTIFICATION_VALIDATION, event);

            result = true;
            logger.endDebug(session, result);
        }catch (Exception ex){
            logger.error(session, ex);
        }
        return result;

    }

    public boolean validateNotification(AppSession appSession, InputEvent notification) throws Exception{
        String METHOD = "validateNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        try {
            logger.startDebug(session, notification);
            
            new FormValidation<InputEvent>(session, logger, notification, EC.AMT_0001, Forms.NOTIFICATION_EVENT);

            Set<NotificationTemplate> templates = new HashSet<>();

            Set<InputDestination> destinations = notification.getDestinations();
            for (InputDestination input : destinations) {
                new FormValidation<InputDestination>(session, logger, input, EC.AMT_0001, Forms.NOTIFICATION_EVENT);

                EventNotification eventNot = dbManager.find(session, EventNotification.class, input.getEventNotificationID(), true);
                if(eventNot == null)
                    throw new BusinessException(session, EC.AMT_0078, input.getEventNotificationID());
                else
                    templates.addAll(eventNot.getNotificationTemplates());
            }

            Set<InputParameter> parameters = notification.getParameters();
            for (NotificationTemplate template : templates)
                checkParametersEnough(session, template, parameters);

            logger.info(session, IC.AMT_0032, notification.getEventName());

            notification.setValid(true);
            notification.setValidationDate(new Date());
            dbManager.merge(session, notification, false);
            logger.info(session, IC.AMT_0033, notification.getEventName());

            jmsManager.sendMessage(NOTIFICATION_PROCESS, notification);
            logger.endDebug(session);
            return true;
        }catch (Exception ex){
            logger.error(session, ex, EC.AMT_0079);

            notification.setValid(false);
            notification.setValidationDate(new Date());
            notification.setFailureReason(ex.getMessage());
            dbManager.merge(session, notification, false);
            logger.info(session, IC.AMT_0034, notification.getEventName());

            // Create new Error Event for the Admin to Check the Error
            return false;
        }
    }

    private void checkParametersEnough(AppSession appSession, NotificationTemplate template, Set<InputParameter> parameters) throws Exception{
        String METHOD = "validateNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, template, parameters);

        String type = template.getType().getType();
        String fileName = template.getTemplateName();

        String templateString = readTemplateFile(session, template);

        Pattern pattern = Pattern.compile("\\{\\{[A-Za-z]+}}");
        Matcher matcher = pattern.matcher(templateString);

        Set<String> templateAttributes = new HashSet<>();
        while (matcher.find())
            templateAttributes.add(matcher.group());

        if(templateAttributes.size() != template.getNumOfPlaceHolders())
            throw new GeneralException(session, EC.AMT_0075);

        for (String tempAttribute : templateAttributes) {
            String _tempAttribute = tempAttribute.replaceAll("[{}]+", "");
            boolean found = false;
            for (InputParameter parameter : parameters) {
                if(parameter.getKey().equals(_tempAttribute)){
                    found = true;
                    break;
                }
            }
            if(found)
                throw new GeneralException(session, EC.AMT_0076, _tempAttribute);
        }

        logger.endDebug(session);
    }

    public void sendEmailNotification(AppSession appSession, Notification notification) throws Exception{
        String METHOD = "sendEmailNotification";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        try {
            logger.startDebug(session, notification);

            notificationManager.sendEmail(session, notification);
            logger.info(session, IC.AMT_0038, notification.getTemplate().getType().getDescription(), notification.getNotificationID(), notification.getAddress());

            logger.endDebug(session);
        }catch (Exception ex){
            logger.error(session, ex, EC.AMT_0080, notification.getTemplate().getType().getDescription(), notification.getAddress());

            notification.setSent(false);
            notification.incrementTrails();
            notification.setFailureReason(ex.getMessage());
            dbManager.merge(session, notification, false);
            logger.info(session, IC.AMT_0039, notification.getTemplate().getType().getDescription(), notification.getAddress());

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
