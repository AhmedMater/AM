package am.data.hibernate.model.performance.codes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_warn")
public class WarnCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warn_id")
    private Integer warnID;

    @Basic
    @Column(name = "warn_code")
    private String code;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public WarnCode() {
    }

    public Integer getWarnID() {
        return warnID;
    }
    public void setWarnID(Integer warnID) {
        this.warnID = warnID;
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
        if (!(o instanceof WarnCode)) return false;

        WarnCode warnCode = (WarnCode) o;

        return getWarnID() != null ? getWarnID().equals(warnCode.getWarnID()) : warnCode.getWarnID() == null;
    }

    @Override
    public int hashCode() {
        return getWarnID() != null ? getWarnID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WarnCode{" +
                "warnID = " + warnID +
                ", code = " + code +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
