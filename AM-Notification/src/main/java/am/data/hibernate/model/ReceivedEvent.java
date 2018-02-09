package am.data.hibernate.model;

import am.data.hibernate.model.apps.RegisteredApplication;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 2/6/2018.
 */
@Entity
@Table(name = "received_event")
public class ReceivedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "received_event_id")
    private Integer receivedEventID;

    @Basic
    @Column(name = "received_date")
    private Date receiveDate;

    @Basic
    @Column(name = "valid")
    private Boolean valid;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private RegisteredApplication application;

    @Basic
    @Column(name = "event_content")
    private String eventContent;

    public ReceivedEvent() {
    }
    public ReceivedEvent(RegisteredApplication application, String eventContent) {
        this.receiveDate = new Date();
        this.application = application;
        this.eventContent = eventContent;
    }

    public Integer getReceivedEventID() {
        return receivedEventID;
    }

    public void setReceivedEventID(Integer receivedEventID) {
        this.receivedEventID = receivedEventID;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public RegisteredApplication getApplication() {
        return application;
    }

    public void setApplication(RegisteredApplication application) {
        this.application = application;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceivedEvent)) return false;

        ReceivedEvent that = (ReceivedEvent) o;

        return getReceivedEventID() != null ? getReceivedEventID().equals(that.getReceivedEventID()) : that.getReceivedEventID() == null;
    }

    @Override
    public int hashCode() {
        return getReceivedEventID() != null ? getReceivedEventID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReceivedEvent{" +
                "receivedEventID = " + receivedEventID +
                ", receiveDate = " + receiveDate +
                ", valid = " + valid +
                ", application = " + application +
                ", eventContent = " + eventContent +
                "}\n";
    }
}
