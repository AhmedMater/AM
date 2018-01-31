package am.main.session;

import am.main.api.MessageHandler;
import am.main.data.enums.Interface;
import am.main.spi.AMPhase;
import am.main.spi.AMSource;

import java.io.Serializable;

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
//    public String getErrorMsg(AMCode code, Object ... args) {
//        try {
//            return messageHandler.getMsg(code, args);
//        } catch (Exception e) {
//            return MessageFormat.format(AME.SYS_012.value(), "Error", code.toString());
//        }
//    }

//    public String getErrorMsg(EC code, Object ... args) {
//        try {
//            return messageHandler.getMsg(code, args);
//        } catch (Exception e) {
//            return MessageFormat.format(AME.SYS_012.value(), "Error", code.toString());
//        }
//    }
//    public String getInfoMsg(IC code, Object ... args) {
//        try {
//            return messageHandler.getMsg(code, args);
//        } catch (Exception e) {
//            return MessageFormat.format(AME.SYS_012.value(), "Info", code.toString());
//        }
//    }
//    public String getWarnMsg(WC code, Object ... args) {
//        try {
//            return messageHandler.getMsg(code, args);
//        } catch (Exception e) {
//            return MessageFormat.format(AME.SYS_012.value(), "Warn", code.toString());
//        }
//    }

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

    public AppSession updateSession(AMSource SOURCE, AMPhase PHASE, String CLASS, String METHOD) {
        AppSession session = new AppSession();
        session.PHASE = PHASE;
        session.CLASS = CLASS;
        session.METHOD = METHOD;
        session.SOURCE = SOURCE;
        session.INTERFACE = this.INTERFACE;
        session.username = this.username;
        session.id = this.id;
        session.messageHandler = this.messageHandler;
        return session;
    }
    public AppSession updateSession(AMPhase PHASE, String CLASS, String METHOD) {
        AppSession session = new AppSession();
        session.PHASE = PHASE;
        session.CLASS = CLASS;
        session.METHOD = METHOD;
        session.SOURCE = this.SOURCE;
        session.INTERFACE = this.INTERFACE;
        session.username = this.username;
        session.id = this.id;
        session.messageHandler = this.messageHandler;
        return session;
    }
    public AppSession updateSession(AMPhase PHASE, String METHOD) {
        AppSession session = new AppSession();
        session.PHASE = PHASE;
        session.METHOD = METHOD;
        session.CLASS = this.CLASS;
        session.SOURCE = this.SOURCE;
        session.INTERFACE = this.INTERFACE;
        session.username = this.username;
        session.id = this.id;
        session.messageHandler = this.messageHandler;
        return session;
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
        return session;
    }

    @Override
    public String toString() {
        String st = "";

        if(THREAD_NAME != null)
            st += "[Thread: " + THREAD_NAME;
        else
            st += "[ThreadID: ";

        if(THREAD_ID != null)
            st += "::" + THREAD_ID + "] \n";
        else
            st += "] \n";

        if(SOURCE != null)
            st += "[Source: " + SOURCE.getName();

        if(INTERFACE != null)
            st += "] [Interface: " + INTERFACE.value();

        if(ip != null)
            st += "] [IP: " + ip;

        if(username != null)
            st += "] [User: " + username;

        if(id !=null)
            st += "] [ID: " + id;

        st += "]\n " + CLASS + "." + METHOD + "(): ";
        return st;
    }
}
