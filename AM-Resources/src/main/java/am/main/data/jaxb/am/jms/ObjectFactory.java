
package am.main.data.jaxb.am.jms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the am.main.data.jaxb.am.jms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: am.main.data.jaxb.am.jms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AMJMSConfigManager }
     * 
     */
    public AMJMSConfigManager createAMJMSConfigManager() {
        return new AMJMSConfigManager();
    }

    /**
     * Create an instance of {@link JMSQueues }
     * 
     */
    public JMSQueues createAMJMSConfigManagerJMSQueues() {
        return new JMSQueues();
    }

    /**
     * Create an instance of {@link JMSConfig }
     * 
     */
    public JMSConfig createAMJMSConfigManagerJMSConfig() {
        return new JMSConfig();
    }

    /**
     * Create an instance of {@link AMQueue }
     * 
     */
    public AMQueue createAMJMSConfigManagerJMSQueuesAMQueue() {
        return new AMQueue();
    }

    /**
     * Create an instance of {@link JMSProperty }
     * 
     */
    public JMSProperty createAMJMSConfigManagerJMSConfigJMSProperty() {
        return new JMSProperty();
    }

}
