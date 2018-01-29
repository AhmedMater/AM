
package am.main.data.jaxb.am.jms;

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
 *         &lt;element name="JMS-Config">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="JMS-Property" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="JMS-Queues">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AM-Queue" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="factory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
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
    "jmsConfig",
    "jmsQueues"
})
@XmlRootElement(name = "AM-JMS-Config-Manager")
public class AMJMSConfigManager {

    @XmlElement(name = "JMS-Config", required = true)
    protected JMSConfig jmsConfig;
    @XmlElement(name = "JMS-Queues", required = true)
    protected JMSQueues jmsQueues;

    /**
     * Gets the value of the jmsConfig property.
     * 
     * @return
     *     possible object is
     *     {@link JMSConfig }
     *     
     */
    public JMSConfig getJMSConfig() {
        return jmsConfig;
    }

    /**
     * Sets the value of the jmsConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link JMSConfig }
     *     
     */
    public void setJMSConfig(JMSConfig value) {
        this.jmsConfig = value;
    }

    /**
     * Gets the value of the jmsQueues property.
     * 
     * @return
     *     possible object is
     *     {@link JMSQueues }
     *     
     */
    public JMSQueues getJMSQueues() {
        return jmsQueues;
    }

    /**
     * Sets the value of the jmsQueues property.
     * 
     * @param value
     *     allowed object is
     *     {@link JMSQueues }
     *     
     */
    public void setJMSQueues(JMSQueues value) {
        this.jmsQueues = value;
    }
}
