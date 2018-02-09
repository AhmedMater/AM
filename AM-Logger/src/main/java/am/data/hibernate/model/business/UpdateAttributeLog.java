package am.data.hibernate.model.business;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "bus_update_attr_log")
public class UpdateAttributeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logID;

    @ManyToOne
    @JoinColumn(name = "update_log_id", referencedColumnName = "log_id")
    private UpdateAttributeLog updateLog;

    @Basic
    @Column(name = "attribute")
    private String attribute;

    @Basic
    @Column(name = "from_value")
    private String fromValue;

    @Basic
    @Column(name = "to_value")
    private String toValue;

    public UpdateAttributeLog() {
    }
    public UpdateAttributeLog(UpdateAttributeLog updateLog, String attribute, String fromValue, String toValue) {
        this.updateLog = updateLog;
        this.attribute = attribute;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    public Integer getLogID() {
        return logID;
    }
    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public UpdateAttributeLog getUpdateLog() {
        return updateLog;
    }
    public void setUpdateLog(UpdateAttributeLog updateLog) {
        this.updateLog = updateLog;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getFromValue() {
        return fromValue;
    }
    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }
    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateAttributeLog)) return false;

        UpdateAttributeLog that = (UpdateAttributeLog) o;

        return getLogID() != null ? getLogID().equals(that.getLogID()) : that.getLogID() == null;
    }

    @Override
    public int hashCode() {
        return getLogID() != null ? getLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UpdateAttributeLog{" +
                "logID = " + logID +
                ", updateLog = " + updateLog +
                ", attribute = " + attribute +
                ", fromValue = " + fromValue +
                ", toValue = " + toValue +
                "}\n";
    }
}
