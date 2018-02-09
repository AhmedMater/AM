package am.data.hibernate.model.valid.event;

import am.data.hibernate.model.configuration.EventNotification;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 2/4/2018.
 */
@Entity
@Table(name = "valid_event_destination")
public class ValidEventDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer destinationID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valid_event_id", referencedColumnName = "valid_event_id")
    private ValidEvent event;

    @ManyToOne
    @JoinColumn(name = "event_ntf_id", referencedColumnName = "event_ntf_id")
    private EventNotification eventNotification;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "user_id")
    private String userID;

    public ValidEventDestination() {
    }

    public ValidEventDestination(String fullName, String email, String phone, String userID) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.userID = userID;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public ValidEvent getEvent() {
        return event;
    }

    public void setEvent(ValidEvent event) {
        this.event = event;
    }

    public EventNotification getEventNotification() {
        return eventNotification;
    }

    public void setEventNotification(EventNotification eventNotification) {
        this.eventNotification = eventNotification;
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
        if (!(o instanceof ValidEventDestination)) return false;

        ValidEventDestination that = (ValidEventDestination) o;

        return getDestinationID() != null ? getDestinationID().equals(that.getDestinationID()) : that.getDestinationID() == null;
    }

    @Override
    public int hashCode() {
        return getDestinationID() != null ? getDestinationID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ValidEventDestination{" +
                "destinationID = " + destinationID +
                ", event = " + event +
                ", eventNotification = " + eventNotification +
                ", fullName = " + fullName +
                ", email = " + email +
                ", phone = " + phone +
                ", userID = " + userID +
                "}\n";
    }
}
