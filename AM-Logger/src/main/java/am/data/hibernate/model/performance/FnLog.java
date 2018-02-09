package am.data.hibernate.model.performance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_fn_log")
public class FnLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    private Integer functionID;

    @Basic
    @Column(name = "fn_name")
    private String fnName;

    @Basic
    @Column(name = "class_name")
    private String className;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    @Basic
    @Column(name = "num_start_debug")
    private Integer numStartDebug;

    @Basic
    @Column(name = "num_end_debug")
    private Integer numEndDebug;

    @Basic
    @Column(name = "num_info")
    private Integer numInfo;

    @Basic
    @Column(name = "num_warn")
    private Integer numWarn;

    @Basic
    @Column(name = "num_error")
    private Integer numError;

    public FnLog() {
    }

    public Integer getFunctionID() {
        return functionID;
    }
    public void setFunctionID(Integer functionID) {
        this.functionID = functionID;
    }

    public String getFnName() {
        return fnName;
    }
    public void setFnName(String fnName) {
        this.fnName = fnName;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
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

    public Integer getNumStartDebug() {
        return numStartDebug;
    }
    public void setNumStartDebug(Integer numStartDebug) {
        this.numStartDebug = numStartDebug;
    }

    public Integer getNumEndDebug() {
        return numEndDebug;
    }
    public void setNumEndDebug(Integer numEndDebug) {
        this.numEndDebug = numEndDebug;
    }

    public Integer getNumInfo() {
        return numInfo;
    }
    public void setNumInfo(Integer numInfo) {
        this.numInfo = numInfo;
    }

    public Integer getNumWarn() {
        return numWarn;
    }
    public void setNumWarn(Integer numWarn) {
        this.numWarn = numWarn;
    }

    public Integer getNumError() {
        return numError;
    }
    public void setNumError(Integer numError) {
        this.numError = numError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FnLog)) return false;

        FnLog fnLog = (FnLog) o;

        return getFunctionID() != null ? getFunctionID().equals(fnLog.getFunctionID()) : fnLog.getFunctionID() == null;
    }

    @Override
    public int hashCode() {
        return getFunctionID() != null ? getFunctionID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FnLog{" +
                "functionID = " + functionID +
                ", fnName = " + fnName +
                ", className = " + className +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                ", numStartDebug = " + numStartDebug +
                ", numEndDebug = " + numEndDebug +
                ", numInfo = " + numInfo +
                ", numWarn = " + numWarn +
                ", numError = " + numError +
                "}\n";
    }
}
