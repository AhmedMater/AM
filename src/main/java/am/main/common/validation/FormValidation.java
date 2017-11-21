package am.main.common.validation;

import am.main.exception.BusinessException;
import am.main.session.AppSession;
import am.shared.enums.EC;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
public class FormValidation<T> implements Serializable{
    private EC code;
    private String mainError;
    private List<String> formErrors;
    private String errorList = "\n";
    private boolean valid;

    public FormValidation() {
    }
    public FormValidation(String mainError, String ... formErrors) {
        this.mainError = mainError;
        this.formErrors = Arrays.asList(formErrors);

    }
    public FormValidation(AppSession session, EC code, T object) throws BusinessException {
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validator.validate(object, am.main.common.validation.groups.FormValidation.class);

        if(errors.size() > 0) {
            this.valid = false;
            this.code = code;
            this.mainError = session.getErrorMsg(code);

            this.formErrors = new ArrayList<>();
            for (ConstraintViolation<T> error : errors) {
                errorList += error.getMessage() + "\n";
                formErrors.add(error.getMessage());
            }

            throw new BusinessException(session, this);
        }else
            this.valid = true;
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

    public String getErrorList() {
        return errorList;
    }
    public void setErrorList(String errorList) {
        this.errorList = errorList;
    }

    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormValidation)) return false;

        FormValidation that = (FormValidation) o;

        if (getMainError() != null ? !getMainError().equals(that.getMainError()) : that.getMainError() != null) return false;
        return getFormErrors() != null ? getFormErrors().equals(that.getFormErrors()) : that.getFormErrors() == null;
    }

    @Override
    public int hashCode() {
        int result = getMainError() != null ? getMainError().hashCode() : 0;
        result = 31 * result + (getFormErrors() != null ? getFormErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormValidation{" +
                "mainError = " + mainError +
                ", formErrors = " + formErrors +
                "}\n";
    }
}
