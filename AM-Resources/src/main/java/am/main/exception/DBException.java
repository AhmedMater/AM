package am.main.exception;

import am.main.session.AppSession;
import am.main.spi.AMCode;

import java.io.Serializable;

/**
 * Created by Ahmed on 3/14/2016.
 * DBException is a wrapper for all sql exception thrown by repositories
 */
public class DBException extends Exception implements Serializable {
    private AMCode code;
    private String CLASS;
    private String METHOD;

    public DBException(AppSession session, Throwable ex){
        super(session.toString(), ex);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }
    public DBException(AppSession session, AMCode code, Object ...args){
        this(session, null, code, args);
    }
    public DBException(AppSession session, Throwable ex, AMCode code, Object ...args){
        super(session.toString() + code.getFullMsg(session.getMessageHandler(), args), ex);
        this.code = code;
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
    }

    public AMCode getCode() {
        return code;
    }
    public void setCode(AMCode code) {
        this.code = code;
    }

    public String getCLASS() {
        return CLASS;
    }
    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getMETHOD() {
        return METHOD;
    }
    public void setMETHOD(String METHOD) {
        this.METHOD = METHOD;
    }

}
