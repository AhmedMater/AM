package am.api.dto;

import am.api.enums.VC;

/**
 * Created by ahmed.motair on 9/14/2017.
 */
public class Field {
    private String fieldName;
    private Object value;
    private int minLength;
    private int maxLength;
    private boolean isRequired;
    private VC validationCode;

    public Field() {
    }

    public Field(String fieldName, Object value, int minLength, int maxLength, boolean isRequired, VC validationCode) {
        this.fieldName = fieldName;
        this.value = value;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.isRequired = isRequired;
        this.validationCode = validationCode;
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    public int getMinLength() {
        return minLength;
    }
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isRequired() {
        return isRequired;
    }
    public void setRequired(boolean required) {
        isRequired = required;
    }

    public VC getValidationCode() {
        return validationCode;
    }
    public void setValidationCode(VC validationCode) {
        this.validationCode = validationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;

        Field field = (Field) o;

        if (getMinLength() != field.getMinLength()) return false;
        if (getMaxLength() != field.getMaxLength()) return false;
        if (isRequired() != field.isRequired()) return false;
        if (getFieldName() != null ? !getFieldName().equals(field.getFieldName()) : field.getFieldName() != null) return false;
        if (getValue() != null ? !getValue().equals(field.getValue()) : field.getValue() != null) return false;
        return getValidationCode() == field.getValidationCode();
    }

    @Override
    public int hashCode() {
        int result = getFieldName() != null ? getFieldName().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + getMinLength();
        result = 31 * result + getMaxLength();
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + (getValidationCode() != null ? getValidationCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName = " + fieldName +
                ", value = " + value +
                ", minLength = " + minLength +
                ", maxLength = " + maxLength +
                ", isRequired = " + isRequired +
                ", validationCode = " + validationCode +
                "}\n";
    }
}
