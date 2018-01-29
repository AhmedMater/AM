package am.main.exception;

import am.main.session.AppSession;
import am.main.spi.AMCode;

import java.io.Serializable;

/**
 * Created by ahmed.motair on 1/29/2017.
 */
public class GeneralException extends Exception implements Serializable {
    private final AMCode ERROR_CODE;
    private final String CLASS;
    private final String METHOD;

//    public GeneralException(AppSession session, Throwable ex){
//        super(session.toString(), ex);
//        this.CLASS = session.getCLASS();
//        this.METHOD = session.getMETHOD();
//    }
    public GeneralException(AppSession session, AMCode errorCode, Object ... args){
        this(session, null, errorCode, args);
    }
    public GeneralException(AppSession session, Throwable ex, AMCode errorCode, Object ... args){
        super(session.toString() + errorCode.getFullMsg(session.getMessageHandler(), args), ex);
        this.CLASS = session.getCLASS();
        this.METHOD = session.getMETHOD();
        this.ERROR_CODE = errorCode;
    }

    public AMCode getErrorCode() {
        return ERROR_CODE;
    }

    public String getClassName() {
        return CLASS;
    }

    public String getMethod() {
        return METHOD;
    }

//    public GeneralException(AppSession session, AMCode errorCode, Object ... args){
//        this(session, null, errorCode, args);
//    }
//    public GeneralException(AppSession session, Throwable ex, AMCode errorCode, Object ... args){
//        super(session.toString() + session.getErrorMsg(errorCode, args), ex);
//        this.errorCode = errorCode;
//        this.CLASS = session.getCLASS();
//        this.METHOD = session.getMETHOD();
//    }
}
