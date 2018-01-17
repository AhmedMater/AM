package am.main.api;

import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed.motair on 11/17/2017.
 */
public class XMLHandler {
    public static <T> T parse(String xml, Class<T> className) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        Reader reader = new InputStreamReader(inputStream);

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = factory.createXMLStreamReader(reader);

        JAXBContext jaxbContext = JAXBContext.newInstance(className);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return jaxbUnmarshaller.unmarshal(streamReader, className).getValue();
    }

    public static String compose(Object obj, Class className) throws Exception {
        // Create JAXB context and initializing Marshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(className);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // For getting nice formatted output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        //Specify the location and name of xml file to be created
        Writer writer = new CharArrayWriter();
        jaxbMarshaller.marshal(obj, writer);

        return writer.toString();
    }

    /**
     * Validates an XML Message against the XSD Scheme of the System
     *
     * @param xml XML Message
     */
    public static List<String> xsdValidate(String xml, Schema xmlSchema) throws Exception {
        if (xml == null || xml.equals(""))
            throw new Exception("XML Message String is empty or null");

        if (xmlSchema == null)
            throw new Exception("The XSD Scheme isn't loaded in the System");

        Validator validator = xmlSchema.newValidator();
        XMLErrorHandler xmlErrorHandler = new XMLErrorHandler();
        validator.setErrorHandler(xmlErrorHandler);
        Source xmlFile = new StreamSource(new StringReader(xml));

        validator.validate(xmlFile);
        return xmlErrorHandler.getErrors();
    }

    public static class XMLErrorHandler implements org.xml.sax.ErrorHandler {
        List<String> errors = new ArrayList<>();

        public XMLErrorHandler() {
        }

        @Override
        public void warning(SAXParseException exception) {
            errors.add("\n" + MessageFormat.format("[WARN] Line: {0}, Column: {1}, Error: {2}",
                    exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage()));
        }

        @Override
        public void error(SAXParseException exception) {
            errors.add("\n" + MessageFormat.format("[ERROR] Line: {0}, Column: {1}, Error: {2}",
                    exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage()));
        }

        @Override
        public void fatalError(SAXParseException exception) {
            errors.add("\n" + MessageFormat.format("[FATAL] Line: {0}, Column: {1}, Error: {2}",
                    exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage()));
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
