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
 *         &lt;element name="Console">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PatternLayout">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RollingFile" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PatternLayout">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Policies">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SizeBasedTriggeringPolicy">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="immediateFlush" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="filePattern" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "console",
        "rollingFile"
})
public class Appenders implements Cloneable{

    @XmlElement(name = "Console", required = true)
    protected Console console;
    @XmlElement(name = "RollingFile")
    protected List<RollingFile> rollingFile;

    /**
     * Gets the value of the console property.
     *
     * @return
     *     possible object is
     *     {@link Console }
     *
     */
    public Console getConsole() {
        return console;
    }

    /**
     * Sets the value of the console property.
     *
     * @param value
     *     allowed object is
     *     {@link Console }
     *
     */
    public void setConsole(Console value) {
        this.console = value;
    }

    /**
     * Gets the value of the rollingFile property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rollingFile property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRollingFile().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RollingFile }
     *
     *
     */
    public List<RollingFile> getRollingFile() {
        if (rollingFile == null) {
            rollingFile = new ArrayList<RollingFile>();
        }
        return this.rollingFile;
    }

    @Override
    protected Appenders clone() throws CloneNotSupportedException {
        Appenders clone = new Appenders();
        clone.setConsole(this.console.clone());
        for (RollingFile item : getRollingFile()) {
            clone.getRollingFile().add(item.clone());
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appenders)) return false;

        Appenders appenders = (Appenders) o;

        if (getConsole() != null ? !getConsole().equals(appenders.getConsole()) : appenders.getConsole() != null) return false;
        return getRollingFile() != null ? getRollingFile().equals(appenders.getRollingFile()) : appenders.getRollingFile() == null;
    }

    @Override
    public int hashCode() {
        int result = getConsole() != null ? getConsole().hashCode() : 0;
        result = 31 * result + (getRollingFile() != null ? getRollingFile().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Appenders{" +
                "console = " + console +
                ", rollingFile = " + rollingFile +
                "}\n";
    }
}
