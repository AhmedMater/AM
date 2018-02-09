package am.data.hibernate.model.business;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
@Entity
@Table(name = "bus_log_type")
public class LogType {
    @Id
    @Column(name = "type")
    private String category;

    @Basic
    @Column(name = "description")
    private String description;

    public LogType() {
    }
    public LogType(String category, String description) {
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

        return getCategory() != null ? getCategory().equals(logType.getCategory()) : logType.getCategory() == null;
    }

    @Override
    public int hashCode() {
        return getCategory() != null ? getCategory().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LogType{" +
                "category = " + category +
                ", description = " + description +
                "}\n";
    }
}
