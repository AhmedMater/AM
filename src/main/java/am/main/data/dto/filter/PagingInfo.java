//package am.main.data.dto.filter;
//
//import java.io.Serializable;
//
///**
// * Created by ahmed.motair on 12/16/2017.
// */
//public class PagingInfo implements Serializable{
//    private Integer pageNum;
//    private Integer pageSize;
//
//    public PagingInfo() {
//    }
//    public PagingInfo(Integer pageNum, Integer pageSize) {
//        this.pageNum = pageNum;
//        this.pageSize = pageSize;
//    }
//
//    public Integer getPageNum() {
//        return pageNum;
//    }
//    public void setPageNum(Integer pageNum) {
//        this.pageNum = pageNum;
//    }
//
//    public Integer getPageSize() {
//        return pageSize;
//    }
//    public void setPageSize(Integer pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof PagingInfo)) return false;
//
//        PagingInfo that = (PagingInfo) o;
//
//        if (getPageNum() != null ? !getPageNum().equals(that.getPageNum()) : that.getPageNum() != null) return false;
//        return getPageSize() != null ? getPageSize().equals(that.getPageSize()) : that.getPageSize() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getPageNum() != null ? getPageNum().hashCode() : 0;
//        result = 31 * result + (getPageSize() != null ? getPageSize().hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "PagingInfo{" +
//                "pageNum = " + pageNum +
//                ", pageSize = " + pageSize +
//                "}\n";
//    }
//}
