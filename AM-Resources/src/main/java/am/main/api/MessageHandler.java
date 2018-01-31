package am.main.api;

import am.main.common.ConfigUtils;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.AMCode;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static am.main.common.ConfigParam.*;
import static am.main.data.enums.Interface.INITIALIZING_COMPONENT;
import static am.main.data.enums.impl.AMP.AM_INITIALIZATION;
import static am.main.data.enums.impl.AMP.MESSAGE_HANDLER;
import static am.main.data.enums.impl.AMS.AM;
import static am.main.data.enums.impl.IEC.*;
import static am.main.data.enums.impl.IIC.*;

/**
 * Created by ahmed.motair on 1/8/2018.
 */
@Singleton
public class MessageHandler {
    @Inject private AppLogger logger;
    private static final String CLASS = MessageHandler.class.getSimpleName();

    private static MessageHandler instance;

    private MessageHandler() {

    }
    public static MessageHandler getInstance() throws Exception{
        if (instance == null){
            synchronized(MessageHandler.class){
                if (instance == null){
                    instance = new MessageHandler();
                    instance.load();
                }
            }
        }
        return instance;
    }

    private static Properties ERROR_MESSAGES = new Properties();
    private static Properties INFO_MESSAGES = new Properties();
    private static Properties WARNING_MESSAGES = new Properties();

    @PostConstruct
    private void load(){
        String METHOD = "load";
        AppSession session = new AppSession(AM, INITIALIZING_COMPONENT, AM_INITIALIZATION, CLASS, METHOD);
        String componentName = COMPONENT.MESSAGE_HANDLER;
        try {
            logger.info(session, I_SYS_1, componentName);

            ERROR_MESSAGES = ConfigUtils.readRemotePropertyFiles(session, logger, ERROR_MSG_CONFIG.FN_PATH);
            logger.info(session, I_MH_1);

            INFO_MESSAGES = ConfigUtils.readRemotePropertyFiles(session, logger, INFO_MSG_CONFIG.FN_PATH);
            logger.info(session, I_MH_2);

            WARNING_MESSAGES = ConfigUtils.readRemotePropertyFiles(session, logger, WARN_MSG_CONFIG.FN_PATH);
            logger.info(session, I_MH_3);

            logger.info(session, I_SYS_2, componentName);
        } catch (Exception ex) {
            logger.error(session, ex, E_SYS_1, componentName, ex.getMessage());
            throw new IllegalStateException(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()));
        }
    }

    public String getMsg(AppSession appSession, AMCode amCode, Object ... args) {
        String METHOD = "getMsg";
        AppSession session = appSession.updateSession(MESSAGE_HANDLER, METHOD);
        try {
            logger.startDebug(session, amCode);

            if (amCode == null)
                throw new GeneralException(session, E_SYS_4);

            logger.info(session, I_MH_8, amCode.getFullCode());

            String message = getRawMsg(session, amCode);
            message = formatMsg(session, message, args);

            logger.info(session, I_MH_9, amCode.getFullCode());
            logger.endDebug(session, message);
            return message;
        } catch(Exception ex){
            throw new IllegalArgumentException(E_SYS_3.getFullMsg(amCode.getFullCode(), ex.getMessage()));
        }
    }

    public String getRawMsg(AppSession appSession, AMCode amCode){
        String METHOD = "getRawMsg";
        AppSession session = appSession.updateSession(MESSAGE_HANDLER, METHOD);
        try {
            logger.startDebug(session, amCode);

            if (amCode == null)
                throw new GeneralException(session, E_SYS_4);

            logger.info(session, I_MH_4, amCode.getFullCode());

            Properties propertiesFile = new Properties();

            switch (amCode.getCodeType()){
                case ERROR: propertiesFile = ERROR_MESSAGES; break;
                case INFO: propertiesFile = INFO_MESSAGES; break;
                case WARN: propertiesFile = WARNING_MESSAGES; break;
                case CONFIGURATION: throw new GeneralException(session, E_MH_1);
            }

            String message = ConfigUtils.readValueFromPropertyFile(session, logger, propertiesFile,
                    amCode.getFullCode(), amCode.getCodeType().toString() + " Messages");

            logger.info(session, I_MH_5, amCode.getFullCode());
            logger.endDebug(session, message);
            return message;
        }catch (GeneralException ex){
            if(ex.getErrorCode().equals(E_IO_8))
                this.load();
            throw new IllegalArgumentException(E_SYS_2.getFullMsg(amCode.getFullCode(), ex.getMessage()));
        } catch(Exception ex){
            throw new IllegalArgumentException(E_SYS_2.getFullMsg(amCode.getFullCode(), ex.getMessage()));
        }
    }

    private String formatMsg(AppSession appSession, String message, Object ... args) throws Exception{
        String METHOD = "formatMsg";
        AppSession session = appSession.updateSession(MESSAGE_HANDLER, METHOD);
        try {
            logger.startDebug(session, message, args);
            logger.info(session, I_MH_6);

            Matcher matcher = Pattern.compile("\\{[0-9]+\\}").matcher(message);
            String _message = "";

            Set<String> placeHolders = new HashSet<>();
            while (matcher.find())
                placeHolders.add(matcher.group());

            if (placeHolders.size() == 0) {
                if (args == null || args.length == 0)
                    _message = message;
                else
                    throw new GeneralException(session, E_MH_2);
            } else if(args == null  || placeHolders.size() != args.length)
                throw new GeneralException(session, E_MH_4, (args != null ? Arrays.toString(args) : "Null"), placeHolders.size());
            else
                _message = MessageFormat.format(message, args);


            logger.info(session, I_MH_7);
            logger.endDebug(session, _message);
            return _message;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_MH_5, message);
        }
    }
}
