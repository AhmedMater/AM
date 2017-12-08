package am.main.data.vto;


import java.io.Serializable;

/**
 * Created by mohamed.elewa on 4/7/2016.
 */
public class PaginationInfo implements Serializable {
    private int total;
    private int pageSize;
    private int pageNum;

    public PaginationInfo() {
    }

    public PaginationInfo(int total, int pageSize, int pageNum) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaginationInfo)) return false;

        PaginationInfo that = (PaginationInfo) o;

        if (getTotal() != that.getTotal()) return false;
        if (getPageSize() != that.getPageSize()) return false;
        return getPageNum() == that.getPageNum();
    }

    @Override
    public int hashCode() {
        int result = getTotal();
        result = 31 * result + getPageSize();
        result = 31 * result + getPageNum();
        return result;
    }

    @Override
    public String toString() {
        return "PaginationInfo{" +
                "total = " + total +
                ", pageSize = " + pageSize +
                ", pageNum = " + pageNum +
                "}\n";
    }
}
