package am.main.common;

import am.main.api.AppLogger;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.exception.GeneralException;
import am.main.session.AppSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ahmed.motair on 9/7/2017.
 */
public class ConfigUtils {
    private static final String CLASS = ConfigUtils.class.getSimpleName();

    /**
     * Reads Resource File into Properties Object
     * @param file Resource File Name
     * @return Properties Object
     */
    public static Properties readResourceFiles(AppSession appSession, AppLogger logger, String file, String component) throws Exception {
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
                throw new GeneralException(session, e, AME.IO_003, file);
            }

            logger.info(session, AMI.IO_001, file);

            logger.endDebug(session);
            return properties;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_004, file);
        }
    }

    /**
     * Reads Remote Properties File into Properties Object
     * @param filePath Resource File Name
     * @return Properties Object
     */
    public static Properties readRemotePropertyFiles(AppSession appSession, AppLogger logger, String filePath, String component) throws Exception {
        String FN_NAME = "readRemotePropertyFiles";
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
                throw new GeneralException(session, e, AME.IO_003, filePath);
            }

            logger.info(session, AMI.IO_001, filePath);

            logger.endDebug(session);
            return properties;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_003, filePath);
        }
    }

    /**
     * Reads Remote Text File into Properties Object
     * @param filePathStr Resource File Name
     * @return Properties Object
     */
    public static List<String> readRemoteTextFiles(AppSession appSession, AppLogger logger, String filePathStr, String component) throws Exception {
        String FN_NAME = "readRemoteTextFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        try {
            logger.startDebug(session, filePathStr, component);
            List<String> fileLines = new ArrayList<>();

            if(filePathStr == null || filePathStr.isEmpty())
                throw new GeneralException(session, AME.IO_001, filePathStr, component);

            try {
                Path filePath = Paths.get(filePathStr);
                fileLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            } catch (FileNotFoundException e) {
                throw new GeneralException(session, e, AME.IO_002, filePathStr);
            } catch (InvalidPathException e) {
                throw new GeneralException(session, e, AME.IO_012, filePathStr);
            }

            logger.info(session, AMI.IO_001, filePathStr);

            logger.endDebug(session);
            return fileLines;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_003, filePathStr);
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
    public static String formatMsg(AppSession appSession, AppLogger logger, String message, Object ... args) throws Exception{
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
                throw new GeneralException(session, ex, AME.SYS_005, message);
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
    public static String readValueFromPropertyFile(AppSession appSession, AppLogger logger, Properties propertyFile, String property, String fileName) throws Exception{
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

            if(value.isEmpty())
                throw new GeneralException(session, AME.IO_010, property, fileName);

            logger.info(session, AMI.IO_002, property, fileName);
            logger.endDebug(session, value);
            return value;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, AME.IO_007, property, fileName);
        }
    }

    public static Properties loadPropertySystemComponent(AppSession appSession, AppLogger logger, String fileName, String componentName) throws Exception{
        String FN_NAME = "loadPropertySystemComponent";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
//        try {
            logger.startDebug(session, fileName, componentName);

            Properties properties = new Properties();

            if(fileName.endsWith(".properties"))
                properties = ConfigUtils.readRemotePropertyFiles(session, logger, fileName, componentName);
            else if(fileName.endsWith(".txt")){
                List<String> fileLines = ConfigUtils.readRemoteTextFiles(session, logger, fileName, componentName);
                properties.put("0", fileLines);
            }

            logger.info(session, AMI.SYS_002, componentName);
            logger.endDebug(session);

            return properties;
//        }catch (Exception ex){
//            logger.error(session, ex, AME.SYS_006, componentName);
//            return null;
//        }
    }


}
