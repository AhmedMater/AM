package am.exception;

import am.api.error.EC;
import am.session.AppSession;

/**
 * Created by Ahmed on 3/14/2016.
 * DBException is a wrapper for all sql exception thrown by repositories
 */
public class DBException extends Exception {
    private EC errorCode;
    private String CLASS;
    private String METHOD;


    public DBException(AppSession session, String CLASS, String METHOD, Throwable ex){
        this(session, CLASS, METHOD, ex, null);
    }
    public DBException(AppSession session, String CLASS, String METHOD, EC errorCode, Object ...args){
        this(session, CLASS, METHOD, null, errorCode, args);
    }
    public DBException(AppSession session, String CLASS, String METHOD, Throwable ex, EC errorCode, Object ...args){
        super(CLASS + "." + METHOD + "(): \n" + session.getErrorHandler().getMsg(errorCode, args), ex);
        this.errorCode = errorCode;
        this.CLASS = CLASS;
        this.METHOD = METHOD;
    }
}
