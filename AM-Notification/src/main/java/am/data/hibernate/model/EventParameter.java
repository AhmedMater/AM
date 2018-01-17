package am.data.hibernate.model;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name = "event_parameter")
public class EventParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Integer paramID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @Basic
    @Column(name = "key")
    private String key;

    @Basic
    @Column(name = "value")
    private String value;

    public EventParameter(Event event) {
        this.event = event;
    }
    public EventParameter(Event event, String key, String value) {
        this.event = event;
        this.key = key;
        this.value = value;
    }
    public EventParameter() {

    }

    public Integer getParamID() {
        return paramID;
    }
    public void setParamID(Integer paramID) {
        this.paramID = paramID;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventParameter)) return false;

        EventParameter that = (EventParameter) o;

        return getParamID() != null ? getParamID().equals(that.getParamID()) : that.getParamID() == null;
    }

    @Override
    public int hashCode() {
        return getParamID() != null ? getParamID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventParameter{" +
                "paramID = " + paramID +
                ", event = " + event +
                ", key = " + key +
                ", value = " + value +
                "}\n";
    }
}
