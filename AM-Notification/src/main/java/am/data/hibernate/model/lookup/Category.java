package am.data.hibernate.model.lookup;

import am.data.hibernate.model.apps.RegisteredApplication;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/12/2018.
 */
@Entity
@Table(name = "category")
public class Category {
    public static final String CATEGORY_ID = "categoryID";
    public static final String APP_ID = "application." + RegisteredApplication.APP_ID;
    @Id
    @Column(name = "category_id")
    private String categoryID;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private RegisteredApplication application;

    @Basic
    @Column(name = "description")
    private String description;

    public Category() {
    }
    public Category(String categoryID) {
        this.categoryID = categoryID;
    }
    public Category(String categoryID, String description) {
        this.categoryID = categoryID;
        this.description = description;
    }

    public String getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public RegisteredApplication getApplication() {
        return application;
    }
    public void setApplication(RegisteredApplication application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return getCategoryID() != null ? getCategoryID().equals(category.getCategoryID()) : category.getCategoryID() == null;
    }

    @Override
    public int hashCode() {
        return getCategoryID() != null ? getCategoryID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return (description != null) ? description : categoryID;
    }
}
