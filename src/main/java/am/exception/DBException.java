package am.exception;

import am.api.enums.EC;
import am.common.enums.AME;
import am.session.AppSession;

import java.text.MessageFormat;

/**
 * Created by Ahmed on 3/14/2016.
 * DBException is a wrapper for all sql exception thrown by repositories
 */
public class DBException extends Exception {
    private EC errorCode;
    private String CLASS;
    private String METHOD;

    public DBException(AppSession session, Throwable ex){
        super(session.toString(), ex);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }
    public DBException(AppSession session, EC errorCode, Object ...args){
        this(session, null, errorCode, args);
    }
    public DBException(AppSession session, Throwable ex, EC errorCode, Object ...args){
        super(session.toString() + session.getErrorMsg(errorCode, args), ex);
        this.errorCode = errorCode;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }

    public DBException(AppSession session, AME errorCode, Object ... args){
        this(session, null, errorCode, args);
    }
    public DBException(AppSession session, Throwable ex, AME errorCode, Object ... args){
        super(session.toString() + MessageFormat.format(errorCode.value(), args), ex);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMethod();
    }
}
