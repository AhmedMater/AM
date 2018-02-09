package am.data.hibernate.model.performance.codes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_error")
public class ErrorCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id")
    private Integer errorID;

    @Basic
    @Column(name = "error_code")
    private String code;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public ErrorCode() {
    }

    public Integer getErrorID() {
        return errorID;
    }
    public void setErrorID(Integer errorID) {
        this.errorID = errorID;
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
        if (!(o instanceof ErrorCode)) return false;

        ErrorCode errorCode = (ErrorCode) o;

        return getErrorID() != null ? getErrorID().equals(errorCode.getErrorID()) : errorCode.getErrorID() == null;
    }

    @Override
    public int hashCode() {
        return getErrorID() != null ? getErrorID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "errorID = " + errorID +
                ", code = " + code +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
