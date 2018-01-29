package am.main.data.dto;

import am.main.data.enums.logger.BusLogTypes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
public class AMBusinessLog implements Serializable{
    private BusLogTypes TYPE;
    private Date currentTimeStamp;
    private String actionUserID;
    private String category;
    private String categoryRelatedID;
    private String newDataJSON;
    private String deletedDataJSON;
    private String updatedFromJSON;
    private String updatedToJSON;

    public AMBusinessLog() {
    }

    public AMBusinessLog(BusLogTypes TYPE, Date currentTimeStamp, String actionUserID, String category, String categoryRelatedID, String newDataJSON, String deletedDataJSON, String updatedFromJSON, String updatedToJSON) {
        this.TYPE = TYPE;
        this.currentTimeStamp = currentTimeStamp;
        this.actionUserID = actionUserID;
        this.category = category;
        this.categoryRelatedID = categoryRelatedID;
        this.newDataJSON = newDataJSON;
        this.deletedDataJSON = deletedDataJSON;
        this.updatedFromJSON = updatedFromJSON;
        this.updatedToJSON = updatedToJSON;
    }

    public BusLogTypes getTYPE() {
        return TYPE;
    }
    public void setTYPE(BusLogTypes TYPE) {
        this.TYPE = TYPE;
    }

    public Date getCurrentTimeStamp() {
        return currentTimeStamp;
    }
    public void setCurrentTimeStamp(Date currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    public String getActionUserID() {
        return actionUserID;
    }
    public void setActionUserID(String actionUserID) {
        this.actionUserID = actionUserID;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryRelatedID() {
        return categoryRelatedID;
    }
    public void setCategoryRelatedID(String categoryRelatedID) {
        this.categoryRelatedID = categoryRelatedID;
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

    public String getUpdatedFromJSON() {
        return updatedFromJSON;
    }
    public void setUpdatedFromJSON(String updatedFromJSON) {
        this.updatedFromJSON = updatedFromJSON;
    }

    public String getUpdatedToJSON() {
        return updatedToJSON;
    }
    public void setUpdatedToJSON(String updatedToJSON) {
        this.updatedToJSON = updatedToJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMBusinessLog)) return false;

        AMBusinessLog that = (AMBusinessLog) o;

        if (getTYPE() != that.getTYPE()) return false;
        if (getCurrentTimeStamp() != null ? !getCurrentTimeStamp().equals(that.getCurrentTimeStamp()) : that.getCurrentTimeStamp() != null) return false;
        if (getActionUserID() != null ? !getActionUserID().equals(that.getActionUserID()) : that.getActionUserID() != null) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null) return false;
        if (getCategoryRelatedID() != null ? !getCategoryRelatedID().equals(that.getCategoryRelatedID()) : that.getCategoryRelatedID() != null) return false;
        if (getNewDataJSON() != null ? !getNewDataJSON().equals(that.getNewDataJSON()) : that.getNewDataJSON() != null) return false;
        if (getDeletedDataJSON() != null ? !getDeletedDataJSON().equals(that.getDeletedDataJSON()) : that.getDeletedDataJSON() != null) return false;
        if (getUpdatedFromJSON() != null ? !getUpdatedFromJSON().equals(that.getUpdatedFromJSON()) : that.getUpdatedFromJSON() != null) return false;
        return getUpdatedToJSON() != null ? getUpdatedToJSON().equals(that.getUpdatedToJSON()) : that.getUpdatedToJSON() == null;
    }

    @Override
    public int hashCode() {
        int result = getTYPE() != null ? getTYPE().hashCode() : 0;
        result = 31 * result + (getCurrentTimeStamp() != null ? getCurrentTimeStamp().hashCode() : 0);
        result = 31 * result + (getActionUserID() != null ? getActionUserID().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getCategoryRelatedID() != null ? getCategoryRelatedID().hashCode() : 0);
        result = 31 * result + (getNewDataJSON() != null ? getNewDataJSON().hashCode() : 0);
        result = 31 * result + (getDeletedDataJSON() != null ? getDeletedDataJSON().hashCode() : 0);
        result = 31 * result + (getUpdatedFromJSON() != null ? getUpdatedFromJSON().hashCode() : 0);
        result = 31 * result + (getUpdatedToJSON() != null ? getUpdatedToJSON().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AMBusinessLog{" +
                "TYPE = " + TYPE +
                ", currentTimeStamp = " + currentTimeStamp +
                ", actionUserID = " + actionUserID +
                ", category = " + category +
                ", categoryRelatedID = " + categoryRelatedID +
                ", newDataJSON = " + newDataJSON +
                ", deletedDataJSON = " + deletedDataJSON +
                ", updatedFromJSON = " + updatedFromJSON +
                ", updatedToJSON = " + updatedToJSON +
                "}\n";
    }
}
