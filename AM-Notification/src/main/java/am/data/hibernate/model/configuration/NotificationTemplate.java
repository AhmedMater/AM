package am.data.hibernate.model.configuration;

import am.data.hibernate.model.lookup.NotificationType;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "notification_template")
public class NotificationTemplate {
    @Id
    @Column(name = "notif_temp_id")
    private String templateID;

    @ManyToOne
    @JoinColumn(name = "event_notif_id", referencedColumnName = "event_notif_id")
    private EventNotification eventNotification;

    @ManyToOne
    @JoinColumn(name = "notification_type", referencedColumnName = "type")
    private NotificationType type;

    @Basic
    @Column(name = "template_name")
    private String templateName;

    @Basic
    @Column(name = "subject")
    private String subject;

    @Basic
    @Column(name = "num_of_placeholders")
    private Integer numOfPlaceHolders;

    public NotificationTemplate() {
    }
    public NotificationTemplate(String templateID, EventNotification eventNotification, NotificationType type, String templateName, Integer numOfPlaceHolders) {
        this.templateID = templateID;
        this.eventNotification = eventNotification;
        this.type = type;
        this.templateName = templateName;
        this.numOfPlaceHolders = numOfPlaceHolders;
    }

    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getNumOfPlaceHolders() {
        return numOfPlaceHolders;
    }
    public void setNumOfPlaceHolders(Integer numOfPlaceHolders) {
        this.numOfPlaceHolders = numOfPlaceHolders;
    }

    public String getTemplateID() {
        return templateID;
    }
    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public EventNotification getEventNotification() {
        return eventNotification;
    }
    public void setEventNotification(EventNotification eventNotification) {
        this.eventNotification = eventNotification;
    }

    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTemplate)) return false;

        NotificationTemplate that = (NotificationTemplate) o;

        return getTemplateID() != null ? getTemplateID().equals(that.getTemplateID()) : that.getTemplateID() == null;
    }

    @Override
    public int hashCode() {
        return getTemplateID() != null ? getTemplateID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotificationTemplate{" +
                "templateID = " + templateID +
                ", eventNotification = " + eventNotification +
                ", type = " + type +
                ", templateName = " + templateName +
                ", numOfPlaceHolders = " + numOfPlaceHolders +
                "}\n";
    }
}
