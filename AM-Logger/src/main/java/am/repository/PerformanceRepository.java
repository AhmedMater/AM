package am.repository;

import am.data.hibernate.model.performance.session.Interface;
import am.data.hibernate.model.performance.session.Phase;
import am.data.hibernate.model.performance.session.Source;
import am.data.hibernate.model.performance.session.Thread;
import am.main.api.AppLogger;
import am.main.api.db.DBManager;
import am.main.exception.DBException;
import am.main.session.AppSession;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static am.main.data.enums.impl.AME.E_DB_16;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
public class PerformanceRepository {
    private final String CLASS = PerformanceRepository.class.getSimpleName();

    @Inject private AppLogger logger;
    @Inject private DBManager dbManager;

    public Phase getPhase(AppSession appSession, String phaseName) throws Exception{
        String METHOD = "getPhase";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, phaseName);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Phase.PHASE_NAME, phaseName);

        Phase phase = null;

        try {
            phase = dbManager.getSingleResult(session, true, Phase.class, parameters);
        }catch (DBException ex){
            if(ex.getCode().equals(E_DB_16))
                throw ex;
        }

        logger.endDebug(session, phase);
        return phase;
    }

    public Thread getThread(AppSession appSession, String threadName) throws Exception{
        String METHOD = "getThread";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, threadName);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Thread.THREAD_NAME, threadName);

        Thread thread = null;

        try {
            thread = dbManager.getSingleResult(session, true, Thread.class, parameters);
        }catch (DBException ex){
            if(ex.getCode().equals(E_DB_16))
                throw ex;
        }

        logger.endDebug(session, thread);
        return thread;
    }

    public Interface getInterface(AppSession appSession, String interfaceName) throws Exception{
        String METHOD = "getInterface";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, interfaceName);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Interface.INTERFACE_NAME, interfaceName);

        Interface _interface = null;

        try {
            _interface = dbManager.getSingleResult(session, true, Interface.class, parameters);
        }catch (DBException ex){
            if(ex.getCode().equals(E_DB_16))
                throw ex;
        }

        logger.endDebug(session, _interface);
        return _interface;
    }

    public Source getSource(AppSession appSession, String sourceName) throws Exception{
        String METHOD = "getSource";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, sourceName);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Source.SOURCE_NAME, sourceName);

        Source source = null;

        try {
            source = dbManager.getSingleResult(session, true, Source.class, parameters);
        }catch (DBException ex){
            if(ex.getCode().equals(E_DB_16))
                throw ex;
        }

        logger.endDebug(session, source);
        return source;
    }
}
