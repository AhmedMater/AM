package am.main.data.dto;

import am.main.api.AppLogger;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.exception.BusinessException;
import am.main.exception.SerializedBusinessException;
import am.main.session.AppSession;
import am.shared.enums.EC;
import am.shared.enums.IC;
import am.shared.enums.LoggerLevels;
import am.shared.enums.WC;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static am.main.data.enums.AME.SYS_017;

/**
 * Created by ahmed.motair on 1/5/2018.
 */
public class AMLogData implements Serializable{
    private AppSession session;
    private LoggerLevels LEVEL;
    private List<String> args = new ArrayList<>();

    private WC wc;

    private IC ic;
    private AMI ami;

    private EC ec;
    private AME ame;
    private Exception ex;

    private String fullMsg = "";

    public AMLogData() {
    }
    public AMLogData(AppSession session, WC wc, Object ... args){
        this.LEVEL = LoggerLevels.WARN;
        this.session = session;
        this.wc = wc;
        this.setArgs(args);
    }
    public AMLogData(AppSession session, WC wc){
        this.LEVEL = LoggerLevels.WARN;
        this.session = session;
        this.wc = wc;
    }
    public AMLogData(AppSession session, IC ic, Object ... args){
        this.LEVEL = LoggerLevels.INFO;
        this.session = session;
        this.ic = ic;
        this.setArgs(args);
    }
    public AMLogData(AppSession session, IC ic){
        this.LEVEL = LoggerLevels.INFO;
        this.session = session;
        this.ic = ic;
    }
    public AMLogData(AppSession session, AMI ami, Object ... args){
        this.LEVEL = LoggerLevels.INFO;
        this.session = session;
        this.ami = ami;
        this.setArgs(args);
    }
    public AMLogData(AppSession session, AMI ami){
        this.LEVEL = LoggerLevels.INFO;
        this.session = session;
        this.ami = ami;
    }
    public AMLogData(AppSession session, Exception ex, EC ec, Object ... args) {
        this.LEVEL = LoggerLevels.ERROR_MSG_EX;
        this.session = session;
        this.ec = ec;
        this.ex = (ex instanceof BusinessException) ? new SerializedBusinessException((BusinessException) ex) : ex;
        this.setArgs(args);
    }
    public AMLogData(AppSession session, Exception ex) {
        this(session, ex, null);
        this.LEVEL = LoggerLevels.ERROR_EX;
    }
    public AMLogData(AppSession session, EC ec, Object ... args) {
        this(session, null, ec, args);
        this.LEVEL = LoggerLevels.ERROR_MSG;
    }
    public AMLogData(AppSession session, Object ... args){
        this.session = session;
        setArgs(args);
        this.LEVEL = LoggerLevels.ST_DEBUG;
    }
    public AMLogData(AppSession session){
        this.session = session;
        this.LEVEL = LoggerLevels.EN_DEBUG;
    }
    public AMLogData(AppSession session, Object result){
        this.session = session;
        setEndDebugArgs(result);
        this.LEVEL = LoggerLevels.EN_DEBUG;
    }

    public AppSession getSession() {
        return session;
    }
    public void setSession(AppSession session) {
        this.session = session;
    }

    public LoggerLevels getLEVEL() {
        return LEVEL;
    }
    public void setLEVEL(LoggerLevels LEVEL) {
        this.LEVEL = LEVEL;
    }

    public EC getEc() {
        return ec;
    }
    public void setEc(EC ec) {
        this.ec = ec;
    }

    public IC getIc() {
        return ic;
    }
    public void setIc(IC ic) {
        this.ic = ic;
    }

    public List<String> getArgs() {
        return args;
    }
    public void setEndDebugArgs(Object result) {
        this.args.add((result != null ? result.toString() : "Null"));
    }
    public void setArgs(Object[] args) {
        if(args != null)
            for (Object arg : args)
                this.args.add(arg.toString());
    }

    public Exception getEx() {
        return ex;
    }
    public void setEx(Exception ex) {
        this.ex = (ex instanceof BusinessException) ? new SerializedBusinessException((BusinessException) ex) : ex;
    }

    public String getFullMsg() {
        return fullMsg;
    }
    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }

    private boolean generateInfoMsg(AppLogger appLogger) throws Exception{
        if(ami != null)
            this.fullMsg = MessageFormat.format(ami.value(), args);
        else if(session != null)
            this.fullMsg = session.getInfoMsg(ic, args);
        else {
            appLogger.FAILURE_LOGGER.error(session.toString() + MessageFormat.format(AME.SYS_011.value(), "Message", "Info", ic.toString()));
            return false;
        }

        if(this.fullMsg == null) {
            appLogger.FAILURE_LOGGER.error(session.toString() + MessageFormat.format(AME.SYS_012.value(), "Info", ic.toString()));
            return false;
        }
        return true;
    }
    private boolean generateErrorMsg(AppLogger appLogger) throws Exception{
        String excMsg = "";

        if(ex != null) {
            if (ex instanceof SerializedBusinessException)
                excMsg = ((SerializedBusinessException) getEx()).getFormattedError();
            else
                excMsg = ex.getMessage();
        }

        String errMsg = "";
        if(ame != null)
            errMsg = MessageFormat.format(ame.value(), args);
        else if(ec != null) {
            if (session != null)
                errMsg = session.getErrorMsg(ec, args);
            else {
                appLogger.FAILURE_LOGGER.error(session.toString() + MessageFormat.format(AME.SYS_011.value(), "Message", "Error", ec.toString()));
                return false;
            }

            if(errMsg == null)
                appLogger.FAILURE_LOGGER.error(session.toString() + MessageFormat.format(AME.SYS_012.value(), "Error", ec.toString()));
        }else if(ex == null) {
            appLogger.FAILURE_LOGGER.error(session.toString() + AME.SYS_015.value());
            return false;
        }

        if (errMsg != null)
            fullMsg += errMsg;

        if (excMsg != null)
            fullMsg += ",\nDue to: " + excMsg;
        return true;
    }
    private boolean generateDebug(AppLogger appLogger) throws Exception{
        switch (this.LEVEL) {
            case ST_DEBUG:
                fullMsg = "Started with " +
                    ((args.size() == 0) ? "No Arguments" : "Arguments = " + Arrays.toString(args.toArray()) + "\n");
                break;
            case EN_DEBUG:
                fullMsg = "Ended " + (args.size() == 1 ?
                        "with Results = " + args.get(0) : "[Void Function]");
                break;
            default:
                appLogger.FAILURE_LOGGER.error(session.toString() + SYS_017.value());
                return false;

        }
        return true;
    }
    private boolean generateWarning(AppLogger appLogger) throws Exception{
        if (session != null) {
            fullMsg = session.getWarnMsg(wc, args);
            return true;
        }else {
            appLogger.FAILURE_LOGGER.error(session.toString() +
                    MessageFormat.format(AME.SYS_011.value(), "Warn", "Error", ec.toString()));
            return false;
        }
    }

    public void logMsg(AppLogger appLogger, Logger logger){
        try {
            String header = session.toString();
            if (logger == null)
                appLogger.FAILURE_LOGGER.error(header + "Logger Passed to log the Message is Null");

            switch (LEVEL) {
                case INFO:
                    if(generateInfoMsg(appLogger))
                        logger.info(header + fullMsg);
                    break;
                case ERROR_EX:
                    if(generateErrorMsg(appLogger)) {
                        if (ex instanceof SerializedBusinessException)
                            logger.error(header + fullMsg);
                        else
                            logger.error(header + fullMsg, ex);
                    }
                    break;
                case ERROR_MSG:
                    if(generateErrorMsg(appLogger))
                        logger.error(header + fullMsg);
                    break;
                case ERROR_MSG_EX:
                    if(generateErrorMsg(appLogger)) {
                        if (ex instanceof SerializedBusinessException)
                            logger.error(header + fullMsg);
                        else
                            logger.error(header + fullMsg, ex);
                    }
                    break;
                case ST_DEBUG:
                case EN_DEBUG:
                    if(generateDebug(appLogger))
                        logger.debug(header + fullMsg);
                    break;
                case WARN:
                    if(generateWarning(appLogger))
                        logger.warn(header + fullMsg);
                    break;
                default:
                    appLogger.FAILURE_LOGGER.error(header + SYS_017.value());
            }
        }catch (Exception ex){
            appLogger.FAILURE_LOGGER.error(ex);
        }
    }
}
