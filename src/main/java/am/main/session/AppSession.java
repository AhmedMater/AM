package am.main.session;

import am.main.api.ErrorHandler;
import am.main.api.InfoHandler;
import am.main.data.enums.Interface;
import am.main.data.enums.Source;
import am.shared.enums.EC;
import am.shared.enums.IC;
import am.shared.session.Phase;

import java.io.Serializable;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public class AppSession implements Serializable{
    private Source source;
    private Interface _interface;
    private Phase phase;
    private String ip;
    private String username;
    private String id;
    private String CLASS;
    private String method;
    private ErrorHandler errorHandler;
    private InfoHandler infoHandler;

    public AppSession(){}
    public AppSession(Source source, Interface _interface, Phase phase, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this(source, _interface, phase, null, null, CLASS, method, errorHandler, infoHandler, null);
    }
    public AppSession(Source source, Interface _interface, Phase phase, String CLASS, String method) {
        this(source, _interface, phase, null, null, CLASS, method, null, null, null);
    }
    public AppSession(Source source, Interface _interface, Phase phase, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler, String ip) {
        this(source, _interface, phase, null, id, CLASS, method, errorHandler, infoHandler, ip);
    }
    public AppSession(Source source, Interface _interface, Phase phase, String username, String id, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler, String ip) {
        this.source = source;
        this._interface = _interface;
        this.phase = phase;
        this.username = username;
        this.id = id;
        this.CLASS = CLASS;
        this.method = method;
        this.errorHandler = errorHandler;
        this.infoHandler = infoHandler;
        this.ip = ip;
    }
    public AppSession(Source source, Interface _interface, String CLASS, String method) {
        this(source, _interface, null, null, null, CLASS, method, null, null, null);
    }

    public AppSession(Source source, Phase phase, String CLASS, String method) {
        this(source, null, phase, null, null, CLASS, method, null, null, null);
    }

    public AppSession(Source source, Interface _interface, Phase phase, String CLASS, String method, ErrorHandler errorHandler, InfoHandler infoHandler, String ip) {
        this(source, _interface, phase, null, null, CLASS, method, errorHandler, infoHandler, ip);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

    public Interface getInterface() {
        return _interface;
    }
    public void setInterface(Interface _interface) {
        this._interface = _interface;
    }

    public Phase getPhase() {
        return phase;
    }
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        String st = "[SRC: " + source.value();

        if(username != null)
            st += "] [Interface: " + _interface.value();

        st += "] [Phase: " + phase.value();

        if(ip != null)
            st += "] [IP: " + ip;

        if(username != null)
            st += "] [User: " + username;

        if(id !=null)
            st += "] [ID: " + id;

        st += "]\n " + CLASS + "." + method + "(): ";
        return st;
    }

    public AppSession updateSession(Phase phase, String CLASS, String method) {
        AppSession session = new AppSession();
        session.phase = phase;
        session.CLASS = CLASS;
        session.method = method;
        session.source = this.source;
        session._interface = this._interface;
        session.username = this.username;
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
