package am.exception;

import am.api.error.EC;
import am.session.AppSession;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BusinessException extends WebApplicationException {
    private EC errorCode;
    private String CLASS;
    private String METHOD;

    public BusinessException(AppSession session, String CLASS, String METHOD, Throwable ex) {
        this(session, CLASS, METHOD, ex, Status.BAD_REQUEST, null);
    }
    public BusinessException(AppSession session, String CLASS, String METHOD, Throwable ex, Status status) {
        this(session, CLASS, METHOD, ex, status, null);
    }
    public BusinessException(AppSession session, String CLASS, String METHOD, Throwable ex, EC errorCode, Object ... args) {
        this(session, CLASS, METHOD, ex, Status.BAD_REQUEST, errorCode, args);
    }
    public BusinessException(AppSession session, String CLASS, String METHOD, Status status, EC errorCode, Object ... args) {
        this(session, CLASS, METHOD, null, status, errorCode, args);
    }
    public BusinessException(AppSession session, String CLASS, String METHOD, Throwable ex, Status status, EC errorCode, Object ... args) {
        super(ex, Response.status(status)
                .entity(CLASS + "." + METHOD + "(): \n" + session.getErrorHandler().getMsg(errorCode, args))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build());
        this.errorCode = errorCode;
        this.CLASS = CLASS;
        this.METHOD = METHOD;
    }
}
