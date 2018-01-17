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
 *         &lt;element name="AppenderRef" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
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
public class Root implements Cloneable{

    @XmlElement(name = "AppenderRef")
    protected List<AppenderRef> appenderRef;
    @XmlAttribute(name = "level")
    protected String level;

    /**
     * Gets the value of the appenderRef property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appenderRef property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppenderRef().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppenderRef }
     *
     *
     */
    public List<AppenderRef> getAppenderRef() {
        if (appenderRef == null) {
            appenderRef = new ArrayList<AppenderRef>();
        }
        return this.appenderRef;
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
    protected Root clone() throws CloneNotSupportedException {
        Root clone = new Root();
        clone.setLevel(this.level);
        for (AppenderRef appenderRef : this.getAppenderRef())
            clone.getAppenderRef().add(appenderRef.clone());

        return clone;
    }
}
