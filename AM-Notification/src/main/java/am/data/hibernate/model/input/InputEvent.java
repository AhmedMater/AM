package am.data.hibernate.model.input;

import am.main.api.validation.groups.*;
import am.main.common.RegExp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ahmed.motair on 1/15/2018.
 */
@Entity
@Table(name = "input_event")
public class InputEvent implements Serializable{
    private static final Map<String, String> FIELDS = Collections.unmodifiableMap(
        new HashMap<String, String>(){{
            put("eventName", "Event Name");
            put("categoryRelatedID", "Category Related ID");
        }}
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventID;

    @Basic
    @Column(name = "jms_id")
    private String jmsID;

    @Basic
    @Column(name = "event_name")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 10, max = 10, message = FormValidation.EQ_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.CONTENT_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String eventName;

    @Basic
    @Column(name = "category_related_id")
    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @Length(min = 1, max = 50, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.CONTENT_NAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String categoryRelatedID;

    @Basic
    @Column(name = "receive_date")
    private Date receiveDate;

    @Basic
    @Column(name = "is_processed")
    private Boolean processed;

    @Basic
    @Column(name = "process_date")
    private Date processDate;

    @Basic
    @Column(name = "failure_reason")
    private String failureReason;

    @Basic
    @Column(name = "num_of_trails")
    private Integer numOfTrails;

    @Basic
    @Column(name = "is_valid")
    private Boolean valid;

    @Basic
    @Column(name = "validation_date")
    private Date validationDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<InputParameter> parameters;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Set<InputDestination> destinations;

    public InputEvent() {
        this.numOfTrails = 1;
        this.receiveDate = new Date();
        this.processed = false;
        this.parameters = new HashSet<>();
        this.destinations = new HashSet<>();
    }
    public InputEvent(String jmsID, String eventName, String categoryRelatedID, Date receiveDate, Date processDate, Boolean processed, String failureReason, Integer numOfTrails) {
        this.jmsID = jmsID;
        this.eventName = eventName;
        this.categoryRelatedID = categoryRelatedID;
        this.receiveDate = receiveDate;
        this.processDate = processDate;
        this.processed = processed;
        this.failureReason = failureReason;
        this.numOfTrails = numOfTrails;
    }

    public Integer getEventID() {
        return eventID;
    }
    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getJmsID() {
        return jmsID;
    }
    public void setJmsID(String jmsID) {
        this.jmsID = jmsID;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public Date getProcessDate() {
        return processDate;
    }
    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Boolean getProcessed() {
        return processed;
    }
    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Integer getNumOfTrails() {
        return numOfTrails;
    }
    public void setNumOfTrails(Integer numOfTrails) {
        this.numOfTrails = numOfTrails;
    }

    public Boolean getValid() {
        return valid;
    }
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getValidationDate() {
        return validationDate;
    }
    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public Set<InputParameter> getParameters() {
        return parameters;
    }
    public void setParameters(Set<InputParameter> parameters) {
        this.parameters = parameters;
    }
    public void addParameters(InputParameter parameter) {
        if(this.parameters == null)
            this.parameters = new HashSet<>();
        this.parameters.add(parameter);
    }

    public Set<InputDestination> getDestinations() {
        return destinations;
    }
    public void setDestinations(Set<InputDestination> destinations) {
        this.destinations = destinations;
    }
    public void addDestinations(InputDestination destination) {
        if(this.destinations == null)
            this.destinations = new HashSet<>();
        this.destinations.add(destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputEvent)) return false;

        InputEvent that = (InputEvent) o;

        return getEventID() != null ? getEventID().equals(that.getEventID()) : that.getEventID() == null;
    }

    @Override
    public int hashCode() {
        return getEventID() != null ? getEventID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InputEvent{" +
                "eventID = " + eventID +
                ", jmsID = " + jmsID +
                ", eventName = " + eventName +
                ", categoryRelatedID = " + categoryRelatedID +
                ", receiveDate = " + receiveDate +
                ", processDate = " + processDate +
                ", processed = " + processed +
                ", failureReason = " + failureReason +
                ", numOfTrails = " + numOfTrails +
                ", parameters = " + parameters +
                ", destinations = " + destinations +
                "}\n";
    }
}
