package am.repository;

import am.data.hibernate.model.apps.ApplicationQuota;
import am.data.hibernate.model.apps.RegisteredApplication;
import am.main.api.AppLogger;
import am.main.api.db.DBManager;
import am.main.session.AppSession;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmed.motair on 2/6/2018.
 */
public class ApplicationRepository {
    private final String CLASS = ApplicationRepository.class.getSimpleName();
    @Inject private DBManager dbManager;
    @Inject private AppLogger logger;

    public RegisteredApplication getRegisteredAppByID(AppSession appSession, String appID) throws Exception{
        String METHOD = "getRegisteredAppByID";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, appID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(RegisteredApplication.APP_ID, appID);

        RegisteredApplication application = dbManager.getSingleResult(session, true,
                RegisteredApplication.class, parameters);

        logger.endDebug(session, application);
        return application;
    }

    public RegisteredApplication getRegAppByUsername(AppSession appSession, String username) throws Exception{
        String METHOD = "getRegAppByUsername";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, username);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(RegisteredApplication.USER_NAME, username);

        RegisteredApplication application = dbManager.getSingleResult(session, true,
                RegisteredApplication.class, parameters);

        logger.endDebug(session, application);
        return application;
    }

    public ApplicationQuota getApplicationTodayQuota(AppSession appSession, String appID) throws Exception{
        String METHOD = "getApplicationTodayQuota";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, appID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ApplicationQuota.APP_ID, appID);
        parameters.put(ApplicationQuota.QUOTA_DATE, new SimpleDateFormat("yyyy-MM-dd"));

        ApplicationQuota quota = dbManager.getSingleResult(session, true,
                ApplicationQuota.class, parameters);

        logger.endDebug(session, quota);
        return quota;
    }

}
