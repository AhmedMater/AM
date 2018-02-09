package am.data.hibernate.model.performance.lookup;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_fn_status")
public class FnStatus {
    @Id
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "description")
    private String description;

    public FnStatus() {
    }
    public FnStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
        if (!(o instanceof FnStatus)) return false;

        FnStatus fnStatus = (FnStatus) o;

        return getStatus() != null ? getStatus().equals(fnStatus.getStatus()) : fnStatus.getStatus() == null;
    }

    @Override
    public int hashCode() {
        return getStatus() != null ? getStatus().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FnStatus{" +
                "status = " + status +
                ", description = " + description +
                "}\n";
    }
}
