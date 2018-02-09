package am.data.hibernate.model.performance.codes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_info")
public class InfoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Integer infoID;

    @Basic
    @Column(name = "info_code")
    private String code;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public InfoCode() {
    }

    public Integer getInfoID() {
        return infoID;
    }
    public void setInfoID(Integer infoID) {
        this.infoID = infoID;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Date getFirstDateLog() {
        return firstDateLog;
    }
    public void setFirstDateLog(Date firstDateLog) {
        this.firstDateLog = firstDateLog;
    }

    public String getFirstExecLog() {
        return firstExecLog;
    }
    public void setFirstExecLog(String firstExecLog) {
        this.firstExecLog = firstExecLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoCode)) return false;

        InfoCode infoCode = (InfoCode) o;

        return getInfoID() != null ? getInfoID().equals(infoCode.getInfoID()) : infoCode.getInfoID() == null;
    }

    @Override
    public int hashCode() {
        return getInfoID() != null ? getInfoID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InfoCode{" +
                "infoID = " + infoID +
                ", code = " + code +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
