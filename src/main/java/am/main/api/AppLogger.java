package am.main.api;

import am.main.data.dto.AMLogData;
import am.main.data.enums.AME;
import am.main.data.enums.AMI;
import am.main.data.jaxb.log4jData.Configuration;
import am.main.data.jaxb.log4jData.RollingFile;
import am.main.session.AppSession;
import am.shared.enums.notification.Category;
import am.shared.enums.EC;
import am.shared.enums.IC;
import am.shared.enums.Phase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unitils.thirdparty.org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import static am.main.common.ConfigParam.LOG4J2_FILE_NAME;
import static am.main.common.ConfigParam.TEMPLATE;
import static am.main.data.enums.AMI.*;
import static am.shared.enums.JMSQueues.LOG4J2;
import static am.shared.enums.Phase.APP_LOGGER;
import static am.shared.enums.Source.AM;
import static am.shared.enums.Source.AM_LOGGER;

@Singleton
public class AppLogger implements Serializable{
    @Inject private JMSManager jmsManager;
    private static final String CLASS = AppLogger.class.getSimpleName();

    public Logger FAILURE_LOGGER = LogManager.getLogger("Failure");
    private Logger INITIAL_LOGGER = LogManager.getLogger("Initial");
    private Map<String, Logger> appLoggers = new HashMap<>();
    private Map<String, Logger> amLoggers = new HashMap<>();

    private static AppLogger instance;
    private static final AppSession appSession = new AppSession(AM, APP_LOGGER, CLASS);

    public AppLogger() {
    }

    public static AppLogger getInstance(){
        if (instance == null){
            synchronized(AppLogger.class){
                if (instance == null){
                    instance = new AppLogger();
                }
            }
        }
        return instance;
    }

    private void addNewLogger(Phase loggerData){
        String METHOD = "addNewLogger";
        AppSession session = appSession.updateSession(METHOD);

        INITIAL_LOGGER.info(session + LOG_001.value());
        try {
            Configuration log4j2 = readLog4J2File();
            INITIAL_LOGGER.info(session + LOG_002.value());

            RollingFile templateRolling = new RollingFile();
            for (RollingFile rollingFile :log4j2.getAppenders().getRollingFile())
                if(rollingFile.getName().equals(TEMPLATE))
                    templateRolling = rollingFile;

            am.main.data.jaxb.log4jData.Logger templateLogger = new am.main.data.jaxb.log4jData.Logger();
            for (am.main.data.jaxb.log4jData.Logger logger : log4j2.getLoggers().getLogger())
                if (logger.getName().equals(TEMPLATE))
                    templateLogger = logger;

            if(loggerData.isEnabled()) {
                log4j2.addNewLogger(loggerData, templateLogger, templateRolling);
                INITIAL_LOGGER.info(session + MessageFormat.format(LOG_003.value(), loggerData.value()));
            }else
                INITIAL_LOGGER.info(session + MessageFormat.format(LOG_004.value(), loggerData.value()));

            writeLog4J2File(log4j2);
            INITIAL_LOGGER.info(session + LOG_006.value());

            //To Reload the Log4j2.xml
            ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
            INITIAL_LOGGER.info(session + LOG_007.value());

            Logger _logger  = LogManager.getLogger(loggerData.value());
            if(loggerData.category().name().equals(Category.AM.name()))
                amLoggers.put(loggerData.value(), _logger);
            else
                appLoggers.put(loggerData.value(), _logger);

            INITIAL_LOGGER.info(session + MessageFormat.format(LOG_008.value(), loggerData.value()));
        }catch (Exception ex){
            INITIAL_LOGGER.error(ex);
        }
    }
    private Configuration readLog4J2File() throws Exception{
        InputStream stream = AppLogger.class.getResourceAsStream(LOG4J2_FILE_NAME);
        String xml = IOUtils.toString(stream, "UTF-8");
        stream.close();
        return XMLHandler.parse(xml, Configuration.class);
    }
    private void writeLog4J2File(Configuration logger) throws Exception{
        String xml = XMLHandler.compose(logger, Configuration.class);

        URL log4j2URL = AppLogger.class.getResource(LOG4J2_FILE_NAME);
        File log4j2File = new File(log4j2URL.getPath());

        PrintWriter writer = new PrintWriter(log4j2File.getAbsolutePath(), "UTF-8");
        writer.write(xml);
        writer.flush();
    }

    public void error(AppSession session, Exception ex) {
        AMLogData logData = new AMLogData(session, ex);
        log(session, logData);
    }
    public void error(AppSession session, EC ec, Object ... args) {
        AMLogData logData = new AMLogData(session, ec, args);
        log(session, logData);
    }
    public void error(AppSession session, AME ame, Object ... args) {
        AMLogData logData = new AMLogData(session, ame, args);
        log(session, logData);
    }
    public void error(AppSession session, Exception ex, EC ec, Object ... args) {
        AMLogData logData = new AMLogData(session, ex, ec, args);
        log(session, logData);
    }
    public void error(AppSession session, Exception ex, AME ame, Object ... args) {
        AMLogData logData = new AMLogData(session, ex, ame, args);
        log(session, logData);
    }

    public void info(AppSession session, IC ic, Object ... args){
        AMLogData logData = new AMLogData(session, ic, args);
        log(session, logData);
    }
    public void info(AppSession session, AMI ami, Object ... args){
        AMLogData logData = new AMLogData(session, ami, args);
        log(session, logData);
    }

    public void startDebug(AppSession session, Object ... args){
        AMLogData logData = new AMLogData(session, args);
        log(session, logData);
    }
    public void endDebug(AppSession session){
        AMLogData logData = new AMLogData(session);
        log(session, logData);
    }
    public void endDebug(AppSession session, Object result){
        AMLogData logData = new AMLogData(session, result);
        log(session, logData);
    }

    public void log(AppSession session, AMLogData logData){
        if(session != null) {
            if(session.getPHASE() != null && session.getPHASE().category().name().equals(Category.AM.name())) {
                Logger logger = amLoggers.get(logData.getSession().getPHASE().value());
                if (logger == null) {
                    addNewLogger(logData.getSession().getPHASE());
                    logger = amLoggers.get(logData.getSession().getPHASE().value());
                }
                logData.logMsg(this, logger);
            }else if (session.getSOURCE() != null && session.getSOURCE().value().equals(AM_LOGGER.value())) {
                Logger logger = appLoggers.get(logData.getSession().getPHASE().value());
                if (logger == null) {
                    addNewLogger(logData.getSession().getPHASE());
                    logger = appLoggers.get(logData.getSession().getPHASE().value());
                }
                logData.logMsg(this, logger);
            } else {
                try {
                    logData.setSession(logData.getSession().removeMessageHandler());
                    jmsManager.sendMessage(LOG4J2, logData);
                } catch (Exception exc) {
                    logData.logMsg(this, FAILURE_LOGGER);
                    FAILURE_LOGGER.error(exc);
                }
            }
        }else
            logData.logMsg(this, FAILURE_LOGGER);
    }
}
