
package am.main.data.jaxb.am.logger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the am.main.data.jaxb.am.logger package. 
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

    private final static QName _AMLogger_QNAME = new QName("", "AM-Logger");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: am.main.data.jaxb.am.logger
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AMLogger }
     * 
     */
    public AMLogger createAMLogger() {
        return new AMLogger();
    }

    /**
     * Create an instance of {@link LoggerGroup }
     * 
     */
    public LoggerGroup createLoggerGroup() {
        return new LoggerGroup();
    }

    /**
     * Create an instance of {@link AMLoggerConfig }
     * 
     */
    public AMLoggerConfig createAMLoggerConfig() {
        return new AMLoggerConfig();
    }

    /**
     * Create an instance of {@link LoggerData }
     * 
     */
    public LoggerData createLoggerData() {
        return new LoggerData();
    }

    /**
     * Create an instance of {@link LoggerProperty }
     * 
     */
    public LoggerProperty createLoggerProperty() {
        return new LoggerProperty();
    }

    /**
     * Create an instance of {@link AMApplications }
     * 
     */
    public AMApplications createAMApplications() {
        return new AMApplications();
    }

    /**
     * Create an instance of {@link AMApplication }
     * 
     */
    public AMApplication createAMApplication() {
        return new AMApplication();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AMLogger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AM-Logger")
    public JAXBElement<AMLogger> createAMLogger(AMLogger value) {
        return new JAXBElement<AMLogger>(_AMLogger_QNAME, AMLogger.class, null, value);
    }

}
