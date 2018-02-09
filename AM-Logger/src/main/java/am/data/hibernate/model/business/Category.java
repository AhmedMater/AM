package am.data.hibernate.model.business;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "bus_category")
public class Category{
    @Id
    @Column(name = "category")
    private String category;

    @Basic
    @Column(name = "description")
    private String description;

    public Category() {
    }
    public Category(String category, String description) {
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
        if (!(o instanceof Category)) return false;

        Category category1 = (Category) o;

        return getCategory() != null ? getCategory().equals(category1.getCategory()) : category1.getCategory() == null;
    }

    @Override
    public int hashCode() {
        return getCategory() != null ? getCategory().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category = " + category +
                ", description = " + description +
                "}\n";
    }
}
