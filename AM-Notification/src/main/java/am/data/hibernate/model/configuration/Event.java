package am.data.hibernate.model.configuration;

import am.data.hibernate.model.lookup.Category;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event")
public class Event {
    public static final String EVENT_ID = "eventID";

    @Id
    @Column(name = "event_id")
    private String eventID;

    @Basic
    @Column(name = "event_type")
    private String eventType;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<EventNotification> eventNotifications;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<EventParameter> eventParameters;

    public Event() {
    }
    public Event(String eventType, String description, Category category, Set<EventNotification> eventNotifications) {
        this.eventType = eventType;
        this.description = description;
        this.category = category;
        this.eventNotifications = eventNotifications;
    }

    public String getEventID() {
        return eventID;
    }
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EventNotification> getEventNotifications() {
        return eventNotifications;
    }
    public void setEventNotifications(Set<EventNotification> eventNotifications) {
        this.eventNotifications = eventNotifications;
    }

    public Set<EventParameter> getEventParameters() {
        return eventParameters;
    }
    public void setEventParameters(Set<EventParameter> eventParameters) {
        this.eventParameters = eventParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        return getEventID() != null ? getEventID().equals(event.getEventID()) : event.getEventID() == null;
    }

    @Override
    public int hashCode() {
        return getEventID() != null ? getEventID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return (description != null) ? description : eventID;
    }
}
