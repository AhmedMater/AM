package am.data.hibernate.model;

import am.data.hibernate.model.lookup.NotificationType;
import am.data.hibernate.model.lookup.SystemAddress;
import am.data.hibernate.model.valid.event.ValidEvent;
import am.data.hibernate.model.valid.event.ValidEventDestination;
import am.data.hibernate.model.valid.event.ValidEventParameter;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable {
    @Id
    @Column(name = "notification_id")
    private String notificationID;

    @ManyToOne
    @JoinColumn(name = "valid_event_id", referencedColumnName = "valid_event_id")
    private ValidEvent validEvent;

    @Basic
    @Column(name = "message_subject")
    private String messageSubject;

    @Basic
    @Column(name = "message_body")
    private String messageBody;

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
    @Column(name = "creation_date")
    private Date creationDate;

    @Basic
    @Column(name = "is_sent")
    private Boolean sent;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "address_id")
    private SystemAddress sender;

    @Basic
    @Column(name = "user_id")
    private String userID;

    @ManyToOne
    @JoinColumn(name = "ntf_type", referencedColumnName = "ntf_type")
    private NotificationType notificationType;

    public Notification() {
    }
    public Notification(ValidEvent validEvent, NotificationType notificationType) {
        creationDate = new Date();
        sent = false;
        this.validEvent = validEvent;
        this.notificationType = notificationType;
        numOfTrails = 0;
    }

    public void generateNotificationID(Integer eventNum) {
        String format = "{0}-{1}-{2}";
        String num = StringUtils.repeat("0", 12 - eventNum.toString().length()) + eventNum;
        String id = MessageFormat.format(format, validEvent.getApplication().getAppID(),
                new SimpleDateFormat("yyyyMMdd").format(new Date()), num);
        setNotificationID(id);
    }

    public String getNotificationID() {
        return notificationID;
    }
    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public ValidEvent getValidEvent() {
        return validEvent;
    }
    public void setValidEvent(ValidEvent validEvent) {
        this.validEvent = validEvent;
    }

    public String getMessageSubject() {
        return messageSubject;
    }
    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getSent() {
        return sent;
    }
    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public SystemAddress getSender() {
        return sender;
    }
    public void setSender(SystemAddress sender) {
        this.sender = sender;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }


    //    public Notification(AppSession session, ValidEvent validEvent, Template template, ValidEventDestination destination) throws Exception {
//        this.validEvent = validEvent;
//        this.sent = false;
//        this.numOfTrails = 0;
//        this.fullName = destination.getFullName();
//
//        switch (NotificationTypes.getNotificationType(template.getType().getType())){
//            case EMAIL: this.address = destination.getEmail(); break;
//            case SMS: this.address = destination.getPhone(); break;
//            case WEB_NOTIFICATION: this.address = destination.getUserID(); break;
//            default:
//                throw new GeneralException(session, E_NOT_4, template.getType().getDescription());
//        }
//    }
//    public Notification(NotificationTemplate template, Event event, String fullMessage) {
//        this.template = template;
//        this.event = event;
//        this.fullMessage = fullMessage;
//    }
//
//    public Integer getNotificationID() {
//        return notificationID;
//    }
//    public void setNotificationID(Integer notificationID) {
//        this.notificationID = notificationID;
//    }
//
//    public NotificationTemplate getTemplate() {
//        return template;
//    }
//    public void setTemplate(NotificationTemplate template) {
//        this.template = template;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//
//    public String getFullMessage() {
//        return fullMessage;
//    }
//    public void setFullMessage(String fullMessage) {
//        this.fullMessage = fullMessage;
//    }
//    public String getAddress() {
//        return address;
//    }
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getFailureReason() {
//        return failureReason;
//    }
//    public void setFailureReason(String failureReason) {
//        this.failureReason = failureReason;
//    }
//
//    public Integer getNumOfTrails() {
//        return numOfTrails;
//    }
//    public void setNumOfTrails(Integer numOfTrails) {
//        this.numOfTrails = numOfTrails;
//    }

//
//    public Date getSentDate() {
//        return sentDate;
//    }
//    public void setSentDate(Date sentDate) {
//        this.sentDate = sentDate;
//    }
//
//    public SystemAddress getSender() {
//        return sender;
//    }
//    public void setSender(SystemAddress sender) {
//        this.sender = sender;
//    }
//
//    public Boolean getSent() {
//        return sent;
//    }
//    public void setSent(Boolean sent) {
//        this.sent = sent;
//    }

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
        return "Notification{" +
                "notificationID = " + notificationID +
                ", validEvent = " + validEvent +
                ", messageSubject = " + messageSubject +
                ", messageBody = " + messageBody +
                ", address = " + address +
                ", failureReason = " + failureReason +
                ", numOfTrails = " + numOfTrails +
                ", sentDate = " + sentDate +
                ", creationDate = " + creationDate +
                ", sent = " + sent +
                ", sender = " + sender +
                ", notificationType = " + notificationType +
                "}\n";
    }
}
