
package am.main.data.jaxb.log4jData;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the am.main.data.jaxb.log4jData package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and amt.model
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: am.main.data.jaxb.log4jData
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Configuration }
     * 
     */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Create an instance of {@link Loggers }
     * 
     */
    public Loggers createConfigurationLoggers() {
        return new Loggers();
    }

    /**
     * Create an instance of {@link Logger }
     * 
     */
    public Logger createConfigurationLoggersLogger() {
        return new Logger();
    }

    /**
     * Create an instance of {@link Root }
     * 
     */
    public Root createConfigurationLoggersRoot() {
        return new Root();
    }

    /**
     * Create an instance of {@link Appenders }
     * 
     */
    public Appenders createConfigurationAppenders() {
        return new Appenders();
    }

    /**
     * Create an instance of {@link RollingFile }
     * 
     */
    public RollingFile createConfigurationAppendersRollingFile() {
        return new RollingFile();
    }

    /**
     * Create an instance of {@link Policies }
     * 
     */
    public Policies createConfigurationAppendersRollingFilePolicies() {
        return new Policies();
    }

    /**
     * Create an instance of {@link Console }
     * 
     */
    public Console createConfigurationAppendersConsole() {
        return new Console();
    }

    /**
     * Create an instance of {@link AppenderRef }
     * 
     */
    public AppenderRef createConfigurationLoggersLoggerAppenderRef() {
        return new AppenderRef();
    }

    /**
     * Create an instance of {@link AppenderRef }
     * 
     */
    public AppenderRef createConfigurationLoggersRootAppenderRef() {
        return new AppenderRef();
    }

    /**
     * Create an instance of {@link PatternLayout }
     * 
     */
    public PatternLayout createConfigurationAppendersRollingFilePatternLayout() {
        return new PatternLayout();
    }

    /**
     * Create an instance of {@link SizeBasedTriggeringPolicy }
     * 
     */
    public SizeBasedTriggeringPolicy createConfigurationAppendersRollingFilePoliciesSizeBasedTriggeringPolicy() {
        return new SizeBasedTriggeringPolicy();
    }

    /**
     * Create an instance of {@link PatternLayout }
     * 
     */
    public PatternLayout createConfigurationAppendersConsolePatternLayout() {
        return new PatternLayout();
    }

}
