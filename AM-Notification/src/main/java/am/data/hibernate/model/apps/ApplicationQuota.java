package am.data.hibernate.model.apps;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmed.motair on 2/6/2018.
 */
@Entity
@Table(name = "application_quota")
public class ApplicationQuota {
    public static final String APP_ID = "application." + RegisteredApplication.APP_ID;
    public static final String QUOTA_DATE = "quotaDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quota_id")
    private Integer quotaID;

    @Basic
    @Column(name = "quota_date")
    private String quotaDate;

    @Basic
    @Column(name = "number_of_notification")
    private Integer numOfNotification;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private RegisteredApplication application;

    @Basic
    @Column(name = "event_counter")
    private Integer eventCounter;

    public ApplicationQuota() {
    }
    public ApplicationQuota(RegisteredApplication application) {
        this.quotaDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.numOfNotification = 1;
        this.application = application;
    }

    public Integer getQuotaID() {
        return quotaID;
    }
    public void setQuotaID(Integer quotaID) {
        this.quotaID = quotaID;
    }

    public String getQuotaDate() {
        return quotaDate;
    }
    public void setQuotaDate(String quotaDate) {
        this.quotaDate = quotaDate;
    }

    public Integer getNumOfNotification() {
        return numOfNotification;
    }
    public void setNumOfNotification(Integer numOfNotification) {
        this.numOfNotification = numOfNotification;
    }
    public void incNumOfNotification(Integer numOfNotification) {
        this.numOfNotification += numOfNotification;
    }

    public RegisteredApplication getApplication() {
        return application;
    }
    public void setApplication(RegisteredApplication application) {
        this.application = application;
    }

    public Integer getEventCounter() {
        return eventCounter;
    }
    public void setEventCounter(Integer eventCounter) {
        this.eventCounter = eventCounter;
    }
    public void incEventCounter() {
        this.eventCounter++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationQuota)) return false;

        ApplicationQuota that = (ApplicationQuota) o;

        return getQuotaID() != null ? getQuotaID().equals(that.getQuotaID()) : that.getQuotaID() == null;
    }

    @Override
    public int hashCode() {
        return getQuotaID() != null ? getQuotaID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ApplicationQuota{" +
                "quotaID = " + quotaID +
                ", quotaDate = " + quotaDate +
                ", numOfNotification = " + numOfNotification +
                ", application = " + application +
                "}\n";
    }
}
