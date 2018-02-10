package am.data.hibernate.model.configuration;

import am.data.hibernate.model.lookup.NotificationType;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Integer templateID;

    @ManyToOne
    @JoinColumn(name = "event_ntf_id", referencedColumnName = "event_ntf_id")
    private EventNotification eventNotification;

    @ManyToOne
    @JoinColumn(name = "ntf_type", referencedColumnName = "ntf_type")
    private NotificationType type;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Basic
    @Column(name = "template_subject")
    private String subject;

    public Template() {
    }
    public Template(EventNotification eventNotification, NotificationType type, String fileName, String subject) {
        this.eventNotification = eventNotification;
        this.type = type;
        this.fileName = fileName;
        this.subject = subject;
    }

    public Integer getTemplateID() {
        return templateID;
    }
    public void setTemplateID(Integer templateID) {
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

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;

        Template template = (Template) o;

        return getTemplateID() != null ? getTemplateID().equals(template.getTemplateID()) : template.getTemplateID() == null;
    }

    @Override
    public int hashCode() {
        return getTemplateID() != null ? getTemplateID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateID = " + templateID +
                ", eventNotification = " + eventNotification +
                ", type = " + type +
                ", fileName = " + fileName +
                ", subject = " + subject +
                "}\n";
    }
}
