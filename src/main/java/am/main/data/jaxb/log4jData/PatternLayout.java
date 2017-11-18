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
 *       &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
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
public class PatternLayout implements Cloneable{
    @XmlValue
    protected String value;
    @XmlAttribute(name = "pattern")
    protected String pattern;

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
     * Gets the value of the pattern property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the value of the pattern property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

    @Override
    protected PatternLayout clone() throws CloneNotSupportedException {
        PatternLayout clone = new PatternLayout();
        clone.setPattern(this.pattern);
        clone.setValue(this.value);
        return clone;
    }
}
