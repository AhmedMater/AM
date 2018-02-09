package am.data.hibernate.model.archive;

import am.data.hibernate.model.business.Category;
import am.data.hibernate.model.business.LogType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
@Entity
@Table(name = "arc_bus_log")
public class ArcBusinessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arc_log_id")
    private Integer arcLogID;

    @ManyToOne
    @JoinColumn(name = "log_type", referencedColumnName = "type")
    private LogType type;

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

    @Basic
    @Column(name = "deleted_data_json")
    private String deletedDataJSON;

    @Basic
    @Column(name = "updated_from_json")
    private String updateFromJSON;

    @Basic
    @Column(name = "updated_to_json")
    private String updateToJSON;

    @Basic
    @Column(name = "item_updated_json")
    private String itemUpdatedJSON;

    public ArcBusinessLog() {
    }
    public ArcBusinessLog(LogType type, Category category, String categoryRelatedId, Date logDate, String actionUserID, String newDataJSON, String deletedDataJSON, String updateFromJSON, String updateToJSON, String itemUpdatedJSON) {
        this.type = type;
        this.category = category;
        this.categoryRelatedId = categoryRelatedId;
        this.logDate = logDate;
        this.actionUserID = actionUserID;
        this.newDataJSON = newDataJSON;
        this.deletedDataJSON = deletedDataJSON;
        this.updateFromJSON = updateFromJSON;
        this.updateToJSON = updateToJSON;
        this.itemUpdatedJSON = itemUpdatedJSON;
    }

    public Integer getArcLogID() {
        return arcLogID;
    }
    public void setArcLogID(Integer arcLogID) {
        this.arcLogID = arcLogID;
    }

    public LogType getType() {
        return type;
    }
    public void setType(LogType type) {
        this.type = type;
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

    public String getDeletedDataJSON() {
        return deletedDataJSON;
    }
    public void setDeletedDataJSON(String deletedDataJSON) {
        this.deletedDataJSON = deletedDataJSON;
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

    public String getItemUpdatedJSON() {
        return itemUpdatedJSON;
    }
    public void setItemUpdatedJSON(String itemUpdatedJSON) {
        this.itemUpdatedJSON = itemUpdatedJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArcBusinessLog)) return false;

        ArcBusinessLog that = (ArcBusinessLog) o;

        return getArcLogID() != null ? getArcLogID().equals(that.getArcLogID()) : that.getArcLogID() == null;
    }

    @Override
    public int hashCode() {
        return getArcLogID() != null ? getArcLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ArcBusinessLog{" +
                "arcLogID = " + arcLogID +
                ", type = " + type +
                ", category = " + category +
                ", categoryRelatedId = " + categoryRelatedId +
                ", logDate = " + logDate +
                ", actionUserID = " + actionUserID +
                ", newDataJSON = " + newDataJSON +
                ", deletedDataJSON = " + deletedDataJSON +
                ", updateFromJSON = " + updateFromJSON +
                ", updateToJSON = " + updateToJSON +
                ", itemUpdatedJSON = " + itemUpdatedJSON +
                "}\n";
    }
}
