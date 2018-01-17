package am.main.exception;

import am.main.data.enums.AME;
import am.main.session.AppSession;
import am.shared.enums.EC;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by Ahmed on 3/14/2016.
 * DBException is a wrapper for all sql exception thrown by repositories
 */
public class DBException extends Exception implements Serializable {
    private EC EC_CODE;
    private AME AME_CODE;
    private String CLASS;
    private String METHOD;

    public DBException(AppSession session, Throwable ex){
        super(session.toString(), ex);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }
    public DBException(AppSession session, EC EC_CODE, Object ...args){
        this(session, null, EC_CODE, args);
    }
    public DBException(AppSession session, Throwable ex, EC EC_CODE, Object ...args){
        super(session.toString() + session.getErrorMsg(EC_CODE, args), ex);
        this.EC_CODE = EC_CODE;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }

    public DBException(AppSession session, AME EC_CODE, Object ... args){
        this(session, null, EC_CODE, args);
    }
    public DBException(AppSession session, Throwable ex, AME AME_CODE, Object ... args){
        super(session.toString() + MessageFormat.format(AME_CODE.value(), args), ex);
        this.AME_CODE = AME_CODE;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }

    public EC getEC_CODE() {
        return EC_CODE;
    }
    public void setEC_CODE(EC EC_CODE) {
        this.EC_CODE = EC_CODE;
    }

    public AME getAME_CODE() {
        return AME_CODE;
    }
    public void setAME_CODE(AME AME_CODE) {
        this.AME_CODE = AME_CODE;
    }
}
