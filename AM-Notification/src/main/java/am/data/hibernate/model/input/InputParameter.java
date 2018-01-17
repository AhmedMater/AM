package am.data.hibernate.model.input;

import am.main.api.validation.groups.BlankValidation;
import am.main.api.validation.groups.FormValidation;
import am.main.api.validation.groups.LengthValidation;
import am.main.api.validation.groups.RequiredValidation;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
@Entity
@Table(name = "input_event_parameter")
public class InputParameter implements Serializable{
    private static final Map<String, String> FIELDS = Collections.unmodifiableMap(
            new HashMap<String, String>(){{
                put("key", "Parameter Key");
                put("value", "Email");
                put("phone", "Mobile Phone");
                put("userID", "User ID");
            }}
    );
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Integer paramID;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private InputEvent event;

    @Basic
    @Column(name = "key")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 50, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String key;

    @Basic
    @Column(name = "value")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 200, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String value;

    public InputParameter() {
    }
    public InputParameter(InputEvent event, String key, String value) {
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

    public InputEvent getEvent() {
        return event;
    }
    public void setEvent(InputEvent event) {
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
        if (!(o instanceof InputParameter)) return false;

        InputParameter that = (InputParameter) o;

        return getParamID() != null ? getParamID().equals(that.getParamID()) : that.getParamID() == null;
    }

    @Override
    public int hashCode() {
        return getParamID() != null ? getParamID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InputParameter{" +
                "paramID = " + paramID +
                ", event = " + event +
                ", key = " + key +
                ", value = " + value +
                "}\n";
    }
}
