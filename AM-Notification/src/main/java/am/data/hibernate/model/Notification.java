package am.data.hibernate.model;

import am.data.hibernate.model.configuration.NotificationTemplate;
import am.data.hibernate.model.input.InputDestination;
import am.data.hibernate.model.lookup.SystemAddress;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.notification.NotificationTypes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationID;

    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "notif_temp_id")
    private NotificationTemplate template;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @Basic
    @Column(name = "full_message")
    private String fullMessage;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "destination_address")
    private String address;

    @Basic
    @Column(name = "failure_reason")
    private String failureReason;

    @Basic
    @Column(name = "num_of_trails")
    private Integer numOfTrails;

    @Basic
    @Column(name = "sent_date")
    private Date sentDate;

    @Basic
    @Column(name = "is_sent")
    private Boolean sent;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "address_id")
    private SystemAddress sender;

    public Notification() {
    }
    public Notification(AppSession session, Event event, NotificationTemplate template, InputDestination destination) throws Exception {
        this.event = event;
        this.template = template;
        this.sent = false;
        this.numOfTrails = 0;
        this.fullName = destination.getFullName();

        switch (NotificationTypes.getNotificationType(template.getType().getType())){
            case EMAIL: this.address = destination.getEmail(); break;
            case SMS: this.address = destination.getPhone(); break;
            case WEB_NOTIFICATION: this.address = destination.getUserID(); break;
            default:
                throw new GeneralException(session, EC.AMT_0077, template.getType().getDescription());
        }
    }
    public Notification(NotificationTemplate template, Event event, String fullMessage) {
        this.template = template;
        this.event = event;
        this.fullMessage = fullMessage;
    }

    public Integer getNotificationID() {
        return notificationID;
    }
    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }
    public void setTemplate(NotificationTemplate template) {
        this.template = template;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public String getFullMessage() {
        return fullMessage;
    }
    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Integer getNumOfTrails() {
        return numOfTrails;
    }
    public void setNumOfTrails(Integer numOfTrails) {
        this.numOfTrails = numOfTrails;
    }
    public void incrementTrails() {
        this.numOfTrails++;
    }

    public Date getSentDate() {
        return sentDate;
    }
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public SystemAddress getSender() {
        return sender;
    }
    public void setSender(SystemAddress sender) {
        this.sender = sender;
    }

    public Boolean getSent() {
        return sent;
    }
    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;

        Notification that = (Notification) o;

        return notificationID != null ? notificationID.equals(that.notificationID) : that.notificationID == null;
    }

    @Override
    public int hashCode() {
        return notificationID != null ? notificationID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AMNotification{" +
                "notificationID = " + notificationID +
                ", template = " + template +
                ", event = " + event +
                ", fullMessage = " + fullMessage +
                "}\n";
    }

}
