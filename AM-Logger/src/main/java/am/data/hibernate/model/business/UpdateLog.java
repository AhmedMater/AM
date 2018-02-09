package am.data.hibernate.model.business;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "bus_update_log")
public class UpdateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logID;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category")
    private Category category;

    @Basic
    @Column(name = "category_related_id")
    private String categoryRelatedId;

    @Basic
    @Column(name = "log_date")
    private Date logDate;

    @Basic
    @Column(name = "action_user_id")
    private String actionUserID;

    @Basic
    @Column(name = "update_from_json")
    private String updateFromJSON;

    @Basic
    @Column(name = "update_to_json")
    private String updateToJSON;

    public UpdateLog() {
    }
    public UpdateLog(Category category, String categoryRelatedId, Date logDate, String actionUserID, String updateFromJSON, String updateToJSON) {
        this.category = category;
        this.categoryRelatedId = categoryRelatedId;
        this.logDate = logDate;
        this.actionUserID = actionUserID;
        this.updateFromJSON = updateFromJSON;
        this.updateToJSON = updateToJSON;
    }

    public Integer getLogID() {
        return logID;
    }
    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryRelatedId() {
        return categoryRelatedId;
    }
    public void setCategoryRelatedId(String categoryRelatedId) {
        this.categoryRelatedId = categoryRelatedId;
    }

    public Date getLogDate() {
        return logDate;
    }
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getActionUserID() {
        return actionUserID;
    }
    public void setActionUserID(String actionUserID) {
        this.actionUserID = actionUserID;
    }

    public String getUpdateFromJSON() {
        return updateFromJSON;
    }
    public void setUpdateFromJSON(String updateFromJSON) {
        this.updateFromJSON = updateFromJSON;
    }

    public String getUpdateToJSON() {
        return updateToJSON;
    }
    public void setUpdateToJSON(String updateToJSON) {
        this.updateToJSON = updateToJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateLog)) return false;

        UpdateLog updateLog = (UpdateLog) o;

        return getLogID() != null ? getLogID().equals(updateLog.getLogID()) : updateLog.getLogID() == null;
    }

    @Override
    public int hashCode() {
        return getLogID() != null ? getLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UpdateLog{" +
                "logID = " + logID +
                ", category = " + category +
                ", categoryRelatedId = " + categoryRelatedId +
                ", logDate = " + logDate +
                ", actionUserID = " + actionUserID +
                ", updateFromJSON = " + updateFromJSON +
                ", updateToJSON = " + updateToJSON +
                "}\n";
    }
}
