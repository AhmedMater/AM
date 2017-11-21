package am.main.exception;

import am.main.common.validation.FormValidation;
import am.main.session.AppSession;
import am.shared.enums.EC;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BusinessException extends WebApplicationException {
    private EC errorCode;
    private String CLASS;
    private String METHOD;
    private String fullErrMsg;
    private FormValidation errorList;

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
                .entity(session.getErrorMsg(errorCode, args))
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build());
        this.errorCode = errorCode;
        this.fullErrMsg = session.toString() + session.getErrorMsg(errorCode, args);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }
    public BusinessException(AppSession session, FormValidation validation) {
        super(Response.status(Status.BAD_REQUEST)
                .entity(validation)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
        this.errorCode = validation.getCode();
        this.fullErrMsg = session.toString() + validation.getMainError() + "\n" + validation.getErrorList();
        this.errorList = validation;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }

    public EC getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(EC errorCode) {
        this.errorCode = errorCode;
    }

    public String getCLASS() {
        return CLASS;
    }
    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getMETHOD() {
        return METHOD;
    }
    public void setMETHOD(String METHOD) {
        this.METHOD = METHOD;
    }

    public String getFullErrMsg() {
        return fullErrMsg;
    }
    public void setFullErrMsg(String fullErrMsg) {
        this.fullErrMsg = fullErrMsg;
    }
}
