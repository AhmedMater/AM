package am.api.dto;

import java.util.List;

/**
 * Created by ahmed.motair on 9/14/2017.
 */
public class ValidationData {
    private String formID;
    private List<Field> fields;

    public ValidationData() {
    }

    public ValidationData(String formID, List<Field> fields) {
        this.formID = formID;
        this.fields = fields;
    }

    public String getFormID() {
        return formID;
    }
    public void setFormID(String formID) {
        this.formID = formID;
    }

    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationData)) return false;

        ValidationData that = (ValidationData) o;

        if (getFormID() != null ? !getFormID().equals(that.getFormID()) : that.getFormID() != null) return false;
        return getFields() != null ? getFields().equals(that.getFields()) : that.getFields() == null;
    }

    @Override
    public int hashCode() {
        int result = getFormID() != null ? getFormID().hashCode() : 0;
        result = 31 * result + (getFields() != null ? getFields().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ValidationData{" +
                "formID = " + formID +
                ", fields = " + fields +
                "}\n";
    }
}
