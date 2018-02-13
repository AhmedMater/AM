package am.main.session;

import am.main.api.MessageHandler;
import am.main.data.enums.Interface;
import am.main.spi.AMPhase;
import am.main.spi.AMSource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public class AppSession implements Serializable{
    private AMSource SOURCE;
    private Interface INTERFACE;
    private AMPhase PHASE;
    private String ip;
    private String username;
    private String id;
    private String CLASS;
    private String THREAD_ID;
    private String THREAD_NAME;
    private String METHOD;
    private MessageHandler messageHandler;

    public AppSession(){}
    public AppSession(AMSource SOURCE, AMPhase PHASE, String CLASS) {
        this(SOURCE, null, PHASE, null, null, CLASS, null, null, null);
    }
    public AppSession(AMSource SOURCE, Interface INTERFACE, AMPhase PHASE){
        this(SOURCE, INTERFACE, PHASE, null, null, null, null, null, null);
    }
    public AppSession(AMSource SOURCE, Interface INTERFACE, AMPhase PHASE, String CLASS, String METHOD) {
        this(SOURCE, INTERFACE, PHASE, null, null, CLASS, METHOD, null, null);
    }
    public AppSession(AMSource SOURCE, Interface INTERFACE, AMPhase PHASE, String id, String CLASS, String METHOD, String ip, MessageHandler messageHandler) {
        this(SOURCE, INTERFACE, PHASE, null, id, CLASS, METHOD, ip, messageHandler);
    }
    public AppSession(AMSource SOURCE, Interface INTERFACE, AMPhase PHASE, String username, String id, String CLASS, String METHOD, String ip, MessageHandler messageHandler) {
        this.SOURCE = SOURCE;
        this.INTERFACE = INTERFACE;
        this.PHASE = PHASE;
        this.username = username;
        this.id = id;
        this.CLASS = CLASS;
        this.METHOD = METHOD;
        this.ip = ip;
        this.messageHandler = messageHandler;
        this.THREAD_ID = Long.toString(Thread.currentThread().getId());
        this.THREAD_NAME = Thread.currentThread().getName();
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

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
    public AppSession removeMessageHandler() {
        AppSession session = this.clone();
        session.messageHandler = null;
        return session;
    }

    public String getCLASS() {
        return CLASS;
    }
    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public AMSource getSOURCE() {
        return SOURCE;
    }
    public void setSOURCE(AMSource SOURCE) {
        this.SOURCE = SOURCE;
    }

    public Interface getINTERFACE() {
        return INTERFACE;
    }
    public void setINTERFACE(Interface INTERFACE) {
        this.INTERFACE = INTERFACE;
    }

    public AMPhase getPHASE() {
        return PHASE;
    }
    public void setPHASE(AMPhase PHASE) {
        this.PHASE = PHASE;
    }

    public String getMETHOD() {
        return METHOD;
    }
    public void setMETHOD(String METHOD) {
        this.METHOD = METHOD;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTHREAD_ID() {
        return THREAD_ID;
    }
    public void setTHREAD_ID(String THREAD_ID) {
        this.THREAD_ID = THREAD_ID;
    }

    public String getTHREAD_NAME() {
        return THREAD_NAME;
    }
    public void setTHREAD_NAME(String THREAD_NAME) {
        this.THREAD_NAME = THREAD_NAME;
    }

    public AppSession updateSession(AMPhase PHASE, String CLASS, String METHOD) {
        AppSession session = this.clone();
        session.PHASE = PHASE;
        session.CLASS = CLASS;
        session.METHOD = METHOD;
//        session.SOURCE = this.SOURCE;
//        session.INTERFACE = this.INTERFACE;
//        session.username = this.username;
//        session.id = this.id;
//        session.THREAD_ID = this.THREAD_ID;
//        session.THREAD_NAME = this.THREAD_NAME;
//        session.messageHandler = this.messageHandler;
        return session;
    }
    public AppSession updateSession(AMPhase PHASE, String METHOD) {
        return updateSession(PHASE, this.CLASS, METHOD);
    }
    public AppSession updateSession(String METHOD) {
        return updateSession(this.PHASE, this.CLASS, METHOD);
    }
    public AppSession updateSession(String CLASS, String METHOD) {
        return updateSession(this.PHASE, CLASS, METHOD);
    }

    @Override
    protected AppSession clone() {
        AppSession session = new AppSession();
        session.SOURCE = this.SOURCE;
        session.PHASE = this.PHASE;
        session.INTERFACE = this.INTERFACE;
        session.messageHandler = this.messageHandler;
        session.ip = this.ip;
        session.id = this.id;
        session.username = this.username;
        session.CLASS = this.CLASS;
        session.METHOD = this.METHOD;
        session.THREAD_ID = this.THREAD_ID;
        session.THREAD_NAME = this.THREAD_NAME;
        return session;
    }

    @Override
    public String toString() {
        String st = "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "] ";

        st += (THREAD_NAME != null) ? ("[Thread: " + THREAD_NAME +
                (THREAD_ID != null ? "::" + THREAD_ID + "] \n" : "]")) :
                (THREAD_ID != null ? "[Thread-ID: " + THREAD_ID + "] \n" : "");

        st += (SOURCE != null) ? "[Source: " + SOURCE.getName() + "] " : "";
        st += (INTERFACE != null) ? "[Interface: " + INTERFACE.value() + "] " : "";
        st += (PHASE != null) ? "[Phase: " + PHASE.getName() + "] \n" : "";

        st += (ip != null) ? "[IP: " + ip + "] " : "";
        st += (username != null) ? "[User: " + username + "] " : "";
        st += (id != null) ? "[ID: " + id + "] \n" : "";

        st += "[" + CLASS + "." + METHOD + "()] ";
        return st;
    }
}
