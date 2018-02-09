package am.data.hibernate.model.business;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "bus_new_log")
public class NewLog {
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
    @Column(name = "new_data_json")
    private String newDataJSON;

    public NewLog() {
    }
    public NewLog(Category category, String categoryRelatedId, Date logDate, String actionUserID, String newDataJSON) {
        this.category = category;
        this.categoryRelatedId = categoryRelatedId;
        this.logDate = logDate;
        this.actionUserID = actionUserID;
        this.newDataJSON = newDataJSON;
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

    public String getNewDataJSON() {
        return newDataJSON;
    }
    public void setNewDataJSON(String newDataJSON) {
        this.newDataJSON = newDataJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewLog)) return false;

        NewLog newLog = (NewLog) o;

        return getLogID() != null ? getLogID().equals(newLog.getLogID()) : newLog.getLogID() == null;
    }

    @Override
    public int hashCode() {
        return getLogID() != null ? getLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NewLog{" +
                "logID = " + logID +
                ", category = " + category +
                ", categoryRelatedId = " + categoryRelatedId +
                ", logDate = " + logDate +
                ", actionUserID = " + actionUserID +
                ", newDataJSON = " + newDataJSON +
                "}\n";
    }
}
