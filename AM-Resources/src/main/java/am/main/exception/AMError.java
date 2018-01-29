package am.main.exception;

import am.main.api.MessageHandler;
import am.main.api.validation.FormValidation;
import am.main.spi.AMCode;

import java.io.Serializable;

/**
 * Created by ahmed.motair on 11/28/2017.
 */
public class AMError implements Serializable{
    private String code;
    private String message;
    private FormValidation validation;

    public AMError() {
    }
    public AMError(AMCode code, String message) {
        this.code = code.getFullCode();
        this.message = message;
    }
    public AMError(AMCode code, MessageHandler messageHandler, Object ... args) {
        this.code = code.getFullCode();
        this.message = code.getFullMsg(messageHandler, args);
    }
    public AMError(FormValidation validation) {
        this.validation = validation;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public FormValidation getValidation() {
        return validation;
    }
    public void setValidation(FormValidation validation) {
        this.validation = validation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMError)) return false;

        AMError amError = (AMError) o;

        if (getCode() != null ? !getCode().equals(amError.getCode()) : amError.getCode() != null) return false;
        if (getValidation() != null ? !getValidation().equals(amError.getValidation()) : amError.getValidation() != null) return false;
        return getMessage() != null ? getMessage().equals(amError.getMessage()) : amError.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getValidation() != null ? getValidation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AMError{" +
                "code = " + code +
                ", message = " + message +
                ", validation = " + validation +
                "}\n";
    }
}
