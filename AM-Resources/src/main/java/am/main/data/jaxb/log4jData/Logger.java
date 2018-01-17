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
 *         &lt;element name="AppenderRef">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "appenderRef"
})
public class Logger implements Cloneable{

    @XmlElement(name = "AppenderRef", required = true)
    protected AppenderRef appenderRef;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "level")
    protected String level;

    /**
     * Gets the value of the appenderRef property.
     *
     * @return
     *     possible object is
     *     {@link AppenderRef }
     *
     */
    public AppenderRef getAppenderRef() {
        return appenderRef;
    }

    /**
     * Sets the value of the appenderRef property.
     *
     * @param value
     *     allowed object is
     *     {@link AppenderRef }
     *
     */
    public void setAppenderRef(AppenderRef value) {
        this.appenderRef = value;
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
     * Gets the value of the level property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLevel(String value) {
        this.level = value;
    }

    @Override
    public Logger clone() throws CloneNotSupportedException {
        Logger clone = new Logger();
        clone.setName(this.name);
        clone.setAppenderRef(this.appenderRef.clone());
        clone.setLevel(this.level);
        return clone;
    }
}
