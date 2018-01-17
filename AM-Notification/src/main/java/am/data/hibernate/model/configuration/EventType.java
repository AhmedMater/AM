package am.data.hibernate.model.configuration;

import am.data.hibernate.model.lookup.EventCategory;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event_type")
public class EventType {
    @Id
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category")
    private EventCategory category;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type", referencedColumnName = "type")
    private Set<EventNotification> eventNotifications;

    public EventType() {
    }
    public EventType(String type) {
        this.type = type;
    }
    public EventType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public EventCategory getCategory() {
        return category;
    }
    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public Set<EventNotification> getEventNotifications() {
        return eventNotifications;
    }
    public void setEventNotifications(Set<EventNotification> eventNotifications) {
        this.eventNotifications = eventNotifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventType)) return false;

        EventType eventType1 = (EventType) o;

        return getType() != null ? getType().equals(eventType1.getType()) : eventType1.getType() == null;
    }

    @Override
    public int hashCode() {
        return getType() != null ? getType().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "type = " + type +
                ", description = " + description +
                ", eventNotifications = " + eventNotifications +
                "}\n";
    }
}
