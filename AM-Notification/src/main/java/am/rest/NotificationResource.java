package am.rest;

import am.main.api.AppLogger;
import am.main.api.MessageHandler;
import am.main.api.validation.FormValidation;
import am.main.data.dto.notification.AMNotificationData;
import am.main.exception.BusinessException;
import am.main.session.AppSession;
import am.services.NotificationService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static am.common.NotificationParam.SOURCE;
import static am.data.enums.am.impl.ANF.NOTIFICATION_DTO;
import static am.data.enums.am.impl.ANI.I_VN_5;
import static am.data.enums.am.impl.ANP.RECEIVE_NTF;
import static am.main.data.enums.Interface.REST;
import static am.main.data.enums.impl.IEC.E_SYS_0;
import static am.main.data.enums.impl.IEC.E_VAL_13;

/**
 * Created by ahmed.motair on 2/6/2018.
 */
@Path("/notification")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {
    private static final String CLASS = NotificationResource.class.getSimpleName();

    @Inject private MessageHandler messageHandler;
    @Inject private AppLogger logger;

    @Inject private HttpSession httpSession;
    @Context private HttpServletRequest httpServletRequest;

    @Inject private NotificationService service;

    @Path("/publish/event")
    @POST
    public Response publishEvent(AMNotificationData data) {
        String METHOD = "publishEvent";
        AppSession session = new AppSession(SOURCE, REST, RECEIVE_NTF, httpSession.getId(),
                CLASS, METHOD, httpServletRequest.getRemoteAddr(), messageHandler);
        try{
            // Validating the Form Data
            new FormValidation<AMNotificationData>(session, logger, data, E_VAL_13, NOTIFICATION_DTO);

            service.receiveNotification(session, data);
            return Response.ok().entity(I_VN_5.getFullMsg()).build();
        }catch (Exception ex){
            logger.error(session, ex);

            if(ex instanceof BusinessException)
                throw (BusinessException) ex;
            else
                throw new BusinessException(session, E_SYS_0);
        }
    }
}
