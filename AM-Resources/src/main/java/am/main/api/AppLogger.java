package am.main.api;

import am.main.common.ConfigParam;
import am.main.common.ConfigUtils;
import am.main.data.dto.logger.AMFunLogData;
import am.main.data.enums.impl.AMQ;
import am.main.data.enums.logger.LogConfigProp;
import am.main.data.jaxb.am.logger.*;
import am.main.data.jaxb.log4jData.Configuration;
import am.main.data.jaxb.log4jData.RollingFile;
import am.main.exception.GeneralException;
import am.main.session.AppSession;
import am.main.spi.AMCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static am.main.common.ConfigParam.APP_NAME;
import static am.main.common.ConfigParam.COMPONENT;
import static am.main.data.enums.Interface.INITIALIZING_COMPONENT;
import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;
import static am.main.data.enums.impl.AMP.AM_INITIALIZATION;
import static am.main.data.enums.impl.AMP.JMS_MANAGER;
import static am.main.data.enums.impl.AMS.AM;
import static am.main.data.enums.impl.AMS.AM_LOGGER;
import static am.main.data.enums.impl.AMW.W_LOG_7;
import static am.main.data.enums.impl.AMW.W_LOG_8;
import static am.main.data.enums.logger.LogConfigProp.*;
import static am.main.data.enums.logger.LoggerLevels.*;
import static java.nio.charset.StandardCharsets.UTF_8;

@Singleton
public class AppLogger implements Serializable{

    private static final String CLASS = AppLogger.class.getSimpleName();

    public Logger FAILURE_LOGGER = LogManager.getLogger("Failure");
    private Logger INITIAL_LOGGER = LogManager.getLogger("Initial");
    private Logger JMS_LOGGER = LogManager.getLogger("JMS-Msg");
    private Map<String, Logger> loggers = new HashMap<>();

    private static final String LOG4J2_FILE_NAME = "/log4j2.xml";
    private static final String TEMPLATE = "Template";

    @Inject private Provider<JMSManager> jmsManager;

    private Boolean USE_AM_LOGGER_APP = false;
    private Boolean USE_PERFORMANCE_LOGGER = false;

    private static AppLogger instance;

    public AppLogger() {
    }

    public static AppLogger getInstance(){
        if (instance == null){
            synchronized(AppLogger.class){
                if (instance == null){
                    instance = new AppLogger();
                    instance.load();
                }
            }
        }
        return instance;
    }

    @PostConstruct
    private void load() {
        String METHOD = "load";
        AppSession session = new AppSession(AM, INITIALIZING_COMPONENT, AM_INITIALIZATION, CLASS, METHOD);
        String componentName = COMPONENT.APP_LOGGER;
        try {
            this.info(session, I_SYS_1, componentName);
            AMLogger externalConfig = null;

            try {
                externalConfig = ConfigUtils.readRemoteXMLFile(session, this, AMLogger.class, ConfigParam.LOGGER_CONFIG.FN_PATH);
                this.info(session, I_LOG_2);
            }catch (GeneralException exc){
                if(exc.getErrorCode().equals(E_IO_3)) {
//                    this.warn(session, W_LOG_1);
                    throw new GeneralException(session, E_LOG_6);
                }else
                    throw exc;
            }

//            if(externalConfig == null)
//                throw new GeneralException(session, E_LOG_6);

            AMLoggerConfig loggerConfig = checkLoggerConfig(session, externalConfig.getAMLoggerConfig());
            List<LoggerProperty> loggerPropertyList = loggerConfig.getLoggerProperty();

            String globalLogLevel = "";
            for (LoggerProperty property :loggerPropertyList) {
                if (property.getName().equals(USE_AM_LOGGER.getName())) {
                    if (!property.getValue().toLowerCase().trim().equals(USE_AM_LOGGER.getDefaultValue()))
                        USE_AM_LOGGER_APP = true;
                }else if(property.getName().equals(LOG_LEVEL_FOR_ALL.getName())){
                    if (!property.getValue().toLowerCase().trim().equals(LOG_LEVEL_FOR_ALL.getDefaultValue()))
                        globalLogLevel = property.getValue().trim().toLowerCase();
                }else if(property.getName().equals(LOG_PERFORMANCE.getName())){
                    if (!property.getValue().toLowerCase().trim().equals(LOG_PERFORMANCE.getDefaultValue()))
                        USE_PERFORMANCE_LOGGER = true;
                }
            }

            List<AMApplication> applicationList = new ArrayList<>();

            if(APP_NAME.equals(AM_LOGGER.getName()) && USE_AM_LOGGER_APP){
                applicationList = externalConfig.getAMApplications().getAMApplication();
            }else if(!USE_AM_LOGGER_APP){
                applicationList = externalConfig.getAMApplications().getAMApplication();
            }

            if(applicationList.size() != 0){
                Configuration log4j2 = ConfigUtils.readResourceXMLFile(session, this, Configuration.class,  "/TemplateLog4j2.xml");
                this.info(session, I_LOG_1);

                RollingFile templateRolling = new RollingFile();
                for (RollingFile rollingFile :log4j2.getAppenders().getRollingFile())
                    if(rollingFile.getName().equals(TEMPLATE))
                        templateRolling = rollingFile;

                am.main.data.jaxb.log4jData.Logger templateLogger = new am.main.data.jaxb.log4jData.Logger();
                for (am.main.data.jaxb.log4jData.Logger logger : log4j2.getLoggers().getLogger())
                    if (logger.getName().equals(TEMPLATE))
                        templateLogger = logger;

                for (AMApplication application :applicationList)
                    log4j2.addNewLogger(session, this, application, loggerConfig, templateLogger,
                            templateRolling, globalLogLevel);

                log4j2.removeTemplate(TEMPLATE);

                writeLog4J2File(session, log4j2);
                this.info(session, I_LOG_5);

                //To Reload the Log4j2.xml
                ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
                this.info(session, I_LOG_6);

                for (AMApplication application : applicationList) {
                    for (LoggerGroup group : application.getLoggerGroup())
                        for (LoggerData loggerData : group.getLoggerData()) {
                            loggers.put(loggerData.getName(), LogManager.getLogger(loggerData.getName()));
                            this.info(session, I_LOG_7, loggerData.getName());
                        }
                }
                loggers.remove(TEMPLATE);
                this.info(session, I_LOG_8);
            }

            this.info(session, I_SYS_2, componentName);
        } catch (Exception ex) {
            this.error(session, ex, E_SYS_1, componentName, ex.getMessage());
            throw new IllegalStateException(session + E_SYS_1.getFullMsg(componentName, ex.getMessage()));
        }
    }

    private AMLoggerConfig checkLoggerConfig(AppSession appSession, AMLoggerConfig externalLogConfig) throws Exception{
        String METHOD = "checkLoggerConfig";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        try {
            this.startDebug(session);
            this.info(session, I_LOG_12);

            for (LogConfigProp internalProp : LogConfigProp.values()) {
                boolean found = false;

                for (LoggerProperty externalProp : externalLogConfig.getLoggerProperty()) {
                    if(internalProp.getName().equals(externalProp.getName())){
                        found = true;

                        if(!externalProp.getValue().matches(internalProp.getRegex())) {
                            this.warn(session, W_LOG_8, externalProp.getValue(), externalProp.getName(), internalProp.getDefaultValue());
                            externalProp.setValue(internalProp.getDefaultValue());
                        }
                        break;
                    }
                }

                if(!found) {
                    this.warn(session, W_LOG_7, internalProp.getName(), internalProp.getDefaultValue());
                    externalLogConfig.getLoggerProperty().add(new LoggerProperty(internalProp.getName(), internalProp.getDefaultValue()));
                }
            }

            this.info(session, I_LOG_13);
            this.endDebug(session, externalLogConfig);
            return externalLogConfig;
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_LOG_2);
        }
    }

    private void writeLog4J2File(AppSession appSession, Configuration log4j2) throws Exception{
        String METHOD = "writeLog4J2File";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        try {
            this.startDebug(session, log4j2);
            this.info(session, I_IO_5, LOG4J2_FILE_NAME);

            String xml = XMLHandler.compose(log4j2, Configuration.class);

            try {
                PrintWriter writer = new PrintWriter(AppLogger.class.getResource(LOG4J2_FILE_NAME).getPath(), UTF_8.displayName());
                writer.write(xml);
                writer.flush();
            }catch (FileNotFoundException | NullPointerException e){
                throw new GeneralException(session, e, E_IO_3, LOG4J2_FILE_NAME);
            } catch (SecurityException e) {
                throw new GeneralException(session, e, E_IO_6, LOG4J2_FILE_NAME);
            }

            this.info(session, I_IO_6, LOG4J2_FILE_NAME);
            this.endDebug(session);
        }catch (Exception ex){
            if(ex instanceof GeneralException)
                throw ex;
            else
                throw new GeneralException(session, ex, E_IO_7, LOG4J2_FILE_NAME);
        }
    }

    public void error(AppSession session, Exception ex) {
        AMFunLogData logData = new AMFunLogData(session, ERROR_EX, ex);
        log(session, logData);
    }
    public void error(AppSession session, AMCode amCode, Object ... args) {
        AMFunLogData logData = new AMFunLogData(session, ERROR_MSG, amCode, args);
        log(session, logData);
    }
    public void error(AppSession session, Exception ex, AMCode ec, Object ... args) {
        AMFunLogData logData = new AMFunLogData(session, ERROR_MSG_EX, ex, ec, args);
        log(session, logData);
    }

    public void info(AppSession session, AMCode amCode, Object ... args){
        AMFunLogData logData = new AMFunLogData(session, INFO, amCode, args);
        log(session, logData);
    }

    public void warn(AppSession session, AMCode amCode, Object ... args){
        AMFunLogData logData = new AMFunLogData(session, WARN, amCode, args);
        log(session, logData);
    }

    public void startDebug(AppSession session, Object ... args){
        AMFunLogData logData = new AMFunLogData(session, args);
        log(session, logData);
    }
    public void endDebug(AppSession session){
        AMFunLogData logData = new AMFunLogData(session);
        log(session, logData);
    }
    public void endDebug(AppSession session, Object result){
        AMFunLogData logData = new AMFunLogData(session, result);
        log(session, logData);
    }

    public void log(AppSession session, AMFunLogData logData){
        if(session == null || session.getPHASE() == null || session.getSOURCE() == null)
            logData.logMsg(null, this, FAILURE_LOGGER);
        else{
            if(session.getPHASE().equals(AM_INITIALIZATION))
                logData.logMsg(session.getMessageHandler(), this, INITIAL_LOGGER);
            else if(session.getPHASE().equals(JMS_MANAGER))
                logData.logMsg(session.getMessageHandler(), this, JMS_LOGGER);
            else if(session.getSOURCE().equals(AM_LOGGER)) {
                Logger logger = loggers.get(logData.getPHASE());
                logger = (logger == null) ? loggers.get(APP_NAME) : logger;

                logData.logMsg(session.getMessageHandler(), this,
                        (logger != null ? logger : FAILURE_LOGGER));

                if(logger == null)
                    throw new IllegalStateException("Invalid Logger Selected");
            }else {
                try {
                    jmsManager.get().sendObjMessage(session, AMQ.FILE_LOG, logData);
                } catch (Exception exc) {
                    logData.logMsg(session.getMessageHandler(), this, FAILURE_LOGGER);
                    FAILURE_LOGGER.error(exc);
                }
            }
        }
    }

    public Boolean getUsePerformanceLogger() {
        return USE_PERFORMANCE_LOGGER;
    }
    public void setUsePerformanceLogger(Boolean USE_PERFORMANCE_LOGGER) {
        this.USE_PERFORMANCE_LOGGER = USE_PERFORMANCE_LOGGER;
    }
}
