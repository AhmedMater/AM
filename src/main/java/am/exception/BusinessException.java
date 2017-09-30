package am.exception;

import am.api.enums.EC;
import am.session.AppSession;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BusinessException extends WebApplicationException {
    private EC errorCode;
    private String CLASS;
    private String METHOD;

    public BusinessException(AppSession session, Throwable ex) {
        this(session, ex, Status.BAD_REQUEST, null);
    }
    public BusinessException(AppSession session, Throwable ex, Status status) {
        this(session, ex, status, null);
    }
    public BusinessException(AppSession session, EC errorCode, Object ... args) {
        this(session, null, Status.BAD_REQUEST, errorCode, args);
    }
    public BusinessException(AppSession session, Throwable ex, EC errorCode, Object ... args) {
        this(session, ex, Status.BAD_REQUEST, errorCode, args);
    }
    public BusinessException(AppSession session, Status status, EC errorCode, Object ... args) {
        this(session, null, status, errorCode, args);
    }
    public BusinessException(AppSession session, Throwable ex, Status status, EC errorCode, Object ... args) {
        super(ex, Response.status(status)
                .entity(session.toString() + session.getErrorMsg(errorCode, args))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build());
        this.errorCode = errorCode;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }
}
