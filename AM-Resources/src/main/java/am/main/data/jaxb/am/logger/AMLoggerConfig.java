
package am.main.data.jaxb.am.logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for AM-Logger-Config complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AM-Logger-Config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Logger-Property" type="{}Logger-Property" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AM-Logger-Config", propOrder = {
    "loggerProperty"
})
public class AMLoggerConfig {

    @XmlElement(name = "Logger-Property", required = true)
    protected List<LoggerProperty> loggerProperty;

    /**
     * Gets the value of the loggerProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggerProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggerProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoggerProperty }
     * 
     * 
     */
    public List<LoggerProperty> getLoggerProperty() {
        if (loggerProperty == null) {
            loggerProperty = new ArrayList<LoggerProperty>();
        }
        return this.loggerProperty;
    }

    public LoggerProperty getLoggerProperty(String name){
        for (LoggerProperty property : loggerProperty)
            if (property.getName().equals(name))
                return property;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMLoggerConfig)) return false;

        AMLoggerConfig that = (AMLoggerConfig) o;

        return getLoggerProperty() != null ? getLoggerProperty().equals(that.getLoggerProperty()) : that.getLoggerProperty() == null;
    }

    @Override
    public int hashCode() {
        return getLoggerProperty() != null ? getLoggerProperty().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AMLoggerConfig{" +
                "loggerProperty = " + loggerProperty +
                "}\n";
    }
}
