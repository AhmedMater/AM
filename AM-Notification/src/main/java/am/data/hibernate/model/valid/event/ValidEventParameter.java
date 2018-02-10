package am.data.hibernate.model.valid.event;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 2/4/2018.
 */
@Entity
@Table(name = "valid_event_parameter")
public class ValidEventParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Integer paramID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valid_event_id", referencedColumnName = "valid_event_id")
    private ValidEvent event;

    @Basic
    @Column(name = "key")
    private String key;

    @Basic
    @Column(name = "value")
    private String value;

    public ValidEventParameter() {
    }
    public ValidEventParameter(ValidEvent event, String key, String value) {
        this.event = event;
        this.key = key;
        this.value = value;
    }

    public Integer getParamID() {
        return paramID;
    }
    public void setParamID(Integer paramID) {
        this.paramID = paramID;
    }

    public ValidEvent getEvent() {
        return event;
    }
    public void setEvent(ValidEvent event) {
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
        if (!(o instanceof ValidEventParameter)) return false;

        ValidEventParameter that = (ValidEventParameter) o;

        return getParamID() != null ? getParamID().equals(that.getParamID()) : that.getParamID() == null;
    }

    @Override
    public int hashCode() {
        return getParamID() != null ? getParamID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ValidEventParameter{" +
                "paramID = " + paramID +
                ", event = " + event +
                ", key = " + key +
                ", value = " + value +
                "}\n";
    }
}
