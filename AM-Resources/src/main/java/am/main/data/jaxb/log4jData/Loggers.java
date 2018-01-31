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
 *         &lt;element name="Root">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AppenderRef" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LoggerData" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AppenderRef">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
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
        "root",
        "logger"
})
public class Loggers implements Cloneable{

    @XmlElement(name = "Root", required = true)
    protected Root root;
    @XmlElement(name = "Logger")
    protected List<Logger> logger;

    /**
     * Gets the value of the root property.
     *
     * @return
     *     possible object is
     *     {@link Root }
     *
     */
    public Root getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     *
     * @param value
     *     allowed object is
     *     {@link Root }
     *
     */
    public void setRoot(Root value) {
        this.root = value;
    }

    /**
     * Gets the value of the loggerDataList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggerDataList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggerDataList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Logger }
     *
     *
     */
    public List<Logger> getLogger() {
        if (logger == null) {
            logger = new ArrayList<Logger>();
        }
        return this.logger;
    }

    @Override
    protected Loggers clone() throws CloneNotSupportedException {
        Loggers clone = new Loggers();
        clone.setRoot(this.root);
        for (Logger item : getLogger())
            clone.getLogger().add(item.clone());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loggers)) return false;

        Loggers loggers = (Loggers) o;

        if (getRoot() != null ? !getRoot().equals(loggers.getRoot()) : loggers.getRoot() != null) return false;
        return getLogger() != null ? getLogger().equals(loggers.getLogger()) : loggers.getLogger() == null;
    }

    @Override
    public int hashCode() {
        int result = getRoot() != null ? getRoot().hashCode() : 0;
        result = 31 * result + (getLogger() != null ? getLogger().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Loggers{" +
                "root = " + root +
                ", logger = " + logger +
                "}\n";
    }
}
