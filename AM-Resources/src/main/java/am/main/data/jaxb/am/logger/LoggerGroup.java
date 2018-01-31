
package am.main.data.jaxb.am.logger;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for LoggerGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoggerGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoggerData" type="{}LoggerData" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoggerGroup", propOrder = {
    "loggerData"
})
public class LoggerGroup {

    @XmlElement(name = "LoggerData", required = true)
    protected List<LoggerData> loggerData;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the loggerData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggerData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggerData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoggerData }
     * 
     * 
     */
    public List<LoggerData> getLoggerData() {
        if (loggerData == null) {
            loggerData = new ArrayList<LoggerData>();
        }
        return this.loggerData;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public LoggerGroup clone() throws CloneNotSupportedException {
        LoggerGroup clone = new LoggerGroup();
        clone.setName(this.name);
        for (LoggerData item : getLoggerData())
            clone.getLoggerData().add(item.clone());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoggerGroup)) return false;

        LoggerGroup group = (LoggerGroup) o;

        if (getLoggerData() != null ? !getLoggerData().equals(group.getLoggerData()) : group.getLoggerData() != null) return false;
        return getName() != null ? getName().equals(group.getName()) : group.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getLoggerData() != null ? getLoggerData().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoggerGroup{" +
                "loggerData = " + loggerData +
                ", name = " + name +
                "}\n";
    }
}
