package am.data.hibernate.model.configuration;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 2/4/2018.
 */
@Entity
@Table(name = "event_parameter")
public class EventParameter {
    public static final String PARAMETER = "parameter";
    public static final String EVENT_ID = "event." + Event.EVENT_ID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Integer parameterID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @Basic
    @Column(name = "parameter")
    private String parameter;

    public EventParameter() {
    }
    public EventParameter(Event event, String parameter) {
        this.event = event;
        this.parameter = parameter;
    }

    public Integer getParameterID() {
        return parameterID;
    }
    public void setParameterID(Integer parameterID) {
        this.parameterID = parameterID;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventParameter)) return false;

        EventParameter that = (EventParameter) o;

        return getParameterID() != null ? getParameterID().equals(that.getParameterID()) : that.getParameterID() == null;
    }

    @Override
    public int hashCode() {
        return getParameterID() != null ? getParameterID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventParameter{" +
                "parameterID = " + parameterID +
                ", event = " + event +
                ", parameter = " + parameter +
                "}\n";
    }
}
