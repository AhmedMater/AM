package am.data.hibernate.model.valid.event;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ahmed.motair on 2/9/2018.
 */
@Entity
@Table(name = "event_ntf_start")
public class EventNtfStart implements Serializable{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valid_event_id", referencedColumnName = "valid_event_id")
    private ValidEvent event;

    @Basic
    @Column(name = "start_ntf_id")
    private Integer startNtfID;

    public EventNtfStart() {
    }
    public EventNtfStart(ValidEvent event, Integer startNtfID) {
        this.event = event;
        this.startNtfID = startNtfID;
    }

    public ValidEvent getEvent() {
        return event;
    }
    public void setEvent(ValidEvent event) {
        this.event = event;
    }

    public Integer getStartNtfID() {
        return startNtfID;
    }
    public void setStartNtfID(Integer startNtfID) {
        this.startNtfID = startNtfID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventNtfStart)) return false;

        EventNtfStart that = (EventNtfStart) o;

        return getEvent() != null ? getEvent().equals(that.getEvent()) : that.getEvent() == null;
    }

    @Override
    public int hashCode() {
        return getEvent() != null ? getEvent().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventNtfStart{" +
                "event = " + event +
                ", startNtfID = " + startNtfID +
                "}\n";
    }
}
