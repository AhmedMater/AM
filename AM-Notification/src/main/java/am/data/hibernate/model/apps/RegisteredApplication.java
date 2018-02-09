package am.data.hibernate.model.apps;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 2/2/2018.
 */
@Entity
@Table(name = "registered_application")
public class RegisteredApplication {
    public static final String APP_ID = "appID";
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";

    @Id
    @Column(name = "app_id")
    private String appID;

    @Basic
    @Column(name = "app_name")
    private String appName;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "user_pass")
    private String password;

    @Basic
    @Column(name = "creation_date")
    private Date creationDate;

    @Basic
    @Column(name = "quota_per_day")
    private Integer quotaPerDay;

    @Basic
    @Column(name = "reached_max_quota")
    private Boolean reachedMaxQuota;

    public RegisteredApplication() {
    }
    public RegisteredApplication(String appID, String appName, String description, String username, String password) {
        this.appID = appID;
        this.appName = appName;
        this.description = description;
        this.username = username;
        this.password = password;
    }

    public static String getAppId() {
        return APP_ID;
    }

    public String getAppID() {
        return appID;
    }
    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getQuotaPerDay() {
        return quotaPerDay;
    }
    public void setQuotaPerDay(Integer quotaPerDay) {
        this.quotaPerDay = quotaPerDay;
    }

    public Boolean getReachedMaxQuota() {
        return reachedMaxQuota;
    }
    public void setReachedMaxQuota(Boolean reachedMaxQuota) {
        this.reachedMaxQuota = reachedMaxQuota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisteredApplication)) return false;

        RegisteredApplication that = (RegisteredApplication) o;

        return getAppID() != null ? getAppID().equals(that.getAppID()) : that.getAppID() == null;
    }

    @Override
    public int hashCode() {
        return getAppID() != null ? getAppID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RegisteredApplication{" +
                "appID = " + appID +
                ", appName = " + appName +
                ", description = " + description +
                ", username = " + username +
                ", password = " + password +
                "}\n";
    }
}
