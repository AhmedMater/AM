package am.main.api;

import am.main.exception.GeneralException;
import am.main.session.AppSession;
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

import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;
import static am.main.data.enums.impl.AMP.XML_HANDLER;

/**
 * Created by ahmed.motair on 11/17/2017.
 */
public class XMLHandler {
    private static final String CLASS = XMLHandler.class.getSimpleName();

    public static <T> T parse(AppSession appSession, AppLogger logger, String xml, Class<T> className) throws Exception {
        String METHOD = "parse";
        AppSession session = appSession.updateSession(XML_HANDLER, CLASS, METHOD);
        logger.startDebug(session, xml, className);
        try {
            if (xml == null || xml.trim().isEmpty())
                throw new GeneralException(session, E_XML_1);

            logger.info(session, I_XML_1);

            InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            Reader reader = new InputStreamReader(inputStream);

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader streamReader = factory.createXMLStreamReader(reader);

            JAXBContext jaxbContext = JAXBContext.newInstance(className);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            T obj = jaxbUnmarshaller.unmarshal(streamReader, className).getValue();

            logger.info(session, I_XML_2);
            logger.endDebug(session, obj);
            return obj;
        }catch (GeneralException ex){
            throw ex;
        }catch (Exception ex){
            throw new GeneralException(session, E_XML_2, xml);
        }
    }

    public static String compose(AppSession appSession, AppLogger logger, Object obj, Class className) throws Exception {
        String METHOD = "compose";
        AppSession session = appSession.updateSession(XML_HANDLER, CLASS, METHOD);
        logger.startDebug(session, obj, className);
        try{
            if (obj == null)
                throw new GeneralException(session, E_XML_5);

            logger.info(session, I_XML_3);

            // Create JAXB context and initializing Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(className);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // For getting nice formatted output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            //Specify the location and name of xml file to be created
            Writer writer = new CharArrayWriter();
            jaxbMarshaller.marshal(obj, writer);

            String xml = writer.toString();

            logger.info(session, I_XML_4);
            logger.endDebug(session, xml);

            return xml;
        }catch (GeneralException ex){
            throw ex;
        }catch (Exception ex){
            throw new GeneralException(session, E_XML_3, obj);
        }
    }

    /**
     * Validates an XML Message against the XSD Scheme of the System
     *
     * @param xml XML Message
     */
    public static List<String> xsdValidate(AppSession appSession, AppLogger logger, String xml, Schema xmlSchema) throws Exception {
        String METHOD = "xsdValidate";
        AppSession session = appSession.updateSession(XML_HANDLER, CLASS, METHOD);
        logger.startDebug(session, xml, xmlSchema);
        try {
            logger.info(session, I_XML_5);

            if (xml == null || xml.trim().isEmpty())
                throw new GeneralException(session, E_XML_1);

            if (xmlSchema == null)
                throw new GeneralException(session, E_XML_6);

            Validator validator = xmlSchema.newValidator();
            XMLErrorHandler xmlErrorHandler = new XMLErrorHandler();
            validator.setErrorHandler(xmlErrorHandler);
            Source xmlFile = new StreamSource(new StringReader(xml));

            validator.validate(xmlFile);
            List<String> errors = xmlErrorHandler.getErrors();

            logger.info(session, I_XML_6);
            logger.endDebug(session, errors);
            return errors;
        }catch (GeneralException ex){
            throw ex;
        }catch (Exception ex){
            throw new GeneralException(session, E_XML_4, xml);
        }
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
