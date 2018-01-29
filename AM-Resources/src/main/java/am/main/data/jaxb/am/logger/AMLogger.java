
package am.main.data.jaxb.am.logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="AM-Application" type="{}AM-Application" maxOccurs="unbounded"/>
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
    "amApplication"
})
public class AMLogger {

    @XmlElement(name = "AM-Application", required = true)
    protected List<AMApplication> amApplication;

    /**
     * Gets the value of the amApplication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amApplication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAMApplication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AMApplication }
     * 
     * 
     */
    public List<AMApplication> getAMApplication() {
        if (amApplication == null) {
            amApplication = new ArrayList<AMApplication>();
        }
        return this.amApplication;
    }

}
