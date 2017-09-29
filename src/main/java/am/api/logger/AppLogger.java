package am.api.logger;

import am.api.error.EC;
import am.api.info.IC;
import am.core.config.AMConfigurationManager;
import am.core.config.AM_CC;
import am.common.enums.AME;
import am.common.enums.AMI;
import am.exception.BusinessException;
import am.session.AppSession;
import am.session.Phase;
import am.session.Source;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

@Singleton
public class AppLogger implements Serializable{
    private static final String CLASS = "AppLogger";
    private static Logger rootLogger = LogManager.getRootLogger();
    private static Logger appLogger;
    private static Logger sysLogger;
    private static Logger restLogger;
    private static Logger dbLogger;
    private static Logger soapLogger;
    private static Logger jmsLogger;
    private static Logger serviceLogger;

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
        AppSession session = new AppSession(Phase.INITIAL_APP, Source.AM, CLASS, FN_NAME);
        try {
            AMConfigurationManager amConfigManager = AMConfigurationManager.getInstance();
            
            String flag = amConfigManager.getConfigValue(session, AM_CC.APP_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                appLogger = LogManager.getLogger("App");

            flag = amConfigManager.getConfigValue(session, AM_CC.AM_LIBRARY_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                sysLogger = LogManager.getLogger("AMLibrary");

            flag = amConfigManager.getConfigValue(session, AM_CC.REST_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                restLogger = LogManager.getLogger("REST");

            flag = amConfigManager.getConfigValue(session, AM_CC.DB_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                dbLogger = LogManager.getLogger("DB");

            flag = amConfigManager.getConfigValue(session, AM_CC.SOAP_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                soapLogger = LogManager.getLogger("SOAP");

            flag = amConfigManager.getConfigValue(session, AM_CC.JMS_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                jmsLogger = LogManager.getLogger("JMS");

            flag = amConfigManager.getConfigValue(session, AM_CC.SERVICE_LOG_FLAG);
            if (flag != null && Boolean.parseBoolean(flag))
                serviceLogger = LogManager.getLogger("Service");
        }catch (Exception ex){
            rootLogger.error(ex);
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
                excMsg = ",\nDue to: " + ((BusinessException) ex).getResponse().getEntity().toString();
            else
                excMsg = ",\nDue to: " + ex.getMessage();
        }

        String errMsg = "";
        if(errCode != null) {
            if(session != null)
                errMsg = session.getErrorMsg(errCode, args);
            else
                appLogger.error(MessageFormat.format(AME.SYS_011.value(), "Error", "Error", errCode.toString()));
        }

        //TODO: if both ex and error Code are nulls

        String message = //header(session, CLASS, FN_NAME) + 
                errMsg + excMsg;

        if (ex != null)
            logMsg(session, ERROR_EX, message, ex);
//            appLogger.error(message + "\n", ex);
        else
            logMsg(session, ERROR, message, null);
//            appLogger.error(message + "\n");

    }
    public void error(AppSession session, AME errCode, Object ... args) {
        error(session, null, errCode, args);
    }
    public void error(AppSession session, Exception ex, AME errCode, Object ... args) {
        String excMsg = "";

        if(ex != null) {
            if (ex instanceof BusinessException)
                excMsg = "Due to: " + ((BusinessException) ex).getResponse().getEntity().toString();
            else
                excMsg = "Due to: " + ex.getMessage();
        }

        String errMsg = "";
        if(errCode != null)
            errMsg = MessageFormat.format(errCode.value(), args);

        String message = //header(session, CLASS, FN_NAME) +
                errMsg + excMsg;
//        sysLogger.error(message + "\n", ex);

        if (ex != null)
            logMsg(session, ERROR_EX, message, ex);
//            appLogger.error(message + "\n", ex);
        else
            logMsg(session, ERROR, message, null);
//            appLogger.error(message + "\n");
    }

    public void info(AppSession session, IC infoCode, Object ... args){
        String infoMsg = "";

        if(session != null)
            infoMsg = session.getInfoHandler().getMsg(session, infoCode, args);
        else
            appLogger.error(MessageFormat.format(AME.SYS_011.value(), "Info", "Info", infoCode.toString()));

        if(infoMsg == null)
            appLogger.error(MessageFormat.format(AME.SYS_010.value(), "Info", infoCode.toString()));
        else {
            String message = //CLASS + "." + FN_NAME + "(): " + 
                    infoMsg;
            logMsg(session, INFO, message, null);
//            appLogger.info(message + "\n");
        }
    }
    public void info(AppSession session, AMI infoMsg, Object ... args){
        String message = //header(session, CLASS, FN_NAME) +
                MessageFormat.format(infoMsg.value(), args);
        logMsg(session, INFO, message, null);
//        sysLogger.info(message + "\n");
    }

    public void startDebug(AppSession session, Object ... arguments){
        String message = //header(session, CLASS, FN_NAME) +
                "Started with ";

        if (arguments.length == 0)
            message += "No Arguments";
        else {
            message += "Arguments = ";
            for (Object arg : arguments)
                message += Arrays.toString(arguments) + "\n";
        }

        logMsg(session, DEBUG, message.substring(0, message.length() - 1), null);
//        appLogger.debug(message + "\n");
    }
    public void endDebug(AppSession session){
//        String message = "" + CLASS + "." + FN_NAME + "(): Ended [Void Function]";
        String message = "Ended [Void Function]";

        logMsg(session, DEBUG, message, null);
//        appLogger.debug(message + "\n");
    }
    public void endDebug(AppSession session, Object result){
        String message = //header(session, CLASS, FN_NAME) +
                "Ended with Results = " + (result != null ? result.toString() : "Null");

        logMsg(session, DEBUG, message, null);        
//        appLogger.debug(message + "\n");
    }

//    private static String header(AppSession session){
//        return session.toString() + CLASS + "." + FN_NAME + "(): \n";
//    }

//    private static final AppSession session = new AppSession(Phase.INITIAL_APP, Source.AM);
//



//
//    public void startDebug(String CLASS, String FN_NAME, Object ... arguments){
//        startDebug(session, arguments);
////        String message = header(CLASS, FN_NAME) +
////                "Started with ";
////
////        if (arguments.length == 0)
////            message += "No Arguments";
////        else {
////            message += "Arguments = ";
////            for (Object arg : arguments)
////                message += (arg != null ? arg.toString() : "Null") + "\n";
////        }
////
////        sysLogger.debug(message + "\n");
//    }
//    public void endDebug(String CLASS, String FN_NAME){
////        endDebug(session, CLASS, FN_NAME);
//        String message = header(session, CLASS, FN_NAME) + "Ended [Void Function]";
//
//        sysLogger.debug(message + "\n");
//    }
//    public void endDebug(String CLASS, String FN_NAME, Object result){
////        endDebug(session, arguments);
//        String message = "Ended with Results = " + (result != null ? result.toString() : "Null");
//
//        sysLogger.debug(message + "\n");
//    }

    private void logMsg(AppSession session, String level, String msg, Exception ex){
        if(session != null) {
            try {
                switch (Source.valueOf(session.getSource())) {
                    case JMS: logMsg(session, jmsLogger, level, msg, ex); break;
                    case REST: logMsg(session, restLogger, level, msg, ex); break;
                    case SOAP: logMsg(session, soapLogger, level, msg, ex); break;
                    case DB: logMsg(session, dbLogger, level, msg, ex); break;
                    case Service: logMsg(session, serviceLogger, level, msg, ex); break;
                    case AM: logMsg(session, sysLogger, level, msg, ex); break;
                }
            }catch (Exception exc){
                logMsg(session, rootLogger, level, msg, ex);
            }
        }else
            logMsg(session, rootLogger, level, msg, ex);
    }

    private void logMsg(AppSession session, Logger logger, String level, String message, Exception ex){
        if(logger == null) {
            logMsg(session, rootLogger, level, message, ex);
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
