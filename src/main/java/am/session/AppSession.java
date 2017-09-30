package am.session;

import am.api.components.ErrorHandler;
import am.api.components.InfoHandler;
import am.api.enums.EC;
import am.api.enums.IC;

import java.io.Serializable;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public class AppSession implements Serializable{
    private Source source;
    private Interface INTERFACE;
    private Phase phase;
    private String userName;
    private String id;
    private String CLASS;
    private String method;
    private ErrorHandler errorHandler;
    private InfoHandler infoHandler;

    public AppSession(){}
    public AppSession(Source source, Interface INTERFACE, Phase phase, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this(source, INTERFACE, phase, null, null, CLASS, method, errorHandler, infoHandler);
    }
    public AppSession(Source source, Interface INTERFACE, Phase phase, String CLASS, String method) {
        this(source, INTERFACE, phase, null, null, CLASS, method, null, null);
    }
    public AppSession(Source source, Interface INTERFACE, Phase phase, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this(source, INTERFACE, phase, null, id, CLASS, method, errorHandler, infoHandler);
    }
    public AppSession(Source source, Interface INTERFACE, Phase phase, String userName, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this.source = source;
        this.INTERFACE = INTERFACE;
        this.phase = phase;
        this.userName = userName;
        this.id = id;
        this.CLASS = CLASS;
        this.method = method;
        this.errorHandler = errorHandler;
        this.infoHandler = infoHandler;
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
        return errorHandler.getMsg(this, errorCode, args);
    }

    public InfoHandler getInfoHandler() {
        return infoHandler;
    }
    public void setInfoHandler(InfoHandler infoHandler) {
        this.infoHandler = infoHandler;
    }
    public String getInfoMsg(IC infoCode, Object ... args) {
        return infoHandler.getMsg(this, infoCode, args);
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

    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }

    public Interface getINTERFACE() {
        return INTERFACE;
    }
    public void setINTERFACE(Interface INTERFACE) {
        this.INTERFACE = INTERFACE;
    }

    public Phase getPhase() {
        return phase;
    }
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        String st = "[" + source.value() + "] [" + INTERFACE.value() + "] [" + phase.value();
        if(userName != null)
            st += "] [" + userName;
        if(id !=null)
            st += "] [" + id;
        st += "]\n " + CLASS + "." + method + "(): ";
        return st;
    }

    public AppSession updateSession(Phase phase, String CLASS, String method) {
        AppSession session = new AppSession();
        session.phase = phase;
        session.CLASS = CLASS;
        session.method = method;
        session.source = this.source;
        session.INTERFACE = this.INTERFACE;
        session.userName = this.userName;
        session.id = this.id;
        session.errorHandler = this.errorHandler;
        session.infoHandler = this.infoHandler;
        return session;
    }
    public AppSession updateSession(String method) {
        return updateSession(this.phase, this.CLASS, method);
    }
    public AppSession updateSession(String CLASS, String method) {
        return updateSession(this.phase, CLASS, method);
    }
}
