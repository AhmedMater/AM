package am.main.data.jaxb.log4jData;

/**
 * Created by ahmed.motair on 11/17/2017.
 */

import javax.xml.bind.annotation.*;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SizeBasedTriggeringPolicy">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sizeBasedTriggeringPolicy"
})
public class Policies implements Cloneable{
    @XmlElement(name = "SizeBasedTriggeringPolicy", required = true)
    protected SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy;

    public Policies() {
    }

    public Policies(SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy) {
        this.sizeBasedTriggeringPolicy = sizeBasedTriggeringPolicy;
    }

    /**
     * Gets the value of the sizeBasedTriggeringPolicy property.
     *
     * @return
     *     possible object is
     *     {@link SizeBasedTriggeringPolicy }
     *
     */
    public SizeBasedTriggeringPolicy getSizeBasedTriggeringPolicy() {
        return sizeBasedTriggeringPolicy;
    }

    /**
     * Sets the value of the sizeBasedTriggeringPolicy property.
     *
     * @param value
     *     allowed object is
     *     {@link SizeBasedTriggeringPolicy }
     *
     */
    public void setSizeBasedTriggeringPolicy(SizeBasedTriggeringPolicy value) {
        this.sizeBasedTriggeringPolicy = value;
    }

    @Override
    protected Policies clone() throws CloneNotSupportedException {
        Policies clone = new Policies();
        clone.setSizeBasedTriggeringPolicy(this.sizeBasedTriggeringPolicy.clone());
        return clone;
    }
}
