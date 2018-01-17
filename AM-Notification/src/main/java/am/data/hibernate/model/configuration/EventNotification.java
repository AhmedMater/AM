package am.data.hibernate.model.configuration;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event_notification")
public class EventNotification {
    @Id
    @Column(name = "event_notif_id")
    private String eventNotificationID;

    @ManyToOne
    @JoinColumn(name = "event_type", referencedColumnName = "type")
    private EventType type;

    @Basic
    @Column(name = "notification")
    private String notification;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_notif_id", referencedColumnName = "event_notif_id")
    private Set<NotificationTemplate> notificationTemplates;

    public EventNotification() {
    }
    public EventNotification(String eventNotificationID, EventType type, String notification, String description) {
        this.eventNotificationID = eventNotificationID;
        this.type = type;
        this.notification = notification;
        this.description = description;
    }

    public String getEventNotificationID() {
        return eventNotificationID;
    }
    public void setEventNotificationID(String eventNotificationID) {
        this.eventNotificationID = eventNotificationID;
    }

    public EventType getType() {
        return type;
    }
    public void setType(EventType type) {
        this.type = type;
    }

    public String getNotification() {
        return notification;
    }
    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<NotificationTemplate> getNotificationTemplates() {
        return notificationTemplates;
    }
    public void setNotificationTemplates(Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventNotification)) return false;

        EventNotification that = (EventNotification) o;

        return getEventNotificationID() != null ? getEventNotificationID().equals(that.getEventNotificationID()) : that.getEventNotificationID() == null;
    }

    @Override
    public int hashCode() {
        return getEventNotificationID() != null ? getEventNotificationID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventNotification{" +
                "eventNotificationID = " + eventNotificationID +
                ", type = " + type +
                ", notification = " + notification +
                ", description = " + description +
                "}\n";
    }
}
