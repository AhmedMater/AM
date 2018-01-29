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
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value"
})
public class SizeBasedTriggeringPolicy implements Cloneable{

    @XmlValue
    protected String value;
    @XmlAttribute(name = "size")
    protected String size;

    public SizeBasedTriggeringPolicy() {
    }

    public SizeBasedTriggeringPolicy(String size) {
        this.size = size;
    }
    /**
     * Gets the value of the value property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the size property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSize(String value) {
        this.size = value;
    }

    @Override
    protected SizeBasedTriggeringPolicy clone() throws CloneNotSupportedException {
        SizeBasedTriggeringPolicy clone = new SizeBasedTriggeringPolicy();
        clone.setValue(this.value);
        clone.setSize(this.size);
        return clone;
    }
}
