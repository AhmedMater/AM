package am.main.api.validation;

import am.main.api.AppLogger;
import am.main.common.RegExp;
import am.main.data.enums.impl.IEC;
import am.main.exception.BusinessException;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.AMCode;
import am.main.spi.AMForm;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

import static am.main.api.validation.groups.FormValidation.*;
import static am.main.data.enums.impl.IEC.*;
import static am.main.data.enums.impl.IIC.I_VAL_1;
import static am.main.data.enums.impl.IIC.I_VAL_2;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
public class FormValidation<T> implements Serializable{
    private AMCode code;
    private String mainError;
    private String formName;
    private List<String> formErrors;

    public FormValidation() {
    }
    public FormValidation(String mainError, String ... formErrors) {
        this.mainError = mainError;
        this.formErrors = Arrays.asList(formErrors);

    }
    public FormValidation(AppSession session, AppLogger logger, T object, AMCode code, AMForm form) throws BusinessException, GeneralException {
        logger.info(session, I_VAL_1, form.getForm());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validator.validate(object, am.main.api.validation.groups.FormValidation.class);

        if(errors.size() > 0) {
            this.code = code;
            this.mainError = code.getFullMsg(session.getMessageHandler(), form);
            this.formName = form.getForm();

            this.formErrors = new ArrayList<>();
            for (ConstraintViolation<T> error : errors) {
                AMCode errorCode = IEC.getCodeByFullCode(error.getMessage());
                if(errorCode == null)
                    throw new GeneralException(session, E_VAL_20, error.getMessage());

                String message = "";
                String fieldName = "";
                String fieldValue = "";

                if(!errorCode.equals(E_VAL_1))
                    fieldValue = (error.getInvalidValue() == null) ? "Null" : error.getInvalidValue().toString();

                //Get the Field Name
                Path fieldPath = error.getPropertyPath();
                for (Path.Node node : fieldPath) {
                    if (!node.getName().isEmpty()) {
                        try {
                            Method method = object.getClass().getMethod("getFIELDS");
                            Map<String, String> FIELDS = (Map<String, String>) method.invoke(object);
                            fieldName = FIELDS.get(node.getName());
                            break;
                        }catch (Exception ex){
                            fieldName = node.getName();
                        }
                    }
                }

                if(fieldName.trim().isEmpty())
                    throw new BusinessException(session, E_VAL_21);

                Map<String, Object> attributes = error.getConstraintDescriptor().getAttributes();
                Integer maxLength, minLength, eqLength;
                Long minMaxValue;

                switch (errorCode.getFullCode()){
                    case REQUIRED: case EMPTY_STR:
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldName);
                        break;
                    case REGEX:
                        String regexError = RegExp.MESSAGES.get(attributes.get("regexp"));
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, regexError);
                        break;
                    case FUTURE_DATE: case POSITIVE_NUM: case POSITIVE_NUM_AND_ZERO:
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName);
                        break;
                    case MAX_LENGTH:
                        maxLength = (Integer) attributes.get("max");
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, maxLength);
                        break;
                    case MIN_LENGTH:
                        minLength = (Integer) attributes.get("min");
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, minLength);
                        break;
                    case MIN_MAX_LENGTH: case EQ_LENGTH:
                        maxLength = (Integer) attributes.get("max");
                        minLength = (Integer) attributes.get("min");

                        if(errorCode.equals(E_VAL_7) && maxLength.equals(minLength))
                            message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, minLength);
                        else if(errorCode.equals(E_VAL_6) && !maxLength.equals(minLength))
                            message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, minLength, maxLength);
                        else
                            throw new GeneralException(session, E_VAL_22);

                        break;
                    case MAX_VALUE: case MIN_VALUE:
                        minMaxValue = (Long) attributes.get("value");
                        message = errorCode.getFullMsg(session.getMessageHandler(), fieldValue, fieldName, minMaxValue);
                        break;
                }
                formErrors.add(message);
            }

            throw new BusinessException(session, this);
        }
        logger.info(session, I_VAL_2, form.getForm());
    }
    public FormValidation(String mainError, List<String> formErrors) {
        this.mainError = mainError;
        this.formErrors = formErrors;
    }

    public String getMainError() {
        return mainError;
    }
    public void setMainError(String mainError) {
        this.mainError = mainError;
    }

    public List<String> getFormErrors() {
        return formErrors;
    }
    public void setFormErrors(List<String> formErrors) {
        this.formErrors = formErrors;
    }

    public AMCode getCode() {
        return code;
    }
    public void setCode(AMCode code) {
        this.code = code;
    }

    public String getFormName() {
        return formName;
    }
    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getErrorList() {
        String errorList = "";
        for (String error : this.formErrors)
            errorList += "\t" + error + "\n";
        return errorList.substring(0, errorList.length()-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormValidation)) return false;

        FormValidation<?> that = (FormValidation<?>) o;

        if (getCode() != that.getCode()) return false;
        if (getMainError() != null ? !getMainError().equals(that.getMainError()) : that.getMainError() != null) return false;
        if (getFormName() != null ? !getFormName().equals(that.getFormName()) : that.getFormName() != null) return false;
        return getFormErrors() != null ? getFormErrors().equals(that.getFormErrors()) : that.getFormErrors() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getMainError() != null ? getMainError().hashCode() : 0);
        result = 31 * result + (getFormErrors() != null ? getFormErrors().hashCode() : 0);
        result = 31 * result + (getFormName() != null ? getFormName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormValidation{" +
                "code = " + code +
                ", mainError = " + mainError +
                ", formName = " + formName +
                ", formErrors = " + formErrors +
                "}\n";
    }
}
