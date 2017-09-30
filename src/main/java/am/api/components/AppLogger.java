package am.api.components;

import am.api.enums.EC;
import am.api.enums.IC;
import am.common.enums.AME;
import am.common.enums.AMI;
import am.core.config.AMConfigurationManager;
import am.exception.BusinessException;
import am.exception.GeneralException;
import am.session.AppSession;
import am.session.Interface;
import am.session.Phase;
import am.session.Source;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

@Singleton
public class AppLogger implements Serializable{
    private static final String CLASS = "AppLogger";

    public static Logger failureLogger = LogManager.getLogger("Failure");
    private static Logger rootLogger = LogManager.getRootLogger();

    private static Map<String, Logger> sourceLoggers = new HashMap<>();
    private static Map<String, Logger> interfaceLoggers = new HashMap<>();
    private static Map<String, Logger> phaseLoggers = new HashMap<>();

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
        AppSession session = new AppSession(Source.AM, Interface.INITIALIZING, Phase.AM_CONFIG, CLASS, FN_NAME);
        try {
            AMConfigurationManager amConfigManager = AMConfigurationManager.getInstance();
            Properties amConfigFile = amConfigManager.getAmConfigFile();

            Set<Object> keys = amConfigFile.keySet();
            for (Object key : keys) {
                if (key instanceof String) {
                    if (((String) key).matches(".*Logger$")) {
                        String value = amConfigFile.getProperty(key.toString());
                        String[] values = value.split(",");

                        if(values[0].toLowerCase().equals("true")) {
                            if (((String) key).matches(".*SourceLogger$"))
                                sourceLoggers.put(values[1].trim(), LogManager.getLogger(values[1].trim()));
                            else if (((String) key).matches(".*InterfaceLogger$"))
                                interfaceLoggers.put(values[1].trim(), LogManager.getLogger(values[1].trim()));
                            else if (((String) key).matches(".*PhaseLogger$"))
                                phaseLoggers.put(values[1].trim(), LogManager.getLogger(values[1].trim()));
                            else
                                throw new GeneralException(session, AME.SYS_013, key);
                        }else if(!values[0].toLowerCase().equals("false")){
                            throw new GeneralException(session, AME.SYS_014, value);
                        }
                    }
                }
            }
        }catch (Exception ex){
            failureLogger.error(ex);
        }
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

        if (ex != null)
            logMsg(session, ERROR_EX, errMsg + ",\nDue to: " + excMsg + "\n", ex);
        else
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

        if (ex != null)
            logMsg(session, ERROR_EX, errMsg + ",\nDue to: " + excMsg + "\n", ex);
        else
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
                    Logger sourceLogger = sourceLoggers.get(session.getSource().value());
                    if(sourceLogger != null)
                        logMsg(session, sourceLogger, level, msg, ex);

                    Logger interfaceLogger = interfaceLoggers.get(session.getINTERFACE().value());
                    if(interfaceLogger != null)
                        logMsg(session, interfaceLogger, level, msg, ex);

                    Logger phaseLogger = phaseLoggers.get(session.getPhase().value());
                    if(phaseLogger != null)
                        logMsg(session, phaseLogger, level, msg, ex);
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
