package am.core.logger;

import am.exception.BusinessException;
import am.session.AppSession;
import am.session.Phase;
import am.session.Source;
import org.apache.logging.log4j.LogManager;

import java.text.MessageFormat;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
public class AMLogger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger("AM");
    private static final AppSession session = new AppSession(Phase.INITIAL_APP, Source.AM_API);
    
    public static void error(String CLASS, String FN_NAME, Exception ex) {
        String excMsg = "";
        if(ex instanceof BusinessException)
            excMsg = ((BusinessException) ex).getResponse().getEntity().toString();
        else
            excMsg  = ex.getMessage();
        String message = header(CLASS, FN_NAME) + "Due to: " + excMsg;

        logger.error(message + "\n",ex);
    }
    public static void error(String CLASS, String FN_NAME, Exception ex, AME errMsg, Object ... args) {
        String excMsg = "";
        if(ex instanceof BusinessException)
            excMsg = ((BusinessException) ex).getResponse().getEntity().toString();
        else
            excMsg  = ex.getMessage();

        String message = header(CLASS, FN_NAME) + MessageFormat.format(errMsg.value(), args) + "Due to: " + excMsg;
        logger.error(message + "\n", ex);
    }
    public static void error(String CLASS, String FN_NAME, AME errMsg, Object ... args) {


        String message = header(CLASS, FN_NAME) + MessageFormat.format(errMsg.value(), args);
        logger.error(message + "\n");
    }
    
    public static void info(String CLASS, String FN_NAME, AMI infoMsg, Object ... args){
        String message = header(CLASS, FN_NAME) + MessageFormat.format(infoMsg.value(), args);
        logger.info(message + "\n");
    }

    public static void startDebug(String CLASS, String FN_NAME, Object ... arguments){
        String message = header(CLASS, FN_NAME) +
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
    public static void endDebug(String CLASS, String FN_NAME){
        String message = header(CLASS, FN_NAME) + "Ended [Void Function]";

        logger.debug(message + "\n");
    }
    public static void endDebug(String CLASS, String FN_NAME, Object result){
        String message = header(CLASS, FN_NAME) +
                "Ended with Results = " + (result != null ? result.toString() : "Null");

        logger.debug(message + "\n");
    }
    
    private static String header(String CLASS, String FN_NAME){
        return session.toString() + CLASS + "." + FN_NAME + "(): \n";
    }

}
