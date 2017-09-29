package am.api.database;

import am.api.logger.AppLogger;
import am.common.ConfigParam;
import am.common.enums.AME;
import am.common.enums.AMI;
import am.core.config.AMConfigurationManager;
import am.exception.DBException;
import am.session.AppSession;
import am.session.Source;
import org.hibernate.annotations.QueryHints;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by ahmed.motair on 9/7/2017.
 */
@ApplicationScoped
public class DBManager implements Serializable {
    @Inject private AppLogger logger;
    @Inject private AMConfigurationManager amConfigManager;
    private final static String CLASS = "DBManager";
    private final static String DB_INSERT = "Insert";
    private final static String DB_UPDATE = "Update";
    private final static String DB_DELETE = "Delete";

    @PersistenceUnit(unitName = ConfigParam.PERSISTENCE_UNIT)
    private EntityManagerFactory emf;

    @PersistenceContext(name = ConfigParam.PERSISTENCE_UNIT)
    private EntityManager unCachedEM;

    public EntityManager getCachedEM() {
        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
    }
    public EntityManager getUnCachedEM() {
        return unCachedEM;
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    @Transactional
    public <T> T persist(AppSession appSession, T toBeInserted, Boolean usingCache) throws Exception {
        String FN_NAME = "persist";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        try {
            logger.startDebug(session, toBeInserted);
            try {
                EntityManager em;
                if(usingCache) {
                    em = getCachedEM();
                    em.persist(toBeInserted);
                    em.flush();
                    em.close();
                }else {
                    em = getUnCachedEM();
                    em.persist(toBeInserted);
                    em.flush();
                }
                logger.info(session, AMI.DB_001, toBeInserted.getClass().getSimpleName());
            } catch (EntityExistsException ex) {
                throw new DBException(session, ex, AME.DB_001, toBeInserted);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, AME.DB_002, toBeInserted.getClass().getSimpleName());
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, AME.DB_003);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, AME.DB_004, DB_INSERT);
            } catch (ConstraintViolationException ex) {
                Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

                List<String> errors = new ArrayList<>();
                for (ConstraintViolation<?> violation : violations)
                    errors.add(violation.getMessageTemplate());

                throw new DBException(session, ex, AME.DB_005, errors, toBeInserted);
            }

            logger.endDebug(session, toBeInserted);
            return toBeInserted;
        }catch (Exception ex){
            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, AME.DB_008, toBeInserted, ex.getMessage());
        }
    }

    public <T> T find(AppSession appSession, Class<T> className, Object identifier, Boolean usingCache)throws DBException {
        String FN_NAME = "find";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        try {
            logger.startDebug(session, className, identifier);

            T foundObject;
            try {
                EntityManager em;
                if(usingCache) {
                    em = getCachedEM();
                    foundObject = em.find(className, identifier);
                    em.close();
                }else {
                    em = getUnCachedEM();
                    foundObject = em.find(className, identifier);
                }
                logger.info(session, AMI.DB_002, className, identifier);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, AME.DB_006, className.toString(), identifier, className.getSimpleName());
            }

            logger.endDebug(session, foundObject);
            return foundObject;
        }catch (Exception ex){
            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, AME.DB_009, className.getSimpleName(), identifier, ex.getMessage());
        }
    }

    @Transactional
    public <T> T merge(AppSession appSession, T toBeUpdated, Boolean usingCache)throws DBException{
        String FN_NAME = "merge";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        try {
            logger.startDebug(session, toBeUpdated);

            try {
                EntityManager em;
                if(usingCache) {
                    em = getCachedEM();
                    toBeUpdated = em.merge(toBeUpdated);
                    em.flush();
                    em.close();
                }else {
                    em = getUnCachedEM();
                    toBeUpdated = em.merge(toBeUpdated);
                    em.flush();
                }
                logger.info(session, AMI.DB_003, toBeUpdated);
            }catch (IllegalArgumentException ex){
                throw new DBException(session, ex, AME.DB_007, toBeUpdated);
            }catch (TransactionRequiredException ex){
                throw new DBException(session, ex, AME.DB_003);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, AME.DB_004, DB_UPDATE);
            }

            logger.endDebug(session, toBeUpdated);
            return toBeUpdated;
        }catch (Exception ex){
            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, AME.DB_010, toBeUpdated, ex.getMessage());
        }
    }

    @Transactional
    public void remove(AppSession appSession, Object toBeRemoved, Boolean usingCache)throws DBException {
        String FN_NAME = "remove";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        EntityManager em = null;
        try {
            logger.startDebug(session, toBeRemoved);

            try {

                if(usingCache) {
                    em = getCachedEM();
                    if (!em.contains(toBeRemoved))
                        throw new DBException(session, AME.DB_002, toBeRemoved);

                    em.remove(toBeRemoved);
                    em.flush();
                    em.close();
                }else {
                    em = getUnCachedEM();
                    if (!em.contains(toBeRemoved))
                        throw new DBException(session, AME.DB_002, toBeRemoved);

                    em.remove(toBeRemoved);
                    em.flush();
                }

                logger.info(session, AMI.DB_004, toBeRemoved);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, AME.DB_007, toBeRemoved);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, AME.DB_003);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, AME.DB_004, DB_DELETE);
            }

            logger.endDebug(session);
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, AME.DB_011, toBeRemoved, ex.getMessage());
        }
    }

    public boolean checkIsFound(AppSession appSession, Query query) throws DBException {
        String FN_NAME = "checkIsFound";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        logger.startDebug(session, query);

        int numOfRows = query.getResultList().size();

        boolean isFound = false;

        if(numOfRows >= 1)
            isFound = true;
        logger.endDebug(session);
        return isFound;
    }

    public boolean checkIsFound(AppSession appSession, Boolean usingCache, String selectAttribute,
                Class entity, String conditionAttribute, String value) throws DBException {
        String FN_NAME = "checkIsFound";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        EntityManager em = null;
        try {
            logger.startDebug(session);

            boolean isFound = false;
            if (usingCache)
                em = getCachedEM();
            else
                em = getUnCachedEM();
            String queryStr = "SELECT " + selectAttribute + " FROM " + entity.getSimpleName() +
                    " WHERE " + conditionAttribute + " = :AttributeValue";

            Query query = em
                    .createQuery(queryStr)
                    .setParameter("AttributeValue", value);

            if (usingCache)
                query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);

            List result;

            try {
                result = query.getResultList();

                if (result.size() >= 1)
                    isFound = true;
            } catch (IllegalStateException ex) {
                throw new DBException(session, ex, AME.DB_017, queryStr);
            } catch (QueryTimeoutException ex) {
                throw new DBException(session, ex, AME.DB_018, queryStr);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, AME.DB_021, queryStr);
            } catch (PessimisticLockException ex) {
                throw new DBException(session, ex, AME.DB_019, queryStr);
            } catch (LockTimeoutException ex) {
                throw new DBException(session, ex, AME.DB_020, queryStr);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, AME.DB_022, queryStr);
            }

            logger.endDebug(session);
            return isFound;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, AME.DB_023, entity.getSimpleName(), ex.getMessage());
        }
    }

    public <T> T getSingleResult(AppSession appSession, Boolean usingCache,
                             Class<T> entity, String attribute, String value) throws Exception {
        String FN_NAME = "getSingleResult";
        AppSession session = appSession.updateSession(Source.DB, CLASS, FN_NAME);
        EntityManager em = null;
        try {
            logger.startDebug(session, usingCache, entity.getSimpleName(), attribute, value);

            if (usingCache)
                em = getCachedEM();
            else
                em = getUnCachedEM();

            String queryStr = "FROM " + entity.getSimpleName() + " WHERE " + attribute + " = :AttributeValue";
            TypedQuery<T> query = em
                    .createQuery(queryStr, entity)
                    .setParameter("AttributeValue", value);

            if (usingCache)
                query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);

            T result;

            try {
                result = (T) query.getSingleResult();
            } catch (NonUniqueResultException ex) {
                throw new DBException(session, AME.DB_015, entity.getSimpleName(), attribute, value);
            } catch (NoResultException ex) {
                throw new DBException(session, AME.DB_016, entity.getSimpleName(), attribute, value);
            } catch (QueryTimeoutException ex) {
                throw new DBException(session, ex, AME.DB_018, queryStr);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, AME.DB_021, queryStr);
            } catch (PessimisticLockException ex) {
                throw new DBException(session, ex, AME.DB_019, queryStr);
            } catch (LockTimeoutException ex) {
                throw new DBException(session, ex, AME.DB_020, queryStr);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, AME.DB_022, queryStr);
            }

            logger.endDebug(session);
            return result;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, AME.DB_024, entity.getSimpleName(), ex.getMessage());
        }
    }
}
