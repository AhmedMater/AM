package am.session;

import am.api.error.ErrorHandler;
import am.api.info.InfoHandler;

import java.io.Serializable;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public class AppSession implements Serializable{
    private String appPhase;
    private String source;
    private String userName;
    private String requestID;
    private ErrorHandler errorHandler;
    private InfoHandler infoHandler;

    public AppSession(){}
    public AppSession(Phase appPhase, Source source){
        this(appPhase, source, null, null);
    }
    public AppSession(Phase appPhase, Source source, ErrorHandler errorHandler, InfoHandler infoHandler){
        this(appPhase, source, null, null, errorHandler, infoHandler);
    }

    public AppSession(Phase appPhase, Source source, String userName, String requestID, ErrorHandler errorHandler, InfoHandler infoHandler) {
        this.appPhase = appPhase.value();
        this.source = source.value();
        this.userName = userName;
        this.requestID = requestID;
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

    public String getRequestID() {
        return requestID;
    }
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public InfoHandler getInfoHandler() {
        return infoHandler;
    }
    public void setInfoHandler(InfoHandler infoHandler) {
        this.infoHandler = infoHandler;
    }

    @Override
    public String toString() {
        String st = "[" + appPhase + "] [" + source;
        if(userName != null)
            st += "] [" + userName;
        if(requestID !=null)
            st += "] [" + requestID;
        return st + "] ";
    }
}
