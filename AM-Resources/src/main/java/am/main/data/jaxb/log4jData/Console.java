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
 *         &lt;element name="PatternLayout">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "patternLayout"
})
public class Console implements Cloneable{

    @XmlElement(name = "PatternLayout", required = true)
    protected PatternLayout patternLayout;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "target")
    protected String target;

    /**
     * Gets the value of the patternLayout property.
     *
     * @return
     *     possible object is
     *     {@link PatternLayout }
     *
     */
    public PatternLayout getPatternLayout() {
        return patternLayout;
    }

    /**
     * Sets the value of the patternLayout property.
     *
     * @param value
     *     allowed object is
     *     {@link PatternLayout }
     *
     */
    public void setPatternLayout(PatternLayout value) {
        this.patternLayout = value;
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
     * Gets the value of the target property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTarget(String value) {
        this.target = value;
    }

    @Override
    protected Console clone() throws CloneNotSupportedException {
        Console clone = new Console();
        clone.setName(this.name);
        clone.setPatternLayout(this.patternLayout.clone());
        clone.setTarget(this.target);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Console)) return false;

        Console console = (Console) o;

        if (getPatternLayout() != null ? !getPatternLayout().equals(console.getPatternLayout()) : console.getPatternLayout() != null) return false;
        if (getName() != null ? !getName().equals(console.getName()) : console.getName() != null) return false;
        return getTarget() != null ? getTarget().equals(console.getTarget()) : console.getTarget() == null;
    }

    @Override
    public int hashCode() {
        int result = getPatternLayout() != null ? getPatternLayout().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTarget() != null ? getTarget().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Console{" +
                "patternLayout = " + patternLayout +
                ", name = " + name +
                ", target = " + target +
                "}\n";
    }
}
