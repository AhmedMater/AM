package am.data.hibernate.model.input;

import am.main.api.validation.groups.*;
import am.main.common.RegExp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
@Entity
@Table(name = "input_event_destination")
public class InputDestination implements Serializable{
    private static final Map<String, String> FIELDS = Collections.unmodifiableMap(
        new HashMap<String, String>(){{
            put("eventNotificationID", "Event Notification ID");
            put("email", "Email");
            put("phone", "Mobile Phone");
            put("userID", "User ID");
        }}
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer destinationID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private InputEvent event;

    @Basic
    @Column(name = "event_notification_id")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 10, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.CONTENT_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String eventNotificationID;

    @Basic
    @Column(name = "full_name")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 30, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.FULL_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String fullName;

    @Basic
    @Column(name = "email")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 60, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.EMAIL, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String email;

    @Basic
    @Column(name = "phone")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 14, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.INTERNATIONAL_PHONE_NUMBER, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String phone;

    @Basic
    @Column(name = "user_id")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 25, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.CONTENT_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String userID;

    public InputDestination() {
    }
    public InputDestination(InputEvent event, String eventNotificationID, String email, String phone, String userID) {
        this.event = event;
        this.eventNotificationID = eventNotificationID;
        this.email = email;
        this.phone = phone;
        this.userID = userID;
    }
    public InputDestination(InputEvent event, String eventNotificationID) {
        this.event = event;
        this.eventNotificationID = eventNotificationID;
    }

    public Integer getDestinationID() {
        return destinationID;
    }
    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public InputEvent getEvent() {
        return event;
    }
    public void setEvent(InputEvent event) {
        this.event = event;
    }

    public String getEventNotificationID() {
        return eventNotificationID;
    }
    public void setEventNotificationID(String eventNotificationID) {
        this.eventNotificationID = eventNotificationID;
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
        if (!(o instanceof InputDestination)) return false;

        InputDestination that = (InputDestination) o;

        return getDestinationID() != null ? getDestinationID().equals(that.getDestinationID()) : that.getDestinationID() == null;
    }

    @Override
    public int hashCode() {
        return getDestinationID() != null ? getDestinationID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InputDestination{" +
                "destinationID = " + destinationID +
                ", event = " + event +
                ", eventNotificationID = " + eventNotificationID +
                ", email = " + email +
                ", phone = " + phone +
                ", userID = " + userID +
                "}\n";
    }
}
