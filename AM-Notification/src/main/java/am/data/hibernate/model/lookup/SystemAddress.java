package am.data.hibernate.model.lookup;

import javax.persistence.*;

/**
 * Created by ahmed.motair on 1/13/2018.
 */
@Entity
@Table(name="system_address")
public class SystemAddress {
    public static final String TYPE = "type." + NotificationType.TYPE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressID;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type")
    private NotificationType type;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "address_password")
    private String password;

    public SystemAddress() {
    }
    public SystemAddress(NotificationType type, String address) {
        this.type = type;
        this.address = address;
    }

    public Integer getAddressID() {
        return addressID;
    }
    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemAddress)) return false;

        SystemAddress that = (SystemAddress) o;

        return getAddressID() != null ? getAddressID().equals(that.getAddressID()) : that.getAddressID() == null;
    }

    @Override
    public int hashCode() {
        return getAddressID() != null ? getAddressID().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SystemAddress{" +
                "addressID = " + addressID +
                ", type = " + type +
                ", address = " + address +
                "}\n";
    }
}
