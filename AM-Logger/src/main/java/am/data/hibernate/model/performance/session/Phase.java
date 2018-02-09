package am.data.hibernate.model.performance.session;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_phase")
public class Phase {
    public static final String PHASE_NAME = "phaseName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phase_id")
    private Integer phaseID;

    @Basic
    @Column(name = "phase_name")
    private String phaseName;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public Phase() {
    }

    public Phase(String phaseName) {
        this.phaseName = phaseName;
        this.firstDateLog = new Date();
    }

    public Integer getPhaseID() {
        return phaseID;
    }
    public void setPhaseID(Integer phaseID) {
        this.phaseID = phaseID;
    }

    public String getPhaseName() {
        return phaseName;
    }
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
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
        if (!(o instanceof Phase)) return false;

        Phase phase = (Phase) o;

        return getPhaseID() != null ? getPhaseID().equals(phase.getPhaseID()) : phase.getPhaseID() == null;
    }

    @Override
    public int hashCode() {
        return getPhaseID() != null ? getPhaseID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Phase{" +
                "phaseID = " + phaseID +
                ", phaseName = " + phaseName +
                ", firstDateLog = " + firstDateLog +
                ", firstExecLog = " + firstExecLog +
                "}\n";
    }
}
