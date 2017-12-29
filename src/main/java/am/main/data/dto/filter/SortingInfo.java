package am.main.data.dto.filter;

import static am.main.common.ConfigParam.DESC_ORDER;

/**
 * Created by ahmed.motair on 12/16/2017.
 */
public class SortingInfo {
    private String direction;
    private String by;

    public SortingInfo() {
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
