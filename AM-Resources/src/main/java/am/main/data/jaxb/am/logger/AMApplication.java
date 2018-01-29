
package am.main.data.jaxb.am.logger;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for AM-Application complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AM-Application">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AM-Logger-Config" type="{}AM-Logger-Config" minOccurs="0"/>
 *         &lt;element name="LoggerGroup" type="{}LoggerGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AM-Application", propOrder = {
    "amLoggerConfig",
    "loggerGroup"
})
public class AMApplication {

    @XmlElement(name = "AM-Logger-Config")
    protected AMLoggerConfig amLoggerConfig;
    @XmlElement(name = "LoggerGroup")
    protected List<LoggerGroup> loggerGroup;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type", required = true)
    protected String type;

    public AMApplication() {
        this.amLoggerConfig = new AMLoggerConfig();
    }

    /**
     * Gets the value of the amLoggerConfig property.
     * 
     * @return
     *     possible object is
     *     {@link AMLoggerConfig }
     *     
     */
    public AMLoggerConfig getAMLoggerConfig() {
        return amLoggerConfig;
    }

    /**
     * Sets the value of the amLoggerConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link AMLoggerConfig }
     *     
     */
    public void setAMLoggerConfig(AMLoggerConfig value) {
        this.amLoggerConfig = value;
    }

    /**
     * Gets the value of the loggerGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggerGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggerGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoggerGroup }
     * 
     * 
     */
    public List<LoggerGroup> getLoggerGroup() {
        if (loggerGroup == null) {
            loggerGroup = new ArrayList<LoggerGroup>();
        }
        return this.loggerGroup;
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

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
