package am.api.logger;

import am.api.error.EC;
import am.api.info.IC;
import am.core.logger.AME;
import am.exception.BusinessException;
import am.session.AppSession;
import org.apache.logging.log4j.LogManager;

import javax.inject.Singleton;
import java.io.Serializable;
import java.text.MessageFormat;

@Singleton
public class AppLogger implements Serializable{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger("AMT");

    public static void error(AppSession session, String CLASS, String FN_NAME, Exception ex) {
        error(session, CLASS, FN_NAME, ex, null);
    }
    public static void error(AppSession session, String CLASS, String FN_NAME, EC errCode, Object ... args) {
        error(session, CLASS, FN_NAME, null, errCode, args);
    }
    public static void error(AppSession session, String CLASS, String FN_NAME, Exception ex, EC errCode, Object ... args) {
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
                errMsg = session.getErrorHandler().getMsg(errCode, args);
            else
                logger.error(MessageFormat.format(AME.SYS_011.value(), "Error", "Error", errCode.toString()));
        }

        if(errMsg == null)
            logger.error(MessageFormat.format(AME.SYS_010.value(), "Error", errCode.toString()));
        else {
            String message = header(session, CLASS, FN_NAME) + errMsg + excMsg;

            if (ex != null)
                logger.error(message + "\n", ex);
            else
                logger.error(message + "\n");
        }
    }

    public static void info(AppSession session, String CLASS, String FN_NAME, IC infoCode, Object ... args){
        String infoMsg = "";

        if(session != null)
            session.getInfoHandler().getMsg(infoCode, args);
        else
            logger.error(MessageFormat.format(AME.SYS_011.value(), "Info", "Info", infoCode.toString()));

        if(infoMsg == null)
            logger.error(MessageFormat.format(AME.SYS_010.value(), "Info", infoCode.toString()));
        else {
            String message = CLASS + "." + FN_NAME + "(): " + infoMsg;
            logger.info(message + "\n");
        }
    }

    public static void startDebug(AppSession session, String CLASS, String FN_NAME, Object ... arguments){
        String message = header(session, CLASS, FN_NAME) +
                "Started with ";

        if (arguments.length == 0)
            message += "No Arguments";
        else {
            message += "Arguments = ";
            for (Object arg : arguments)
                message += (arg != null ? arg.toString() : "Null") + "\n";
        }

        logger.debug(message + "\n");
    }
    public static void endDebug(AppSession session, String CLASS, String FN_NAME){
        String message = "" + CLASS + "." + FN_NAME + "(): Ended [Void Function]";

        logger.debug(message + "\n");
    }
    public static void endDebug(AppSession session, String CLASS, String FN_NAME, Object result){
        String message = header(session, CLASS, FN_NAME) +
                "Ended with Results = " + (result != null ? result.toString() : "Null");

        logger.debug(message + "\n");
    }

    private static String header(AppSession session, String CLASS, String FN_NAME){
        return session.toString() + CLASS + "." + FN_NAME + "(): \n";
    }
}
