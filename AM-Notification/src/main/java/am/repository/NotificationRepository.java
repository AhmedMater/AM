package am.repository;

import am.data.hibernate.model.lookup.NotificationType;
import am.data.hibernate.model.lookup.SystemAddress;
import am.main.api.AppLogger;
import am.main.api.db.DBManager;
import am.main.session.AppSession;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

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
}
