package am.main.data.dto.notification;

import am.main.api.validation.groups.*;
import am.main.common.RegExp;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
public class AMDestination implements Serializable{
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Size(min = 1, message = FormValidation.MIN_LENGTH, groups = LengthValidation.class)
    private List<String> eventNtfIDs;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 10, max = 60, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.EMAIL, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String email;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 11, max = 14, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.PHONE_NUMBER, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String phone;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 5, max = 30, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.FULL_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String fullName;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 30, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String userID;

    public AMDestination() {
    }
    public AMDestination(String userID, String fullName, String email, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
    public AMDestination(List<String> eventNtfIDs, String email, String phone, String fullName, String userID) {
        this.eventNtfIDs = eventNtfIDs;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.userID = userID;
    }

    public List<String> getEventNtfIDs() {
        return eventNtfIDs;
    }
    public void setEventNtfIDs(List<String> eventNtfIDs) {
        this.eventNtfIDs = eventNtfIDs;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMDestination)) return false;

        AMDestination that = (AMDestination) o;

        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
        return getUserID() != null ? getUserID().equals(that.getUserID()) : that.getUserID() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getUserID() != null ? getUserID().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AMDestination{" +
                "email = " + email +
                ", phone = " + phone +
                ", userID = " + userID +
                "}\n";
    }
}
