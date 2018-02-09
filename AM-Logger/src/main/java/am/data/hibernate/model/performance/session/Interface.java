package am.data.hibernate.model.performance.session;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ahmed.motair on 1/19/2018.
 */
@Entity
@Table(name = "per_interface")
public class Interface {
    public static final String INTERFACE_NAME = "interfaceName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interface_id")
    private Integer interfaceID;

    @Basic
    @Column(name = "interface_name")
    private String interfaceName;

    @Basic
    @Column(name = "first_date_log")
    private Date firstDateLog;

    @Basic
    @Column(name = "first_exec_log")
    private String firstExecLog;

    public Interface() {
    }
    public Interface(String interfaceName) {
        this.interfaceName = interfaceName;
        this.firstDateLog = new Date();
    }

    public Integer getInterfaceID() {
        return interfaceID;
    }
    public void setInterfaceID(Integer interfaceID) {
        this.interfaceID = interfaceID;
    }

    public String getInterfaceName() {
        return interfaceName;
    }
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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
        if (!(o instanceof Interface)) return false;

        Interface that = (Interface) o;

        return getInterfaceID() != null ? getInterfaceID().equals(that.getInterfaceID()) : that.getInterfaceID() == null;
    }

    @Override
    public int hashCode() {
        return getInterfaceID() != null ? getInterfaceID().hashCode() : 0;
    }
}
