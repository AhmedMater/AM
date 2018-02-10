package am.main.common;

import am.main.api.AppLogger;
import am.main.api.XMLHandler;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import org.unitils.thirdparty.org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.Properties;

import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;

/**
 * Created by ahmed.motair on 9/7/2017.
 */
public class ConfigUtils {
    private static final String CLASS = ConfigUtils.class.getSimpleName();

    /**
     * Reads External XML File and Parses the content to an Object of the same class provided
     *
     * @param className Class to which the XML will be parsed
     * @param filePath Path of the file
     * @return Parsed object of the xml file content
     * @throws GeneralException <code>E_IO_2</code> - If the File Path is null or empty String
     * @throws GeneralException <code>E_IO_3</code> - If the file isn't found
     * @throws GeneralException <code>E_IO_4</code> - If the file Path provided is invalid
     * @throws GeneralException <code>E_IO_5</code> - If the content of the file is empty
     * @throws GeneralException <code>E_IO_6</code> - If the file isn't readable
     * @throws GeneralException <code>E_IO_1</code> - If failed to read the file due to any other reason
     */
    public static <T> T readRemoteXMLFile(AppSession appSession, AppLogger logger, Class<T> className, String filePath) throws Exception {
        String FN_NAME = "readRemoteXMLFile";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        String xmlFile = readFile(session, logger, false, filePath);
        return XMLHandler.parse(xmlFile, className);
    }

    /**
     * Reads Resource XML File and Parses the content to an Object of the same class provided
     *
     * @param className - Class to which the XML will be parsed
     * @param filePath - Path of the file
     * @return Parsed object of the xml file content
     * @throws GeneralException <code>E_IO_2</code> - If the File Path is null or empty String
     * @throws GeneralException <code>E_IO_3</code> - If the file isn't found
     * @throws GeneralException <code>E_IO_4</code> - If the file Path provided is invalid
     * @throws GeneralException <code>E_IO_5</code> - If the content of the file is empty
     * @throws GeneralException <code>E_IO_6</code> - If the file isn't readable
     * @throws GeneralException <code>E_IO_1</code> - If failed to read the file due to any other reason
     */
    public static <T> T readResourceXMLFile(AppSession appSession, AppLogger logger, Class<T> className, String filePath) throws Exception {
        String FN_NAME = "readResourceXMLFile";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        String xmlFile = readFile(session, logger, true, filePath);
        return XMLHandler.parse(xmlFile, className);
    }

    private static String readFile(AppSession session, AppLogger logger, Boolean isResourceFile, String filePath) throws Exception{
        try {
            logger.startDebug(session, isResourceFile, filePath);

            logger.info(session, I_IO_1, filePath);
            String xmlFile = "";

            if (filePath == null || filePath.isEmpty())
                throw new GeneralException(session, E_IO_2, (filePath == null ? "Null" : filePath));

            try {
                InputStream stream = (isResourceFile) ?
                    AppLogger.class.getResourceAsStream(filePath) :
                        new FileInputStream(new File(filePath));

                xmlFile = IOUtils.toString(stream, "UTF-8");
                stream.close();
            } catch (FileNotFoundException | NullPointerException e) {
                throw new GeneralException(session, e, E_IO_3, filePath);
            } catch (InvalidPathException e) {
                throw new GeneralException(session, e, E_IO_4, filePath);
            } catch (SecurityException e) {
                throw new GeneralException(session, e, E_IO_6, filePath);
            }

            if (xmlFile.isEmpty())
                throw new GeneralException(session, E_IO_5, filePath);

            logger.info(session, I_IO_2, filePath);

            logger.endDebug(session);
            return xmlFile;

        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_IO_1, filePath);
        }
    }

    /**
     * Reads Remote Properties File into Properties Object
     * @param filePath Resource File Name
     * @return Properties Object
     */
    public static Properties readRemotePropertyFiles(AppSession appSession, AppLogger logger, String filePath) throws Exception {
        String FN_NAME = "readRemotePropertyFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        String propertyFileStr = readFile(session, logger, false, filePath);

        Properties properties = new Properties();
        properties.load(new StringReader(propertyFileStr));

        if (properties == null || properties.isEmpty())
            throw new GeneralException(session, E_IO_8, filePath);

        return properties;
    }

    /**
     * Reads Resource Properties File into Properties Object
     * @param filePath Resource File Name
     * @return Properties Object
     */
    public static Properties readResourcePropertyFiles(AppSession appSession, AppLogger logger, String filePath) throws Exception {
        String FN_NAME = "readResourcePropertyFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        String propertyFileStr = readFile(session, logger, true, filePath);

        Properties properties = new Properties();
        properties.load(new StringReader(propertyFileStr));

        if (properties == null || properties.isEmpty())
            throw new GeneralException(session, E_IO_8, filePath);

        return properties;
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
            logger.info(session, I_IO_3, property, fileName);

            String value = "";
            if (propertyFile == null || propertyFile.isEmpty())
                throw new GeneralException(session, E_IO_8, fileName);
            else if (!propertyFile.containsKey(property))
                throw new GeneralException(session, E_IO_9, property, fileName);
            else
                value = propertyFile.getProperty(property);

            if(value.isEmpty())
                throw new GeneralException(session, E_IO_10, property, fileName);

            logger.info(session, I_IO_4, property, fileName);
            logger.endDebug(session, value);
            return value;
        } catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_IO_11, property, fileName);
        }
    }

    /**
     * Reads Remote Text File into Properties Object
     * @param filePath Resource File Name
     * @return Properties Object
     */
    public static String readRemoteTextFiles(AppSession appSession, AppLogger logger, String filePath) throws Exception {
        String FN_NAME = "readRemoteTextFiles";
        AppSession session = appSession.updateSession(CLASS, FN_NAME);
        return readFile(session, logger, false, filePath);
    }
//    public static Properties loadPropertySystemComponent(AppSession appSession, AppLogger loggerDataList, String fileName, String componentName) throws Exception{
//        String FN_NAME = "loadPropertySystemComponent";
//        AppSession session = appSession.updateSession(CLASS, FN_NAME);
////        try {
//            loggerDataList.startDebug(session, fileName, componentName);
//
//            Properties properties = new Properties();
//
//            if(fileName.endsWith(".properties"))
//                properties = ConfigUtils.readRemotePropertyFiles(session, loggerDataList, fileName, componentName);
//            else if(fileName.endsWith(".txt")){
//                List<String> fileLines = ConfigUtils.readRemoteTextFiles(session, loggerDataList, fileName, componentName);
//                properties.put("0", fileLines);
//            }
//
//            loggerDataList.info(session, AMI.SYS_002, componentName);
//            loggerDataList.endDebug(session);
//
//            return properties;
////        }catch (Exception ex){
////            loggerDataList.error(session, ex, AME.SYS_006, componentName);
////            return null;
////        }
//    }


//    /**
//     * Reads Remote Properties File into Properties Object
//     * @param filePath Resource File Name
//     * @return Properties Object
//     */
//    public static Properties readRemotePropertyFiles(AppSession appSession, AppLogger logger, String filePath, String component) throws Exception {
//        String FN_NAME = "readRemotePropertyFiles";
//        AppSession session = appSession.updateSession(CLASS, FN_NAME);
//        try {
//            logger.startDebug(session, filePath, component);
//            Properties properties = new Properties();
//
//            if(filePath == null || filePath.isEmpty())
//                throw new GeneralException(session, AME.IO_001, filePath, component);
//
//            try {
//                InputStream input = new FileInputStream(filePath);
//                properties.load(input);
//                input.close();
//            } catch (FileNotFoundException e) {
//                throw new GeneralException(session, e, AME.IO_002, filePath);
//            } catch (IOException e) {
//                throw new GeneralException(session, e, AME.IO_003, filePath);
//            }
//
//            logger.info(session, AMI.IO_001, filePath);
//
//            logger.endDebug(session);
//            return properties;
//        } catch (Exception ex){
//            if(ex instanceof GeneralException)
//                throw ex;
//            else
//                throw new GeneralException(session, ex, AME.IO_003, filePath);
//        }
//    }

//    /**
//     * Reads Remote Text File into Properties Object
//     * @param filePathStr Resource File Name
//     * @return Properties Object
//     */
//    public static String readRemoteTextFiles(AppSession appSession, AppLogger logger, String filePathStr, String component) throws Exception {
//        String FN_NAME = "readRemoteTextFiles";
//        AppSession session = appSession.updateSession(CLASS, FN_NAME);
//        try {
//            logger.startDebug(session, filePathStr, component);
//            String textFile = "";
//
//            if(filePathStr == null || filePathStr.isEmpty())
//                throw new GeneralException(session, AME.IO_001, filePathStr, component);
//
//            try {
//                Path filePath = Paths.get(filePathStr);
//                List<String> fileLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
//                for (String line : fileLines)
//                    textFile += line + "\n";
//                textFile = textFile.substring(0, textFile.length()-1);
//            } catch (FileNotFoundException e) {
//                throw new GeneralException(session, e, AME.IO_002, filePathStr);
//            } catch (InvalidPathException e) {
//                throw new GeneralException(session, e, AME.IO_012, filePathStr);
//            }
//
//            logger.info(session, AMI.IO_001, filePathStr);
//
//            logger.endDebug(session);
//            return textFile;
//        } catch (Exception ex){
//            if(ex instanceof GeneralException)
//                throw ex;
//            else
//                throw new GeneralException(session, ex, AME.IO_003, filePathStr);
//        }
//    }

//    /**
//     * Reads Resource File into Properties Object
//     * @param file Resource File Name
//     * @return Properties Object
//     */
//    public static Properties readResourceFiles(AppSession appSession, AppLogger logger, String file, String component) throws Exception {
//        String FN_NAME = "readResourceFiles";
//        AppSession session = appSession.updateSession(CLASS, FN_NAME);
//        try {
//            logger.startDebug(session, file, component);
//            Properties properties = new Properties();
//
//            if(file == null || file.isEmpty())
//                throw new GeneralException(session, AME.IO_001, file, component);
//
//            try {
//                InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream(file);
//                properties.load(input);
//                input.close();
//            } catch (FileNotFoundException e) {
//                throw new GeneralException(session, e, AME.IO_002, file);
//            } catch (IOException e) {
//                throw new GeneralException(session, e, AME.IO_003, file);
//            }
//
//            logger.info(session, AMI.IO_001, file);
//
//            logger.endDebug(session);
//            return properties;
//        } catch (Exception ex){
//            if(ex instanceof GeneralException)
//                throw ex;
//            else
//                throw new GeneralException(session, ex, AME.IO_004, file);
//        }
//    }

}
