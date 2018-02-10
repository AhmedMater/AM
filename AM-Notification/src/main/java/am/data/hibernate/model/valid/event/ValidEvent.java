package am.data.hibernate.model.valid.event;

import am.data.hibernate.model.configuration.Event;
import am.data.hibernate.model.apps.RegisteredApplication;
import am.data.hibernate.model.lookup.Category;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by ahmed.motair on 2/4/2018.
 */
@Entity
@Table(name = "valid_event")
public class ValidEvent {
    @Id
    @Column(name = "valid_event_id")
    private String validEventID;

    @Basic
    @Column(name = "app_notification_id")
    private String appNotificationID;

    @Basic
    @Column(name = "jms_id")
    private String jmsID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private RegisteredApplication application;

    @Basic
    @Column(name = "category_related_id")
    private String categoryRelatedID;

    @Basic
    @Column(name = "receive_date")
    private Date receiveDate;

    @Basic
    @Column(name = "validation_date")
    private Date validationDate;

    @Basic
    @Column(name = "process_date")
    private Date processDate;

    @Basic
    @Column(name = "completion_date")
    private Date completionDate;

    @Basic
    @Column(name = "is_completed")
    private Boolean completed;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<ValidEventDestination> destinationSet;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<ValidEventParameter> parameterSet;

    @Basic
    @Column(name = "is_quarantined")
    private Boolean quarantined;
    
    @PrePersist
    public void prePersist(){
        for (ValidEventDestination destination :destinationSet)
            destination.setEvent(this);

        for (ValidEventParameter parameter :parameterSet)
            parameter.setEvent(this);
    }

    public ValidEvent() {
    }

    public void generateValidEventID(Integer eventNum) {
        String format = "{0}-{1}-{2}-{3}";
        String num = StringUtils.repeat("0", 6 - eventNum.toString().length()) + eventNum;
        String id = MessageFormat.format(format, application.getAppID(), event.getEventID(),
            new SimpleDateFormat("yyyyMMdd").format(new Date()), num);
        setValidEventID(id);
    }
    public String getValidEventID() {
        return validEventID;
    }
    public void setValidEventID(String validEventID) {
        this.validEventID = validEventID;
    }

    public String getAppNotificationID() {
        return appNotificationID;
    }
    public void setAppNotificationID(String appNotificationID) {
        this.appNotificationID = appNotificationID;
    }

    public String getJmsID() {
        return jmsID;
    }
    public void setJmsID(String jmsID) {
        this.jmsID = jmsID;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public RegisteredApplication getApplication() {
        return application;
    }
    public void setApplication(RegisteredApplication application) {
        this.application = application;
    }

    public String getCategoryRelatedID() {
        return categoryRelatedID;
    }
    public void setCategoryRelatedID(String categoryRelatedID) {
        this.categoryRelatedID = categoryRelatedID;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getValidationDate() {
        return validationDate;
    }
    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public Date getProcessDate() {
        return processDate;
    }
    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Set<ValidEventDestination> getDestinationSet() {
        return destinationSet;
    }
    public void setDestinationSet(Set<ValidEventDestination> destinationSet) {
        this.destinationSet = destinationSet;
    }

    public Set<ValidEventParameter> getParameterSet() {
        return parameterSet;
    }
    public void setParameterSet(Set<ValidEventParameter> parameterSet) {
        this.parameterSet = parameterSet;
    }

    public Boolean isQuarantined() {
        return quarantined;
    }
    public void setQuarantined(Boolean quarantined) {
        this.quarantined = quarantined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidEvent)) return false;

        ValidEvent that = (ValidEvent) o;

        return getValidEventID() != null ? getValidEventID().equals(that.getValidEventID()) : that.getValidEventID() == null;
    }

    @Override
    public int hashCode() {
        return getValidEventID() != null ? getValidEventID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ValidEvent{" +
                "validEventID = " + validEventID +
                ", appNotificationID = " + appNotificationID +
                ", jmsID = " + jmsID +
                ", event = " + event +
                ", category = " + category +
                ", application = " + application +
                ", categoryRelatedID = " + categoryRelatedID +
                ", receiveDate = " + receiveDate +
                ", validationDate = " + validationDate +
                ", processDate = " + processDate +
                ", completionDate = " + completionDate +
                ", completed = " + completed +
                "}\n";
    }

    public Integer getNotificationNumber() {
        int counter = 0;

        for (ValidEventDestination destination : destinationSet) {
            if(destination.getUserID() != null)
                counter++;
            if(destination.getPhone() != null)
                counter++;
            if(destination.getEmail() != null)
                counter++;
        }
        return counter;
    }
}
