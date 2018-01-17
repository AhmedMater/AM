package am.data.hibernate.model.lookup;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/12/2018.
 */
@Entity
@Table(name="notification_type")
public class NotificationType {
    public static final String TYPE = "type";

    @Id
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "description")
    private String description;

    public NotificationType() {
    }
    public NotificationType(String type) {
        this.type = type;
    }
    public NotificationType(String type, String description) {
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
        if (!(o instanceof NotificationType)) return false;

        NotificationType notificationType1 = (NotificationType) o;

        return getType() != null ? getType().equals(notificationType1.getType()) : notificationType1.getType() == null;
    }

    @Override
    public int hashCode() {
        return getType() != null ? getType().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotificationType{" +
                "type = " + type +
                ", description = " + description +
                "}\n";
    }
}
