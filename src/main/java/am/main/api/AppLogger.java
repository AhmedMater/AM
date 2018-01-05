package am.main.api;

import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.data.enums.Source;
import am.main.data.jaxb.log4jData.Configuration;
import am.main.data.jaxb.log4jData.RollingFile;
import am.main.data.jaxb.loggerData.AMLogger;
import am.main.data.jaxb.loggerData.AMLoggers;
import am.main.exception.BusinessException;
import am.main.exception.DBException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.IC;
import am.shared.enums.Phase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unitils.thirdparty.org.apache.commons.io.IOUtils;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static am.main.common.ConfigParam.*;

@Singleton
public class AppLogger implements Serializable{
    private static final String CLASS = "AppLogger";

    public static Logger failureLogger = LogManager.getLogger("Failure");
    private static Logger InitialLogger = LogManager.getLogger("Initial");
    private static Map<String, Logger> loggers = new HashMap<>();

    private static AppLogger instance;
    private static String FILE_NAME;

    public AppLogger() {

    }

    public static AppLogger getInstance(){
        if (instance == null){
            synchronized(AppLogger.class){
                if (instance == null){
                    instance = new AppLogger();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load(){
        String FN_NAME = "load";
        AppSession session = new AppSession(Source.AM, Phase.AM_INITIALIZING, CLASS, FN_NAME);
        InitialLogger.info(session + "Start initializing the Loggers of the System");
        try {
            Configuration log4j2 = readLog4J2File();
            InitialLogger.info(session + "Reading old Log4j2.xml file successfully");

            AMLoggers amLoggers = readAMLoggersFile();
            InitialLogger.info(session + "Reading AM-Loggers.xml file successfully");

            List<AMLogger> amLoggerList = amLoggers.getLogger();

            RollingFile templateRolling = new RollingFile();
            for (RollingFile item :log4j2.getAppenders().getRollingFile())
                if(item.getName().equals(TEMPLATE))
                    templateRolling = item;

            am.main.data.jaxb.log4jData.Logger templateLogger = new am.main.data.jaxb.log4jData.Logger();
            for (am.main.data.jaxb.log4jData.Logger item : log4j2.getLoggers().getLogger())
                if (item.getName().equals(TEMPLATE))
                    templateLogger = item;

            for (AMLogger item: amLoggerList) {
                if(item.isEnabled()) {
                    log4j2.addNewLogger(item, templateLogger, templateRolling);
                    InitialLogger.info(session + "Logger: " + item.getName() + " is added to Log4j2.xml successfully");
                }else
                    InitialLogger.info(session + "Logger: " + item.getName() + " is disabled");
            }
            InitialLogger.info(session + "All Loggers are added to Log4j2.xml successfully");

            writeLog4J2File(log4j2);
            InitialLogger.info(session + "Log4j2.xml file is recreated successfully");

            //To Reload the Log4j2.xml
            ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
            InitialLogger.info(session + "Log4j2.xml file is reloaded successfully");

            for (AMLogger item : amLoggerList) {
                loggers.put(item.getName(), LogManager.getLogger(item.getName()));
                InitialLogger.info(session + "Logger: " + item.getName() + " is loaded successfully");
            }

            InitialLogger.info(session + "All the System Loggers has been initialized successfully");
        }catch (Exception ex){
            InitialLogger.error(ex);
        }
    }

    private Configuration readLog4J2File() throws Exception{
        InputStream stream = AppLogger.class.getResourceAsStream(LOG4J2_FILE_NAME);
        String xml = IOUtils.toString(stream, "UTF-8");
        stream.close();
        return XMLHandler.parse(xml, Configuration.class);
    }
    private AMLoggers readAMLoggersFile() throws Exception{
        InputStream stream = AppLogger.class.getResourceAsStream(AM_LOGGERS_FILE_NAME);
        String xml = IOUtils.toString(stream, "UTF-8");
        stream.close();
        return XMLHandler.parse(xml, AMLoggers.class);
    }
    private void writeLog4J2File(Configuration logger) throws Exception{
        String xml = XMLHandler.compose(logger, Configuration.class);

        URL log4j2URL = AppLogger.class.getResource(LOG4J2_FILE_NAME);
        File log4j2File = new File(log4j2URL.getPath());

        PrintWriter writer = new PrintWriter(log4j2File.getAbsolutePath(), "UTF-8");
        writer.write(xml);
        writer.flush();
    }

    public void error(AppSession session, Exception ex) {
        EC ec = null;
        error(session, ex, ec);
    }
    public void error(AppSession session, EC errCode, Object ... args) {
        error(session, null, errCode, args);
    }
    public void error(AppSession session, Exception ex, EC errCode, Object ... args) {
        String excMsg = "";
        if(ex != null) {
            if (ex instanceof BusinessException)
                excMsg = ((BusinessException) ex).getResponse().getEntity().toString();
            else
                excMsg = ex.getMessage();
        }

        String errMsg = "";
        if(errCode != null) {
            if (session != null)
                errMsg = session.getErrorMsg(errCode, args);
            else {
                failureLogger.error(MessageFormat.format(AME.SYS_011.value(), "Error", "Error", errCode.toString()));
                return;
            }

            if(errMsg == null)
                failureLogger.error(MessageFormat.format(AME.SYS_012.value(), "Error", errCode.toString()));
        }else if(ex == null) {
            failureLogger.error(AME.SYS_015.value());
            return;
        }

        if (ex != null) {
            if(ex instanceof DBException || ex instanceof BusinessException)
                logMsg(session, ERROR, errMsg + ",\nDue to: " + excMsg + "\n", null);
            else
                logMsg(session, ERROR_EX, errMsg + ",\nDue to: " + excMsg + "\n", ex);
        }else
            logMsg(session, ERROR, errMsg, null);
    }
    public void error(AppSession session, AME errCode, Object ... args) {
        error(session, null, errCode, args);
    }
    public void error(AppSession session, Exception ex, AME errCode, Object ... args) {
        String excMsg = "";

        if(ex != null) {
            if (ex instanceof BusinessException)
                excMsg = ((BusinessException) ex).getResponse().getEntity().toString();
            else
                excMsg = ex.getMessage();
        }

        String errMsg = "";
        if(errCode != null)
            errMsg = MessageFormat.format(errCode.value(), args);
        else if (ex == null) {
            failureLogger.error(AME.SYS_015.value());
            return;
        }

        if (ex != null) {
            if(ex instanceof DBException || ex instanceof BusinessException)
                logMsg(session, ERROR, errMsg + ",\nDue to: " + excMsg + "\n", null);
            else
                logMsg(session, ERROR_EX, errMsg + ",\nDue to: " + excMsg + "\n", ex);
        }else
            logMsg(session, ERROR, errMsg, null);
    }

    public void info(AppSession session, IC infoCode, Object ... args){
        String infoMsg = "";

        if(session != null) {
            infoMsg = session.getInfoHandler().getMsg(session, infoCode, args);
            if (infoMsg == null)
                failureLogger.error(MessageFormat.format(AME.SYS_012.value(), "Info", infoCode.toString()));
        }else {
            failureLogger.error(MessageFormat.format(AME.SYS_011.value(), "Info", "Info", infoCode.toString()));
            return;
        }
        if(infoMsg == null)
            failureLogger.error(MessageFormat.format(AME.SYS_010.value(), "Info", infoCode.toString()));
        else {
            String message = infoMsg;
            logMsg(session, INFO, message, null);
        }
    }
    public void info(AppSession session, AMI infoMsg, Object ... args){
        String message = MessageFormat.format(infoMsg.value(), args);
        logMsg(session, INFO, message, null);
    }

    public void startDebug(AppSession session, Object ... args){
        String message = "Started with " +
            ((args.length == 0) ? "No Arguments" : "Arguments = " + Arrays.toString(args) + "\n");

        logMsg(session, DEBUG, message.substring(0, message.length() - 1), null);
    }
    public void endDebug(AppSession session){
        logMsg(session, DEBUG, "Ended [Void Function]", null);
    }
    public void endDebug(AppSession session, Object result){
        String message = "Ended with Results = " + (result != null ? result.toString() : "Null");
        logMsg(session, DEBUG, message, null);
    }

    private void logMsg(AppSession session, String level, String msg, Exception ex){
        try {
            if (session != null) {
                try {
                    Logger phaseLogger = loggers.get(session.getPhase().value());
                    if(phaseLogger != null)
                        logMsg(session, phaseLogger, level, msg, ex);
                    else
                        failureLogger.error("Phase Logger: " + session.getPhase().value() + " isn't found");
                } catch (Exception exc) {
                    logMsg(session, failureLogger, ERROR_EX, msg, exc);
                }
            } else
                logMsg(session, failureLogger, level, msg, ex);
        }catch (Exception exc){
            failureLogger.error(exc);
        }
    }
    private void logMsg(AppSession session, Logger logger, String level, String message, Exception ex){
        if(logger == null) {
            logMsg(session, failureLogger, level, message, ex);
            return;
        }

        message = session.toString() + message;
        switch (level){
            case DEBUG: logger.debug(message); break;
            case INFO: logger.info(message); break;
            case ERROR: logger.error(message); break;
            case ERROR_EX: logger.error(message, ex); break;
        }
    }

    private static final String DEBUG = "Debug";
    private static final String ERROR_EX = "Error_Ex";
    private static final String ERROR = "Error";
    private static final String INFO = "INFO";
}
