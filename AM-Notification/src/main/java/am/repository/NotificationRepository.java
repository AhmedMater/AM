package am.repository;

import am.data.hibernate.model.configuration.Event;
import am.data.hibernate.model.configuration.EventNotification;
import am.data.hibernate.model.configuration.EventParameter;
import am.data.hibernate.model.lookup.Category;
import am.data.hibernate.model.lookup.NotificationType;
import am.data.hibernate.model.lookup.SystemAddress;
import am.main.api.AppLogger;
import am.main.api.db.DBManager;
import am.main.session.AppSession;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
public class NotificationRepository {
    private final String CLASS = NotificationRepository.class.getSimpleName();
    @Inject private DBManager dbManager;
    @Inject private AppLogger logger;

    public List<SystemAddress> getAllSystemAddressByType(AppSession appSession, NotificationType type) throws Exception{
        String METHOD = "getAllSystemAddressByType";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, type);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(SystemAddress.TYPE, type.getType());

        List<SystemAddress> systemAddressList = dbManager.getList(session, true,
                SystemAddress.class, parameters);

        logger.endDebug(session, systemAddressList);
        return systemAddressList;
    }

    public SystemAddress getRandomSender(AppSession appSession, NotificationType type) throws Exception{
        String METHOD = "getRandomSender";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, type);
        SystemAddress systemAddress = new SystemAddress();

        List<SystemAddress> systemAddressList = getAllSystemAddressByType(session, type);
        systemAddress = systemAddressList.get((int) (Math.random() * systemAddressList.size()));

        logger.endDebug(session, systemAddress);
        return systemAddress;
    }

    public Event getEventByID(AppSession appSession, String eventID) throws Exception{
        String METHOD = "getEventByID";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, eventID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Event.EVENT_ID, eventID);

        Event event = dbManager.getSingleResult(session, true, Event.class, parameters);

        logger.endDebug(session, event);
        return event;
    }

    public Category getCategoryByID(AppSession appSession, String categoryID, String appID) throws Exception{
        String METHOD = "getCategoryByID";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, categoryID, appID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Category.CATEGORY_ID, categoryID);
        parameters.put(Category.APP_ID, appID);

        Category category = dbManager.getSingleResult(session, true, Category.class, parameters);

        logger.endDebug(session, category);
        return category;
    }

    public EventNotification getEventNtfByID(AppSession appSession, String eventNtfID, String eventID) throws Exception {
        String METHOD = "getEventNtfByID";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, eventNtfID, eventID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(EventNotification.EVENT_NTF_ID, eventNtfID);
        parameters.put(EventNotification.EVENT_ID, eventID);

        EventNotification eventNotification = dbManager.getSingleResult(session, true, EventNotification.class, parameters);

        logger.endDebug(session, eventNotification);
        return eventNotification;
    }

    public EventParameter getEventParam(AppSession appSession, String parameter, String eventID) throws Exception{
        String METHOD = "getEventParam";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, parameter, eventID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(EventParameter.PARAMETER, parameter);
        parameters.put(EventParameter.EVENT_ID, eventID);

        EventParameter eventParameter = dbManager.getSingleResult(session, true, EventParameter.class, parameters);

        logger.endDebug(session, eventParameter);
        return eventParameter;
    }
}
