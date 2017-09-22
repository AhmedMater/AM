package am.exception;

import am.api.error.EC;
import am.core.logger.AME;
import am.session.AppSession;

import java.text.MessageFormat;

/**
 * Created by ahmed.motair on 1/29/2017.
 */
public class GeneralException extends Exception {
    private EC errorCode;
    private String CLASS;
    private String METHOD;

    public GeneralException(AppSession session, String CLASS, String METHOD, Throwable ex){
        this(session, CLASS, METHOD, ex, null);
    }
    public GeneralException(AppSession session, String CLASS, String METHOD, EC errorCode, Object ... args){
        this(session, CLASS, METHOD, null, errorCode, args);
    }
    public GeneralException(AppSession session, String CLASS, String METHOD, Throwable ex, EC errorCode, Object ... args){
        super(CLASS + "." + METHOD + "(): " + session.getErrorHandler().getMsg(errorCode, args), ex);
        this.errorCode = errorCode;
        this.CLASS = CLASS;
        this.METHOD = METHOD;
    }

    public GeneralException(String CLASS, String METHOD, AME errorCode, Object ... args){
        this(CLASS, METHOD, null, errorCode, args);
    }
    public GeneralException(String CLASS, String METHOD, Throwable ex, AME errorCode, Object ... args){
        super(CLASS + "." + METHOD + "(): " + MessageFormat.format(errorCode.value(), args), ex);
        this.CLASS = CLASS;
        this.METHOD = METHOD;
    }
}
