package am.data.hibernate.model.performance;

import am.data.hibernate.model.performance.lookup.FnStatus;
import am.data.hibernate.model.performance.session.Interface;
import am.data.hibernate.model.performance.session.Phase;
import am.data.hibernate.model.performance.session.Source;
import am.data.hibernate.model.performance.session.Thread;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_fn_exec_log")
public class FnExecLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logID;

    @ManyToOne
    @JoinColumn(name = "function_id", referencedColumnName = "function_id")
    private FnLog fnLog;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "status")
    private FnStatus status;

    @ManyToOne
    @JoinColumn(name = "thread_id", referencedColumnName = "thread_id")
    private Thread thread;

    @ManyToOne
    @JoinColumn(name = "interface_id", referencedColumnName = "interface_id")
    private Interface interfaceLog;

    @ManyToOne
    @JoinColumn(name = "phase_id", referencedColumnName = "phase_id")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "source_id")
    private Source source;

    @Basic
    @Column(name = "last_exec_id")
    private String lastExecLog;

    public FnExecLog() {
    }

    public Integer getLogID() {
        return logID;
    }
    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public FnLog getFnLog() {
        return fnLog;
    }
    public void setFnLog(FnLog fnLog) {
        this.fnLog = fnLog;
    }

    public FnStatus getStatus() {
        return status;
    }
    public void setStatus(FnStatus status) {
        this.status = status;
    }

    public Thread getThread() {
        return thread;
    }
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Interface getInterfaceLog() {
        return interfaceLog;
    }
    public void setInterfaceLog(Interface interfaceLog) {
        this.interfaceLog = interfaceLog;
    }

    public Phase getPhase() {
        return phase;
    }
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }

    public String getLastExecLog() {
        return lastExecLog;
    }
    public void setLastExecLog(String lastExecLog) {
        this.lastExecLog = lastExecLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FnExecLog)) return false;

        FnExecLog fnExecLog = (FnExecLog) o;

        return getLogID() != null ? getLogID().equals(fnExecLog.getLogID()) : fnExecLog.getLogID() == null;
    }

    @Override
    public int hashCode() {
        return getLogID() != null ? getLogID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FnExecLog{" +
                "logID = " + logID +
                ", fnLog = " + fnLog +
                ", status = " + status +
                ", thread = " + thread +
                ", interfaceLog = " + interfaceLog +
                ", phase = " + phase +
                ", source = " + source +
                ", lastExecLog = " + lastExecLog +
                "}\n";
    }
}
