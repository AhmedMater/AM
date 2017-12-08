package am.main.common.validation;

import am.main.exception.BusinessException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.Forms;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
public class FormValidation<T> implements Serializable{
    private EC code;
    private String mainError;
    private String formName;
    private List<String> formErrors;

    public FormValidation() {
    }
    public FormValidation(String mainError, String ... formErrors) {
        this.mainError = mainError;
        this.formErrors = Arrays.asList(formErrors);

    }
    public FormValidation(AppSession session, T object, EC code, Forms form) throws BusinessException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validator.validate(object, am.main.common.validation.groups.FormValidation.class);

        if(errors.size() > 0) {
            this.code = code;
            this.mainError = session.getErrorMsg(code, form);
            this.formName = form.getName();

            this.formErrors = new ArrayList<>();
            for (ConstraintViolation<T> error : errors) {
                EC errorCode = EC.valueOf(error.getMessage().replaceAll("-", "_"));
                String message = "";
                String fieldName = "";
                String fieldValue = "";

                if(!errorCode.equals(EC.AMT_0003))
                    fieldValue = error.getInvalidValue().toString();

                //Get the Field Name
                Path fieldPath = error.getPropertyPath();
                for (Path.Node node : fieldPath) {
                    if (!node.getName().isEmpty()) {
//                        fieldName = node.getName();//object.getClass().getField("FIELDS").;
                        try {
                            Method method = object.getClass().getMethod("getFIELDS");
                            Map<String, String> FIELDS = (Map<String, String>) method.invoke(object);
                            fieldName = FIELDS.get(node.getName());
                            break;
                        }catch (Exception ex){
                            throw new BusinessException(session, EC.AMT_0000);
                        }
                    }
                }

                if(fieldName.isEmpty())
                    throw new BusinessException(session, EC.AMT_0000);

                Map<String, Object> attributes = error.getConstraintDescriptor().getAttributes();
                Integer maxLength, minLength, eqLength;
                Long minMaxValue;

                switch (errorCode){
                    case AMT_0003: case AMT_0005:
                        message = session.getErrorMsg(errorCode, fieldName);
                        break;
                    case AMT_0004:
                        String regexError = RegExp.MESSAGES.get(attributes.get("regexp"));
                        message = session.getErrorMsg(errorCode, fieldValue, fieldName, regexError);
                        break;
                    case AMT_0012: case AMT_0013:
                        message = session.getErrorMsg(errorCode, fieldValue, fieldName);
                        break;
                    case AMT_0006:
                        maxLength = (Integer) attributes.get("max");
                        message = session.getErrorMsg(errorCode, fieldValue, fieldName, maxLength);
                        break;
                    case AMT_0007:
                        minLength = (Integer) attributes.get("min");
                        message = session.getErrorMsg(errorCode, fieldValue, fieldName, minLength);
                        break;
                    case AMT_0008: case AMT_0009:
                        maxLength = (Integer) attributes.get("max");
                        minLength = (Integer) attributes.get("min");

                        if(errorCode.equals(EC.AMT_0009) && maxLength.equals(minLength))
                            message = session.getErrorMsg(errorCode, fieldValue, fieldName, minLength);
                        else if(errorCode.equals(EC.AMT_0008) && !maxLength.equals(minLength))
                            message = session.getErrorMsg(errorCode, fieldValue, fieldName, minLength, maxLength);
                        else
                            throw new BusinessException(session, EC.AMT_0000);

                        break;
                    case AMT_0010: case AMT_0011:
                        minMaxValue = (Long) attributes.get("value");
                        message = session.getErrorMsg(errorCode, fieldValue, fieldName, minMaxValue);
                        break;
                }
                formErrors.add(message);
            }

            throw new BusinessException(session, this);
        }
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

    public EC getCode() {
        return code;
    }
    public void setCode(EC code) {
        this.code = code;
    }

    public String getFormName() {
        return formName;
    }
    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getErrorList() {
        String errorList = "\n";
        for (String error : this.formErrors)
            errorList += error + "\n";
        return errorList;
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
