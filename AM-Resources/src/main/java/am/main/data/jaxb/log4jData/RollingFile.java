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
 *         &lt;element name="Policies">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SizeBasedTriggeringPolicy">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="immediateFlush" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="filePattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "patternLayout",
        "policies"
})
public class RollingFile implements Cloneable{

    @XmlElement(name = "PatternLayout", required = true)
    protected PatternLayout patternLayout;
    @XmlElement(name = "Policies", required = true)
    protected Policies policies;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "fileName")
    protected String fileName;
    @XmlAttribute(name = "immediateFlush")
    protected String immediateFlush;
    @XmlAttribute(name = "filePattern")
    protected String filePattern;

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
     * Gets the value of the policies property.
     *
     * @return
     *     possible object is
     *     {@link Policies }
     *
     */
    public Policies getPolicies() {
        return policies;
    }

    /**
     * Sets the value of the policies property.
     *
     * @param value
     *     allowed object is
     *     {@link Policies }
     *
     */
    public void setPolicies(Policies value) {
        this.policies = value;
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
     * Gets the value of the fileName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the immediateFlush property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getImmediateFlush() {
        return immediateFlush;
    }

    /**
     * Sets the value of the immediateFlush property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setImmediateFlush(String value) {
        this.immediateFlush = value;
    }

    /**
     * Gets the value of the filePattern property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFilePattern() {
        return filePattern;
    }

    /**
     * Sets the value of the filePattern property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFilePattern(String value) {
        this.filePattern = value;
    }

    @Override
    public RollingFile clone() throws CloneNotSupportedException {
        RollingFile clone = new RollingFile();
        clone.setName(this.name);
        clone.setFileName(this.fileName);
        clone.setFilePattern(this.filePattern);
        clone.setImmediateFlush(this.immediateFlush);
        clone.setPatternLayout(this.patternLayout.clone());
        clone.setPolicies(this.policies.clone());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RollingFile)) return false;

        RollingFile that = (RollingFile) o;

        if (getPatternLayout() != null ? !getPatternLayout().equals(that.getPatternLayout()) : that.getPatternLayout() != null) return false;
        if (getPolicies() != null ? !getPolicies().equals(that.getPolicies()) : that.getPolicies() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getFileName() != null ? !getFileName().equals(that.getFileName()) : that.getFileName() != null) return false;
        if (getImmediateFlush() != null ? !getImmediateFlush().equals(that.getImmediateFlush()) : that.getImmediateFlush() != null) return false;
        return getFilePattern() != null ? getFilePattern().equals(that.getFilePattern()) : that.getFilePattern() == null;
    }

    @Override
    public int hashCode() {
        int result = getPatternLayout() != null ? getPatternLayout().hashCode() : 0;
        result = 31 * result + (getPolicies() != null ? getPolicies().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getFileName() != null ? getFileName().hashCode() : 0);
        result = 31 * result + (getImmediateFlush() != null ? getImmediateFlush().hashCode() : 0);
        result = 31 * result + (getFilePattern() != null ? getFilePattern().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RollingFile{" +
                "patternLayout = " + patternLayout +
                ", policies = " + policies +
                ", name = " + name +
                ", fileName = " + fileName +
                ", immediateFlush = " + immediateFlush +
                ", filePattern = " + filePattern +
                "}\n";
    }
}