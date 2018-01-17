package am.data.hibernate.model.lookup;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/12/2018.
 */
@Entity
@Table(name = "event_category")
public class EventCategory {
    @Id
    @Column(name = "category")
    private String category;

    @Basic
    @Column(name = "description")
    private String description;

    public EventCategory() {
    }
    public EventCategory(String category) {
        this.category = category;
    }
    public EventCategory(String category, String description) {
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String type) {
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
        if (!(o instanceof EventCategory)) return false;

        EventCategory eventCategory1 = (EventCategory) o;

        return getCategory() != null ? getCategory().equals(eventCategory1.getCategory()) : eventCategory1.getCategory() == null;
    }

    @Override
    public int hashCode() {
        return getCategory() != null ? getCategory().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventCategory{" +
                "category = " + category +
                ", description = " + description +
                "}\n";
    }
}
