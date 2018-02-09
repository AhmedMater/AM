package am.data.hibernate.model.configuration;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event_notification")
public class EventNotification {
    public static final String EVENT_NTF_ID = "eventNtfID";
    public static final String EVENT_ID = "event." + Event.EVENT_ID;

    @Id
    @Column(name = "event_ntf_id")
    private String eventNtfID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @Basic
    @Column(name = "notification")
    private String notification;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id", referencedColumnName = "event_ntf_id")
    private Set<Template> templates;

    public EventNotification() {
    }
    public EventNotification(String eventNtfID, Event event, String notification, String description, Set<Template> templates) {
        this.eventNtfID = eventNtfID;
        this.event = event;
        this.notification = notification;
        this.description = description;
        this.templates = templates;
    }

    public String getEventNtfID() {
        return eventNtfID;
    }
    public void setEventNtfID(String eventNtfID) {
        this.eventNtfID = eventNtfID;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
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

    public Set<Template> getTemplates() {
        return templates;
    }
    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventNotification)) return false;

        EventNotification that = (EventNotification) o;

        return getEventNtfID() != null ? getEventNtfID().equals(that.getEventNtfID()) : that.getEventNtfID() == null;
    }

    @Override
    public int hashCode() {
        return getEventNtfID() != null ? getEventNtfID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventNotification{" +
                "eventNtfID = " + eventNtfID +
                ", event = " + event +
                ", notification = " + notification +
                ", description = " + description +
                ", templates = " + templates +
                "}\n";
    }
}
