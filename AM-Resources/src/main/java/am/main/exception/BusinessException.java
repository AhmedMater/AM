package am.main.exception;

import am.main.api.validation.FormValidation;
import am.main.session.AppSession;
import am.main.spi.AMCode;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BusinessException extends WebApplicationException {
    private AMCode errorCode;
    private String formattedError;
    private String CLASS;
    private String METHOD;
    private String fullErrMsg;
    private FormValidation errorList;

    public BusinessException(AppSession session, AMCode errorCode, Object ... args) {
        this(session, Status.BAD_REQUEST, errorCode, args);
    }

    public BusinessException(AppSession session, Status status, AMCode errorCode, Object ... args) {
        super(Response.status(status)
                .entity(new AMError(errorCode,  session.getMessageHandler(), args))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
        this.errorCode = errorCode;
        this.formattedError = errorCode.getFullMsg(session.getMessageHandler(), args);
        this.fullErrMsg = session.toString() + this.formattedError;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }
    public BusinessException(AppSession session, FormValidation validation) {
        super(Response.status(Status.BAD_REQUEST)
                .entity(new AMError(validation))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
        this.errorCode = validation.getCode();
        this.formattedError = validation.getMainError() + "\n" + validation.getErrorList();
        this.fullErrMsg = session.toString() + this.formattedError;
        this.errorList = validation;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }

    public AMCode getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(AMCode errorCode) {
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

    public String getFormattedError() {
        return formattedError;
    }
    public void setFormattedError(String formattedError) {
        this.formattedError = formattedError;
    }

    public FormValidation getErrorList() {
        return errorList;
    }
    public void setErrorList(FormValidation errorList) {
        this.errorList = errorList;
    }
}
