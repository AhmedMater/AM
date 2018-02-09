package am.data.hibernate.model.performance.session;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_thread")
public class Thread {
    public static final String THREAD_NAME = "threadName";

    @Id
    @Column(name = "thread_id")
    private Integer threadID;

    @Basic
    @Column(name = "thread_name")
    private String threadName;

    @Basic
    @Column(name = "server_thread_id")
    private String serverThreadID;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public Thread() {
    }

    public Thread(String threadName, String serverThreadID) {
        this.threadName = threadName;
        this.serverThreadID = serverThreadID;
        this.firstDateLog = new Date();
    }

    public Integer getThreadID() {
        return threadID;
    }
    public void setThreadID(Integer threadID) {
        this.threadID = threadID;
    }

    public String getThreadName() {
        return threadName;
    }
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getServerThreadID() {
        return serverThreadID;
    }
    public void setServerThreadID(String serverThreadID) {
        this.serverThreadID = serverThreadID;
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
        if (!(o instanceof Thread)) return false;

        Thread thread = (Thread) o;

        return getThreadID() != null ? getThreadID().equals(thread.getThreadID()) : thread.getThreadID() == null;
    }

    @Override
    public int hashCode() {
        return getThreadID() != null ? getThreadID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "threadID = " + threadID +
                ", threadName = " + threadName +
                ", serverThreadID = " + serverThreadID +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
