package am.services;

import am.data.hibernate.model.performance.ExecutionLog;
import am.data.hibernate.model.performance.codes.ErrorCode;
import am.data.hibernate.model.performance.session.Interface;
import am.data.hibernate.model.performance.session.Phase;
import am.data.hibernate.model.performance.session.Source;
import am.data.hibernate.model.performance.session.Thread;
import am.main.api.AppLogger;
import am.main.api.db.DBManager;
import am.main.data.dto.logger.AMFunLogData;
import am.main.session.AppSession;
import am.repository.PerformanceRepository;
import org.apache.commons.collections.map.HashedMap;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
public class PerformanceService {
    private final String CLASS = PerformanceService.class.getSimpleName();

    @Inject private AppLogger logger;
    @Inject private PerformanceRepository repository;
    @Inject private DBManager dbManager;

    @Transactional
    public void processLogPerformance(AppSession appSession, AMFunLogData logData) throws Exception{
        String METHOD = "";
        AppSession session = appSession.updateSession(CLASS, METHOD);
        logger.startDebug(session, logData);

        Map<Class, Object> newRecords = new HashedMap();

        ExecutionLog executionLog = new ExecutionLog();

        //Get the Thread Record from Database
        Thread thread = repository.getThread(session, logData.getSession().getTHREAD_NAME());
        if(thread == null){
            thread = dbManager.persist(session, new Thread(logData.getSession().getTHREAD_NAME(), logData.getSession().getTHREAD_ID()), true);
            newRecords.put(Thread.class, thread);
        }
        executionLog.setThread(thread);

        //Get the Phase Record from Database
        Phase phase = repository.getPhase(session, logData.getSession().getPHASE().value());
        if(phase == null){
            phase = dbManager.persist(session, new Phase(logData.getSession().getPHASE().value()), true);
            newRecords.put(Phase.class, phase);
        }
        executionLog.setPhase(phase);

        //Get the Interface Record from Database
        Interface _interface = repository.getInterface(session, logData.getSession().getINTERFACE().value());
        if(_interface == null){
            _interface = dbManager.persist(session, new Interface(logData.getSession().getINTERFACE().value()), true);
            newRecords.put(Interface.class, _interface);
        }
        executionLog.setInterfaceLog(_interface);
        executionLog.setInterfaceRelatedID(logData.getSession().getId());

        //Get the Source Record from Database
        Source source = repository.getSource(session, logData.getSession().getSOURCE().value());
        if(source == null){
            source = dbManager.persist(session, new Source(logData.getSession().getSOURCE().value()), true);
            newRecords.put(Source.class, source);
        }
        executionLog.setSource(source);

        //Get the Source Record from Database
        if(logData.getEc() != null) {
            ErrorCode errorCode = repository.getErrorCode(session, logData.getEc());
            if (source == null) {
                source = dbManager.persist(session, new ErrorCode(logData.getEc()), true);
                newRecords.put(Source.class, source);
            }
            executionLog.setSource(source);
        }




        logger.endDebug(session);
    }
}
