package am.session;

import am.api.error.EC;
import am.api.error.ErrorHandler;
import am.api.info.IC;
import am.api.info.InfoHandler;
import am.common.enums.AME;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public class AppSession implements Serializable{
    private String appPhase;
    private String source;
    private String userName;
    private String id;
    private String CLASS;
    private String method;
    private ErrorHandler errorHandler;
    private InfoHandler infoHandler;

    public AppSession(){}
//    public AppSession(Phase appPhase, Source source){
//        this(appPhase, source, null, null, null, null ,null, null);
//    }
    public AppSession(Phase appPhase, Source source, String CLASS, String method){
        this(appPhase, source, null, null, CLASS, method, null, null);
    }
//    public AppSession(Phase appPhase, Source source, String id, ErrorHandler errorHandler, InfoHandler infoHandler){
//        this(appPhase, source, null, id, null, null, errorHandler, infoHandler);
//    }
//    public AppSession(Phase appPhase, Source source, ErrorHandler errorHandler, InfoHandler infoHandler){
//        this(appPhase, source, null, null, errorHandler, infoHandler);
//    }

    public AppSession(Phase appPhase, Source source, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this(appPhase, source, null, id, CLASS, method, errorHandler, infoHandler);
    }

    public AppSession(Phase appPhase, Source source, String userName, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this.appPhase = appPhase.value();
        this.source = source.value();
        this.userName = userName;
        this.id = id;
        this.CLASS = CLASS;
        this.method = method;
        this.errorHandler = errorHandler;
        this.infoHandler = infoHandler;
    }

    public String getAppPhase() {
        return appPhase;
    }
    public void setAppPhase(String appPhase) {
        this.appPhase = appPhase;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    public String getErrorMsg(EC errorCode, Object ... args) {
        try{
            return errorHandler.getMsg(this, errorCode, args);
        }catch (Exception ex){
            return MessageFormat.format(AME.SYS_012.value(), "Error", errorCode.toString(), ex.getMessage());
        }
    }

    public InfoHandler getInfoHandler() {
        return infoHandler;
    }
    public void setInfoHandler(InfoHandler infoHandler) {
        this.infoHandler = infoHandler;
    }
    public String getInfoMsg(IC infoCode, Object ... args) {
        try{
            return infoHandler.getMsg(this, infoCode, args);
        }catch (Exception ex){
            return MessageFormat.format(AME.SYS_012.value(), "Info", infoCode.toString(), ex.getMessage());
        }
    }

    public String getCLASS() {
        return CLASS;
    }
    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public AppSession updateSession(Source source, String CLASS, String method){
        AppSession session = new AppSession();
        session.appPhase = this.appPhase;
        session.source = source.value();
        session.CLASS = CLASS;
        session.method = method;
        session.userName = this.userName;
        session.id = this.id;
        session.errorHandler = this.errorHandler;
        session.infoHandler = this.infoHandler;
        return session;
    }
    public AppSession updateSession(String CLASS, String method){
        AppSession session = new AppSession();
        session.appPhase = this.appPhase;
        session.source = this.source;
        session.CLASS = CLASS;
        session.method = method;
        session.userName = this.userName;
        session.id = this.id;
        session.errorHandler = this.errorHandler;
        session.infoHandler = this.infoHandler;
        return session;
    }
    public AppSession updateSession(Phase phase, Source source, String CLASS, String method){
        AppSession session = new AppSession();
        session.appPhase = phase.value();
        session.source = source.value();
        session.CLASS = CLASS;
        session.method = method;
        session.userName = this.userName;
        session.id = this.id;
        session.errorHandler = this.errorHandler;
        session.infoHandler = this.infoHandler;
        return session;
    }
    public AppSession updateSession(String method){
        AppSession session = new AppSession();
        session.appPhase = this.appPhase;
        session.source = this.source;
        session.CLASS = this.CLASS;
        session.method = method;
        session.userName = this.userName;
        session.id = this.id;
        session.errorHandler = this.errorHandler;
        session.infoHandler = this.infoHandler;
        return session;
    }

    @Override
    public String toString() {
        String st = "[" + appPhase + "] [" + source;
        if(userName != null)
            st += "] [" + userName;
        if(id !=null)
            st += "] [" + id;
        st += "]\n " + CLASS + "." + method + "(): ";
        return st;
    }
}
