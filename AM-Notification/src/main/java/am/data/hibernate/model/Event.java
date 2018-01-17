package am.data.hibernate.model;

import am.data.hibernate.model.configuration.EventType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventID;

    @ManyToOne
    @JoinColumn(name = "event_type", referencedColumnName = "type")
    private EventType type;

    @Basic
    @Column(name = "category_related_id")
    private String categoryRelatedID;

    @Basic
    @Column(name = "creation_date")
    private Date creationDate;

    @Basic
    @Column(name = "completion_date")
    private Date completionDate;

    @Basic
    @Column(name = "is_completed")
    private Boolean completed;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<Notification> notifications;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<EventParameter> parameters;

    public Event() {
        this.completed = false;
        this.creationDate = new Date();
        this.notifications = new HashSet<>();
        this.parameters = new HashSet<>();
    }
    public Event(EventType type, String categoryRelatedID, Date creationDate, Date completionDate, Boolean completed) {
        this.type = type;
        this.categoryRelatedID = categoryRelatedID;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.completed = completed;
    }

    public Integer getEventID() {
        return eventID;
    }
    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public EventType getType() {
        return type;
    }
    public void setType(EventType type) {
        this.type = type;
    }

    public String getCategoryRelatedID() {
        return categoryRelatedID;
    }
    public void setCategoryRelatedID(String categoryRelatedID) {
        this.categoryRelatedID = categoryRelatedID;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }
    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }
    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<EventParameter> getParameters() {
        return parameters;
    }
    public void setParameters(Set<EventParameter> parameters) {
        this.parameters = parameters;
    }
    public void addParameters(EventParameter parameter) {
        this.parameters.add(parameter);
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
        return "Event{" +
                "eventID = " + eventID +
                ", type = " + type +
                ", categoryRelatedID = " + categoryRelatedID +
                ", creationDate = " + creationDate +
                ", completionDate = " + completionDate +
                ", completed = " + completed +
                "}\n";
    }
}
