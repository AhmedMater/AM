package am.data.hibernate.model.performance.lookup;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_log_type")
public class LogType {
    @Id
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "description")
    private String description;

    public LogType() {
    }
    public LogType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogType)) return false;

        LogType logType = (LogType) o;

        return getType() != null ? getType().equals(logType.getType()) : logType.getType() == null;
    }

    @Override
    public int hashCode() {
        return getType() != null ? getType().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LogType{" +
                "type = " + type +
                ", description = " + description +
                "}\n";
    }
}
