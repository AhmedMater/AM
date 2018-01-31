package am.main.data.jaxb.log4jData;

/**
 * Created by ahmed.motair on 11/17/2017.
 */

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
        "appenderRefList"
})
public class Logger implements Cloneable{

    @XmlElement(name = "AppenderRef", required = true)
    protected List<AppenderRef> appenderRefList;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "level")
    protected String level;

    /**
     * Gets the value of the getAppenderRefList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getAppenderRefList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppenderRefList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppenderRef }
     *
     *
     */
    public List<AppenderRef> getAppenderRefList() {
        if (appenderRefList == null) {
            appenderRefList = new ArrayList<AppenderRef>();
        }
        return this.appenderRefList;
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
        for (AppenderRef item : getAppenderRefList())
            clone.getAppenderRefList().add(item.clone());
        clone.setLevel(this.level);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logger)) return false;

        Logger logger = (Logger) o;

        if (getAppenderRefList() != null ? !getAppenderRefList().equals(logger.getAppenderRefList()) : logger.getAppenderRefList() != null) return false;
        if (getName() != null ? !getName().equals(logger.getName()) : logger.getName() != null) return false;
        return getLevel() != null ? getLevel().equals(logger.getLevel()) : logger.getLevel() == null;
    }

    @Override
    public int hashCode() {
        int result = getAppenderRefList() != null ? getAppenderRefList().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLevel() != null ? getLevel().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Logger{" +
                "appenderRefList = " + appenderRefList +
                ", name = " + name +
                ", level = " + level +
                "}\n";
    }
}
