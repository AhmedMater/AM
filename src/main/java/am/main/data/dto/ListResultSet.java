package am.main.data.dto;

import am.main.data.vto.PaginationInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ahmed.motair on 12/29/2017.
 */
public class ListResultSet<T> implements Serializable{
    private List<T> data;
    private PaginationInfo pagination;

    public ListResultSet() {
    }
    public ListResultSet(List<T> data, PaginationInfo pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }
    public void setPagination(PaginationInfo pagination) {
        this.pagination = pagination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListResultSet)) return false;

        ListResultSet that = (ListResultSet) o;

        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) return false;
        return getPagination() != null ? getPagination().equals(that.getPagination()) : that.getPagination() == null;
    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getPagination() != null ? getPagination().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListResultSet{" +
                "data = " + data +
                ", pagination = " + pagination +
                "}\n";
    }
}
