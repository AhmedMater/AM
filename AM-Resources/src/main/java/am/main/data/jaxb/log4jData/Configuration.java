
package am.main.data.jaxb.log4jData;

import am.main.api.AppLogger;
import am.main.data.jaxb.am.logger.AMLoggerConfig;
import am.main.data.jaxb.am.logger.LoggerData;
import am.main.data.jaxb.am.logger.LoggerGroup;
import am.main.session.AppSession;

import javax.xml.bind.annotation.*;
import java.text.MessageFormat;
import java.util.List;

import static am.main.data.enums.impl.AMI.I_LOG_3;
import static am.main.data.enums.logger.LogConfigProp.MAIN_LOG_PATH;
import static am.main.data.enums.logger.LogConfigProp.MAX_FILE_SIZE;


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
 *         &lt;element name="appenders">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Console">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PatternLayout">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="RollingFile" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PatternLayout">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Policies">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="SizeBasedTriggeringPolicy">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="immediateFlush" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="filePattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Loggers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Root">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AppenderRef" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="LoggerData" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AppenderRef">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}string" />
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
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "appenders",
    "loggers"
})
@XmlRootElement(name = "configuration")
public class Configuration implements Cloneable{

    @XmlElement(required = true)
    protected Appenders appenders;
    @XmlElement(name = "Loggers", required = true)
    protected Loggers loggers;
    @XmlAttribute(name = "status")
    protected String status = "WARN";

    /**
     * Gets the value of the appenders property.
     * 
     * @return
     *     possible object is
     *     {@link Appenders }
     *     
     */
    public Appenders getAppenders() {
        return appenders;
    }

    /**
     * Sets the value of the appenders property.
     * 
     * @param value
     *     allowed object is
     *     {@link Appenders }
     *     
     */
    public void setAppenders(Appenders value) {
        this.appenders = value;
    }

    /**
     * Gets the value of the loggers property.
     * 
     * @return
     *     possible object is
     *     {@link Loggers }
     *     
     */
    public Loggers getLoggers() {
        return loggers;
    }

    /**
     * Sets the value of the loggers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loggers }
     *     
     */
    public void setLoggers(Loggers value) {
        this.loggers = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    private static final String LOGGER_FILE_NAME = "{0}\\{1}\\{2}.log";
    private static final String LOGGER_FILE_PATTERN = "{0}\\{1}\\{2}\\{2}-%i-%d'{yyyy-MM-dd HH-mm-ss.SSS'}.log";

    public void addNewLogger(AppSession appSession, LoggerGroup group, AppLogger logger, AMLoggerConfig config, Logger templateLogger, RollingFile templateRolling) throws Exception{
        String METHOD = "addNewLogger";
        AppSession session = appSession.updateSession(getClass().getSimpleName(), METHOD);
        logger.startDebug(session, group, config, templateLogger, templateRolling);

        String groupName = group.getName();
        String mainPath = config.getLoggerProperty(MAIN_LOG_PATH.getName()).getValue();
        String maxFileLogSize = config.getLoggerProperty(MAX_FILE_SIZE.getName()).getValue();

        List<LoggerData> loggerDataList = group.getLoggerData();
        for (LoggerData loggerData : loggerDataList) {
            RollingFile newRollingFile = templateRolling.clone();

            newRollingFile.setName(loggerData.getName());
            newRollingFile.setFileName(MessageFormat.format(LOGGER_FILE_NAME, mainPath, groupName, loggerData.getName()));
            newRollingFile.setFilePattern(MessageFormat.format(LOGGER_FILE_PATTERN, mainPath, groupName, loggerData.getName()));
            this.getAppenders().getRollingFile().add(newRollingFile);

            Logger newLogger = templateLogger.clone();

            newLogger.setName(loggerData.getName());
            newLogger.setLevel(loggerData.getLevel());
            newLogger.getAppenderRefList().add(new AppenderRef(loggerData.getName()));
            newLogger.getAppenderRefList().add(new AppenderRef("Console"));
            this.getLoggers().getLogger().add(newLogger);
            logger.info(session, I_LOG_3, loggerData.getName());
        }
        logger.endDebug(session);
    }
    @Override
    protected Configuration clone() throws CloneNotSupportedException {
        Configuration clone = new Configuration();
        clone.setAppenders(this.appenders.clone());
        clone.setLoggers(this.loggers.clone());
        clone.setStatus(this.status);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Configuration)) return false;

        Configuration that = (Configuration) o;

        if (getAppenders() != null ? !getAppenders().equals(that.getAppenders()) : that.getAppenders() != null) return false;
        if (getLoggers() != null ? !getLoggers().equals(that.getLoggers()) : that.getLoggers() != null) return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getAppenders() != null ? getAppenders().hashCode() : 0;
        result = 31 * result + (getLoggers() != null ? getLoggers().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "appenders = " + appenders +
                ", loggers = " + loggers +
                ", status = " + status +
                "}\n";
    }
}
