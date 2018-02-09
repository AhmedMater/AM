package am.data.hibernate.model.performance.session;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_source")
public class Source {
    public static final String SOURCE_NAME = "sourceName";

    @Id
    @Column(name = "source_id")
    private Integer sourceID;

    @Basic
    @Column(name = "source_name")
    private String sourceName;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public Source() {
    }
    public Source(String sourceName) {
        this.sourceName = sourceName;
        this.firstDateLog = new Date();
    }

    public Integer getSourceID() {
        return sourceID;
    }
    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }

    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
        if (!(o instanceof Source)) return false;

        Source source = (Source) o;

        return getSourceID() != null ? getSourceID().equals(source.getSourceID()) : source.getSourceID() == null;
    }

    @Override
    public int hashCode() {
        return getSourceID() != null ? getSourceID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Source{" +
                "sourceID = " + sourceID +
                ", sourceName = " + sourceName +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
