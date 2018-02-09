package am.services;

import am.data.hibernate.model.apps.RegisteredApplication;
import am.main.api.AppLogger;
import am.main.api.SecurityManager;
import am.main.data.dto.LoginData;
import am.main.exception.BusinessException;
import am.main.session.AppSession;
import am.repository.ApplicationRepository;

import javax.inject.Inject;
import java.util.Base64;

import static am.data.enums.am.impl.ANE.E_REC_2;
import static am.data.enums.am.impl.ANE.E_REC_3;
import static am.data.enums.am.impl.ANE.E_REC_4;
import static am.data.enums.am.impl.ANI.I_REC_1;
import static am.data.enums.am.impl.ANI.I_REC_2;

/**
 * Created by ahmed.motair on 2/6/2018.
 */
public class ApplicationService {
    private final String CLASS = ApplicationService.class.getSimpleName();
    @Inject private ApplicationRepository appRepository;
    @Inject private AppLogger logger;
    @Inject private SecurityManager securityManager;

    public RegisteredApplication authenticate(AppSession appSession, LoginData loginData, String notificationID) throws Exception{
        String METHOD = "authenticate";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, loginData, notificationID);

        logger.info(session, I_REC_1, notificationID);

        String passwordString;
        try {
            byte[] decoded = Base64.getDecoder().decode(loginData.getPassword());
            passwordString = new String(decoded, "UTF-8");
        } catch (Exception ex) {
            logger.error(session, ex);
            throw new BusinessException(session, E_REC_3);
        }

        RegisteredApplication application = appRepository.getRegAppByUsername(session, loginData.getUsername());

        String hashedPassword = securityManager.dm5Hash(passwordString);
        if(!application.getPassword().equals(hashedPassword))
            throw new BusinessException(session, E_REC_2, application.getUsername());

        if(application.getReachedMaxQuota())
            throw new BusinessException(session, E_REC_4, application.getAppName());

        logger.info(session, I_REC_2, application.getAppName());
        logger.endDebug(session, application);
        return application;
    }


}
