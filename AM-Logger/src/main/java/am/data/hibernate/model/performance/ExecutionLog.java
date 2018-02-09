package am.data.hibernate.model.performance;

import am.data.hibernate.model.performance.lookup.LogType;
import am.data.hibernate.model.performance.session.Interface;
import am.data.hibernate.model.performance.session.Phase;
import am.data.hibernate.model.performance.session.Source;
import am.data.hibernate.model.performance.session.Thread;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_exec_log")
public class ExecutionLog {
    private static final String ARGS_SEPARATOR = ";,;";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_log_id")
    private BigInteger execLogID;

    @ManyToOne
    @JoinColumn(name = "function_id", referencedColumnName = "function_id")
    private FnLog fnLog;

    @ManyToOne
    @JoinColumn(name = "log_type", referencedColumnName = "type")
    private LogType type;

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
    @Column(name = "exec_time_stamp")
    private Date execTimeStamp;

    @Basic
    @Column(name = "exec_log_date")
    private Date execLogDate;

    @Basic
    @Column(name = "interface_related_id")
    private String interfaceRelatedID;

    @Basic
    @Column(name = "full_msg")
    private String fullMsg;

    @Basic
    @Column(name = "args")
    private String args;

    @Basic
    @Column(name = "processed")
    private Boolean processed;

    public ExecutionLog() {
    }
    public ExecutionLog(BigInteger execLogID, FnLog fnLog, LogType type, Thread thread, Interface interfaceLog, Phase phase, Source source, Date execTimeStamp, Date execLogDate, String interfaceRelatedID, String fullMsg, String args, Boolean processed) {
        this.execLogID = execLogID;
        this.fnLog = fnLog;
        this.type = type;
        this.thread = thread;
        this.interfaceLog = interfaceLog;
        this.phase = phase;
        this.source = source;
        this.execTimeStamp = execTimeStamp;
        this.execLogDate = execLogDate;
        this.interfaceRelatedID = interfaceRelatedID;
        this.fullMsg = fullMsg;
        this.args = args;
        this.processed = processed;
    }

    public BigInteger getExecLogID() {
        return execLogID;
    }
    public void setExecLogID(BigInteger execLogID) {
        this.execLogID = execLogID;
    }

    public FnLog getFnLog() {
        return fnLog;
    }
    public void setFnLog(FnLog fnLog) {
        this.fnLog = fnLog;
    }

    public LogType getType() {
        return type;
    }
    public void setType(LogType type) {
        this.type = type;
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

    public Date getExecTimeStamp() {
        return execTimeStamp;
    }
    public void setExecTimeStamp(Date execTimeStamp) {
        this.execTimeStamp = execTimeStamp;
    }

    public Date getExecLogDate() {
        return execLogDate;
    }
    public void setExecLogDate(Date execLogDate) {
        this.execLogDate = execLogDate;
    }

    public String getInterfaceRelatedID() {
        return interfaceRelatedID;
    }
    public void setInterfaceRelatedID(String interfaceRelatedID) {
        this.interfaceRelatedID = interfaceRelatedID;
    }

    public String getFullMsg() {
        return fullMsg;
    }
    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }

    public String getArgs() {
        return args;
    }
    public String[] getArgsArray() {
        return args.split(ARGS_SEPARATOR);
    }
    public void setArgs(String args) {
        this.args = args;
    }
    public void setArgs(List<String> args) {
        this.args = args.get(0);
        for (String arg : args)
            this.args += ARGS_SEPARATOR + arg;
    }

    public Boolean getProcessed() {
        return processed;
    }
    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecutionLog)) return false;

        ExecutionLog that = (ExecutionLog) o;

        return execLogID != null ? execLogID.equals(that.execLogID) : that.execLogID == null;
    }

    @Override
    public int hashCode() {
        return execLogID != null ? execLogID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ExecutionLog{" +
                "execLogID = " + execLogID +
                ", fnLog = " + fnLog +
                ", type = " + type +
                ", thread = " + thread +
                ", interfaceLog = " + interfaceLog +
                ", phase = " + phase +
                ", source = " + source +
                ", execTimeStamp = " + execTimeStamp +
                ", execLogDate = " + execLogDate +
                ", interfaceRelatedID = " + interfaceRelatedID +
                ", fullMsg = " + fullMsg +
                ", args = " + args +
                ", processed = " + processed +
                "}\n";
    }
}
