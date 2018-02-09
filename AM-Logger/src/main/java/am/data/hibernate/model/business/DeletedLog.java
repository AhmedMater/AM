package am.data.hibernate.model.business;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "bus_delete_log")
public class DeletedLog {
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
    @Column(name = "deleted_data_json")
    private String deletedDataJSON;

    public DeletedLog() {
    }
    public DeletedLog(Category category, String categoryRelatedId, Date logDate, String actionUserID, String deletedDataJSON) {
        this.category = category;
        this.categoryRelatedId = categoryRelatedId;
        this.logDate = logDate;
        this.actionUserID = actionUserID;
        this.deletedDataJSON = deletedDataJSON;
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

    public String getDeletedDataJSON() {
        return deletedDataJSON;
    }
    public void setDeletedDataJSON(String deletedDataJSON) {
        this.deletedDataJSON = deletedDataJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeletedLog)) return false;

        DeletedLog that = (DeletedLog) o;

        return getLogID() != null ? getLogID().equals(that.getLogID()) : that.getLogID() == null;
    }

    @Override
    public int hashCode() {
        return getLogID() != null ? getLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DeletedLog{" +
                "logID = " + logID +
                ", category = " + category +
                ", categoryRelatedId = " + categoryRelatedId +
                ", logDate = " + logDate +
                ", actionUserID = " + actionUserID +
                ", deletedDataJSON = " + deletedDataJSON +
                "}\n";
    }
}
