package am.main.data.dto.notification;

import am.main.api.validation.groups.BlankValidation;
import am.main.api.validation.groups.FormValidation;
import am.main.api.validation.groups.LengthValidation;
import am.main.api.validation.groups.RequiredValidation;
import am.main.data.dto.LoginData;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmed.motair on 1/31/2018.
 */
public class AMNotificationData implements Serializable{
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    private LoginData loginData;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(max = 30, message = FormValidation.MAX_LENGTH, groups = LengthValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String notificationID;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 5, max = 5, message = FormValidation.EQ_LENGTH, groups = LengthValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String eventID;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 3, max = 3, message = FormValidation.EQ_LENGTH, groups = LengthValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String category;

    @Length(max = 30, message = FormValidation.MAX_LENGTH, groups = LengthValidation.class)
    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String categoryRelatedID;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Size(min = 1, message = FormValidation.MIN_LENGTH, groups = LengthValidation.class)
    private List<AMDestination> destinations;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Size(min = 1, message = FormValidation.MIN_LENGTH, groups = LengthValidation.class)
    private Map<String, String> parameters;

    public AMNotificationData() {
    }

    public String getNotificationID() {
        return notificationID;
    }
    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getEventID() {
        return eventID;
    }
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public LoginData getLoginData() {
        return loginData;
    }
    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryRelatedID() {
        return categoryRelatedID;
    }
    public void setCategoryRelatedID(String categoryRelatedID) {
        this.categoryRelatedID = categoryRelatedID;
    }

    public List<AMDestination> getDestinations() {
        return destinations;
    }
    public void setDestinations(List<AMDestination> destinations) {
        this.destinations = destinations;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
