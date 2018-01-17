package am.main.data.dto;


import am.main.api.validation.groups.*;
import am.main.common.RegExp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static am.main.common.ConfigParam.DESC_ORDER;

/**
 * Created by ahmed.motair on 12/16/2017.
 */
public class SortingInfo {
    public static final Map<String, String> FIELDS = Collections.unmodifiableMap(
            new HashMap<String, String>(){{
                put("direction", "Sorting Direction");
                put("by", "Sorting By Column");
            }}
    );

    @Size(min = 3, max = 4, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
    @Pattern(regexp = RegExp.ORDER_DIRECTION, message = FormValidation.REGEX, groups = InvalidValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String direction;

    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
    private String by;

    public SortingInfo() {
        this.direction = DESC_ORDER;
    }
    public SortingInfo(String by) {
        this.by = by;
    }
    public SortingInfo(String direction, String by) {
        this.direction = direction;
        this.by = by;
    }

    public String getDirection() {
        return (direction == null) ? DESC_ORDER : direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }

    public String getSortStatement(){
        return "ORDER BY " + by + " " + direction;
    }

    public static Map<String, String> getFIELDS() {
        return FIELDS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortingInfo)) return false;

        SortingInfo that = (SortingInfo) o;

        if (getDirection() != null ? !getDirection().equals(that.getDirection()) : that.getDirection() != null) return false;
        return getBy() != null ? getBy().equals(that.getBy()) : that.getBy() == null;
    }

    @Override
    public int hashCode() {
        int result = getDirection() != null ? getDirection().hashCode() : 0;
        result = 31 * result + (getBy() != null ? getBy().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SortingInfo{" +
                "direction = " + direction +
                ", by = " + by +
                "}\n";
    }
}
