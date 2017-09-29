package am.common;

import am.api.logger.AppLogger;
import am.common.enums.AME;
import am.common.enums.AMI;
import am.exception.GeneralException;
import am.session.AppSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ahmed.motair on 9/7/2017.
 */
public class ConfigUtils {
    private static final String CLASS = "ConfigUtils";
    private static final AppLogger logger = new AppLogger();

    /**
     * Reads Resource File into Properties Object
     * @param file Resource File Name
     * @return Properties Object
     */
    public static Properties readResourceFiles(AppSession appSession, String file, String component) throws Exception {
        String FN_NAME = "readResourceFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, file, component);
            Properties properties = new Properties();

            if(file == null || file.isEmpty())
                throw new GeneralException(session, AME.IO_001, file, component);

            try {
                InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream(file);
                properties.load(input);
                input.close();
            } catch (FileNotFoundException e) {
                throw new GeneralException(session, e, AME.IO_002, file);
            } catch (IOException e) {
                throw new GeneralException(session, AME.IO_003, file, e.getMessage());
            }

            logger.info(session, AMI.IO_001, file);

            logger.endDebug(session);
            return properties;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, AME.IO_004, file, ex.getMessage());
        }
    }

    /**
     * Reads Remote File into Properties Object
     * @param filePath Resource File Name
     * @return Properties Object
     */
    public static Properties readRemoteFiles(AppSession appSession, String filePath, String component) throws Exception {
        String FN_NAME = "readResourceFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, filePath, component);
            Properties properties = new Properties();

            if(filePath == null || filePath.isEmpty())
                throw new GeneralException(session, AME.IO_001, filePath, component);

            try {
                InputStream input = new FileInputStream(filePath);
                properties.load(input);
                input.close();
            } catch (FileNotFoundException e) {
                throw new GeneralException(session, e, AME.IO_002, filePath);
            } catch (IOException e) {
                throw new GeneralException(session, e, AME.IO_003, filePath, e.getMessage());
            }

            logger.info(session, AMI.IO_001, filePath);

            logger.endDebug(session);
            return properties;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_003, filePath, ex.getMessage());
        }
    }

    /**
     * Replaces the Placeholders in a Message with values
     * @param message String message that has placeholders or no
     * @param args values to be replaced with the placeholders in the message
     * @return String message formatted
     * @throws GeneralException - IO_0007 - If the message needs arguments for placeholders that aren't provided
     * @throws GeneralException - IO_0008 - If the message doesn't have placeholders for the arguments provided
     * @throws GeneralException - IO_0009 - If the message has placeholders less than arguments provided
     * @throws GeneralException - IO_0010 - If the message has placeholders more than arguments provided
     */
    public static String formatMsg(AppSession appSession, String message, Object ... args) throws Exception{
        String FN_NAME = "formatMsg";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, message, args);

            Matcher matcher = Pattern.compile("\\{[0-9]+\\}").matcher(message);
            String _message = "";

            int counter = 0;
            while (matcher.find())
                counter++;

            if (counter == 0) {
                if (args == null || args.length == 0)
                    _message = message;
                else
                    throw new GeneralException(session, AME.SYS_001);
            } else {
                if (args == null || args.length == 0)
                    throw new GeneralException(session, AME.SYS_002);
                else if (args.length == counter)
                    _message = MessageFormat.format(message, args);
                else if (args.length > counter)
                    throw new GeneralException(session, AME.SYS_003);
                else if (args.length < counter)
                    throw new GeneralException(session, AME.SYS_004);
            }

            logger.info(session, AMI.SYS_001, message);
            logger.endDebug(session, _message);
            return _message;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.SYS_005, message, ex.getMessage());
        }
    }

    /**
     * Reads value from Property File
     * @param propertyFile Properties Object
     * @param property String property
     * @return String value of the key
     * @throws GeneralException - AM_E_0030 - If the Property file isn't loaded or Empty
     * @throws GeneralException - AM_E_0031 - If the Property isn't found in the file
     */
    public static String readValueFromPropertyFile(AppSession appSession, Properties propertyFile, String property, String fileName) throws Exception{
        String FN_NAME = "readValueFromPropertyFile";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, property, fileName);

            String value = "";

            if (propertyFile == null || propertyFile.isEmpty())
                throw new GeneralException(session, AME.IO_005, fileName);
            else if (!propertyFile.containsKey(property))
                throw new GeneralException(session, AME.IO_006, property, fileName);
            else
                value = propertyFile.getProperty(property);

            logger.info(session, AMI.IO_002, property, fileName);
            logger.endDebug(session, value);
            return value;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_007, property, fileName, ex.getMessage());
        }
    }

    public static Properties loadSystemComponent(AppSession appSession, String fileName, String componentName){
        String FN_NAME = "loadSystemComponent";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, fileName, componentName);

            Properties properties = ConfigUtils.readRemoteFiles(session, fileName, componentName);

            logger.info(session, AMI.SYS_002, componentName);
            logger.endDebug(session);
            return properties;
        }catch (Exception ex){
            logger.error(session, ex, AME.SYS_006, componentName, ex.getMessage());
            return null;
        }
    }
}
