
package am.main.data.jaxb.am.logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AM-Logger complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AM-Logger">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AM-Logger-Config" type="{}AM-Logger-Config"/>
 *         &lt;element name="AM-Applications" type="{}AM-Applications"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AM-Logger", propOrder = {
    "amLoggerConfig",
    "amApplications"
})
public class AMLogger {

    @XmlElement(name = "AM-Logger-Config", required = true)
    protected AMLoggerConfig amLoggerConfig;
    @XmlElement(name = "AM-Applications", required = true)
    protected AMApplications amApplications;

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
     * Gets the value of the amApplications property.
     * 
     * @return
     *     possible object is
     *     {@link AMApplications }
     *     
     */
    public AMApplications getAMApplications() {
        return amApplications;
    }

    /**
     * Sets the value of the amApplications property.
     * 
     * @param value
     *     allowed object is
     *     {@link AMApplications }
     *     
     */
    public void setAMApplications(AMApplications value) {
        this.amApplications = value;
    }

}
