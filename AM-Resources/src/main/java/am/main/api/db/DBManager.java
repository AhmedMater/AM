package am.main.api.db;

import am.main.api.AppLogger;
import am.main.exception.DBException;
import am.main.session.AppSession;
import org.hibernate.annotations.QueryHints;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.*;

import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;


/**
 * Created by ahmed.motair on 9/7/2017.
 */
@ApplicationScoped
public class DBManager implements Serializable {
    @Inject private AppLogger logger;

    private static final String CLASS = DBManager.class.getSimpleName();
//    private static final AppSession appSession = new AppSession(AM, DB_MANAGER, CLASS);

    private final static String DB_INSERT = "Insert";
    private final static String DB_UPDATE = "Update";
    private final static String DB_DELETE = "Delete";
    private final static String QUERY = "Query";

    @PersistenceUnit(unitName = "AM")
    private EntityManagerFactory emf;

    @PersistenceContext(name = "AM")
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
        String METHOD = "persist";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;

        try {
            logger.startDebug(session, toBeInserted);
            logger.info(session, I_DB_1, toBeInserted.getClass().getSimpleName());
            try {
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
                logger.info(session, I_DB_2, toBeInserted.getClass().getSimpleName());
            } catch (EntityExistsException ex) {
                throw new DBException(session, ex, E_DB_1, toBeInserted);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, E_DB_2, toBeInserted.getClass().getSimpleName());
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, E_DB_3);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_4, DB_INSERT);
            } catch (ConstraintViolationException ex) {
                Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

                List<String> errors = new ArrayList<>();
                for (ConstraintViolation<?> violation : violations)
                    errors.add(violation.getMessageTemplate());

                throw new DBException(session, ex, E_DB_5, errors, toBeInserted);
            }

            logger.endDebug(session, toBeInserted);
            return toBeInserted;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_8, toBeInserted);
        }
    }

    public <T> T find(AppSession appSession, Class<T> className, Object identifier, Boolean usingCache)throws DBException {
        String METHOD = "find";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;

        try {
            logger.startDebug(session, className, identifier);
            logger.info(session, I_DB_3, className, identifier);

            T foundObject;
            try {
                if(usingCache) {
                    em = getCachedEM();
                    foundObject = em.find(className, identifier);
                    em.close();
                }else {
                    em = getUnCachedEM();
                    foundObject = em.find(className, identifier);
                }
                logger.info(session, I_DB_4, className, identifier);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, E_DB_6, className.toString(), identifier, className.getSimpleName());
            }

            logger.endDebug(session, foundObject);
            return foundObject;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_9, className.getSimpleName(), identifier);
        }
    }
    public <T> List<T> findAll(AppSession appSession, Class<T> className, Boolean usingCache)throws DBException {
        String METHOD = "findAll";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;

        try {
            logger.startDebug(session, className);
            logger.info(session, I_DB_5, className);

            List<T> result;
            try {
                if(usingCache) {
                    em = getCachedEM();
                    result = em.createQuery("FROM " + className.getSimpleName(), className)
                            .setHint(QueryHints.CACHEABLE, true).getResultList();
                    em.close();
                }else {
                    em = getUnCachedEM();
                    result = em.createQuery("FROM " + className.getSimpleName(), className).getResultList();
                }
                logger.info(session, I_DB_6, className);
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, E_DB_26, className.getSimpleName());
            }

            logger.endDebug(session, result);
            return result;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_27, className.getSimpleName());
        }
    }

    @Transactional
    public <T> T merge(AppSession appSession, T toBeUpdated, Boolean usingCache)throws DBException{
        String METHOD = "merge";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;
        try {
            logger.startDebug(session, toBeUpdated);
            logger.info(session, I_DB_7, toBeUpdated.getClass().getSimpleName());

            try {
                if(usingCache) {
                    em = getCachedEM();

                    if (!em.contains(toBeUpdated))
                        throw new DBException(session, E_DB_28, toBeUpdated);

                    toBeUpdated = em.merge(toBeUpdated);
                    em.flush();
                    em.close();
                }else {
                    em = getUnCachedEM();

                    if (!em.contains(toBeUpdated))
                        throw new DBException(session, E_DB_28, toBeUpdated);

                    toBeUpdated = em.merge(toBeUpdated);
                    em.flush();
                }
                logger.info(session, I_DB_8, toBeUpdated.getClass().getSimpleName());
            }catch (IllegalArgumentException ex){
                throw new DBException(session, ex, E_DB_26, toBeUpdated);
            }catch (TransactionRequiredException ex){
                throw new DBException(session, ex, E_DB_3);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_4, DB_UPDATE);
            }

            logger.endDebug(session, toBeUpdated);
            return toBeUpdated;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_10, toBeUpdated);
        }
    }

    @Transactional
    public void remove(AppSession appSession, Object toBeRemoved, Boolean usingCache)throws DBException {
        String METHOD = "remove";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;
        try {
            logger.startDebug(session, toBeRemoved);
            logger.info(session, I_DB_9, toBeRemoved.getClass().getSimpleName());

            try {

                if(usingCache) {
                    em = getCachedEM();

                    if (!em.contains(toBeRemoved))
                        throw new DBException(session, E_DB_28, toBeRemoved);

                    em.remove(toBeRemoved);
                    em.flush();
                    em.close();
                }else {
                    em = getUnCachedEM();

                    if (!em.contains(toBeRemoved))
                        throw new DBException(session, E_DB_28, toBeRemoved);

                    em.remove(toBeRemoved);
                    em.flush();
                }

                logger.info(session, I_DB_10, toBeRemoved.getClass().getSimpleName());
            } catch (IllegalArgumentException ex) {
                throw new DBException(session, ex, E_DB_26, toBeRemoved);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, E_DB_3);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_4, DB_DELETE);
            }

            logger.endDebug(session);
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_11, toBeRemoved);
        }
    }

    public boolean checkIsFound(AppSession appSession, Boolean usingCache, String selectAttribute,
                Class entity, String conditionAttribute, String value) throws DBException {
        String METHOD = "checkIsFound";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;
        try {
            logger.startDebug(session);

            boolean isFound = false;

            em = (usingCache) ? getCachedEM() : getUnCachedEM();

            String queryStr = "SELECT " + selectAttribute + " FROM " + entity.getSimpleName() +
                    " WHERE " + conditionAttribute + " = :AttributeValue";

            logger.info(session, I_DB_11, queryStr);
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

                logger.info(session, I_DB_12, queryStr);
            } catch (IllegalStateException ex) {
                throw new DBException(session, ex, E_DB_17, queryStr);
            } catch (QueryTimeoutException ex) {
                throw new DBException(session, ex, E_DB_18, queryStr);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, E_DB_21, queryStr);
            } catch (PessimisticLockException ex) {
                throw new DBException(session, ex, E_DB_19, queryStr);
            } catch (LockTimeoutException ex) {
                throw new DBException(session, ex, E_DB_20, queryStr);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_22, queryStr);
            }

            if(usingCache && em != null)
                em.close();

            logger.endDebug(session);
            return isFound;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_23, entity.getSimpleName());
        }
    }

    /**
     * Retrieves Single value from Database Table or Entity
     *
     * @param usingCache Retrieve from cached data
     * @param entity Hibernate Model Class
     * @param parameters HashMap of Attribute and value as a filter condition
     *
     * @return Object of the entity returned from Database
     *
     * @throws DBException <code>E_DB_15</code> - If Non unique Result returns
     * @throws DBException <code>E_DB_16</code> - If No Result returns
     * @throws DBException <code>E_DB_18</code> - If Query Times out
     * @throws DBException <code>E_DB_22</code> - If There is Persistence Error
     */
    public <T> T getSingleResult(AppSession appSession, Boolean usingCache,
                                 Class<T> entity, Map<String, Object> parameters) throws Exception {
        String METHOD = "getSingleResult";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;
        try {
            logger.startDebug(session, usingCache, entity.getSimpleName(), parameters);

            Map<String, Object> placeHolders = constructQuery(session, entity, parameters);
            String queryStr = (String) placeHolders.remove(QUERY);

            logger.info(session, I_DB_11, queryStr);
            em = (usingCache) ? getCachedEM() : getUnCachedEM();
            TypedQuery<T> query = em.createQuery(queryStr, entity);

            Set<String> keys = placeHolders.keySet();
            String allValues = "";

            for (String key : keys) {
                query.setParameter(key, placeHolders.get(key));
                allValues += placeHolders.get(key) + ", ";
            }

            if (usingCache)
                query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);

            T result;

            try {
                result = query.getSingleResult();
                logger.info(session, I_DB_12, queryStr);
            } catch (NonUniqueResultException ex) {
                throw new DBException(session, E_DB_15, entity.getSimpleName(), placeHolders.keySet().toString(), allValues.substring(0, allValues.length()-2));
            } catch (NoResultException ex) {
                throw new DBException(session, E_DB_16, entity.getSimpleName(), placeHolders.keySet().toString(), allValues.substring(0, allValues.length()-2));
            } catch (QueryTimeoutException ex) {
                throw new DBException(session, ex, E_DB_18, queryStr);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, E_DB_21, queryStr);
            } catch (PessimisticLockException ex) {
                throw new DBException(session, ex, E_DB_19, queryStr);
            } catch (LockTimeoutException ex) {
                throw new DBException(session, ex, E_DB_20, queryStr);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_22, queryStr);
            }

            if(usingCache && em != null)
                em.close();

            logger.endDebug(session);
            return result;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_24, entity.getSimpleName());
        }
    }

    public <T> List<T> getList(AppSession appSession, Boolean usingCache,
                                 Class<T> entity, Map<String, Object> parameters) throws Exception {
        String METHOD = "getList";
        AppSession session = appSession.updateSession(METHOD);
        EntityManager em = null;
        try {
            logger.startDebug(session, usingCache, entity.getSimpleName(), parameters);

            Map<String, Object> placeHolders = constructQuery(session, entity, parameters);
            String queryStr = (String) placeHolders.remove(QUERY);

            logger.info(session, I_DB_11, queryStr);
            em = (usingCache) ? getCachedEM() : getUnCachedEM();
            TypedQuery<T> query = em.createQuery(queryStr, entity);

            Set<String> keys = placeHolders.keySet();
            for (String key : keys)
                query.setParameter(key, placeHolders.get(key));

            if (usingCache)
                query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);

            List<T> result;

            try {
                result = query.getResultList();
                logger.info(session, I_DB_12, queryStr);
            } catch (IllegalStateException ex) {
                throw new DBException(session, ex, E_DB_17, queryStr);
            } catch (QueryTimeoutException ex) {
                throw new DBException(session, ex, E_DB_18, queryStr);
            } catch (TransactionRequiredException ex) {
                throw new DBException(session, ex, E_DB_21, queryStr);
            } catch (PessimisticLockException ex) {
                throw new DBException(session, ex, E_DB_19, queryStr);
            } catch (LockTimeoutException ex) {
                throw new DBException(session, ex, E_DB_20, queryStr);
            } catch (PersistenceException ex) {
                throw new DBException(session, ex, E_DB_22, queryStr);
            }

            if(usingCache && em != null)
                em.close();

            logger.endDebug(session);
            return result;
        }catch (Exception ex){
            if(usingCache && em != null)
                em.close();

            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_29, entity.getSimpleName());
        }
    }

    private Map<String, Object> constructQuery(AppSession appSession, Class entity, Map<String, Object> parameters) throws Exception{
        String METHOD = "constructQuery";
        AppSession session = appSession.updateSession(METHOD);
        try {
            logger.startDebug(session, entity.getSimpleName(), parameters);
            String queryStr = "FROM " + entity.getSimpleName() + " WHERE ";

            if (parameters.size() == 0)
                throw new DBException(session, E_DB_14);

            int count = 1;

            Map<String, Object> placeHolders = new HashMap<>();
            Iterator<String> iterator = parameters.keySet().iterator();

            String attribute = iterator.next();
            Object value = parameters.get(attribute);
            String placeHolder = "Parameter" + count;
            queryStr += attribute + " = :" + placeHolder;
            placeHolders.put(placeHolder, value);

            if (parameters.size() > 1) {
                while (iterator.hasNext()) {
                    count++;
                    attribute = iterator.next();
                    value = parameters.get(attribute);
                    placeHolder = "Parameter" + count;
                    queryStr += " AND " + attribute + " = :" + placeHolder;
                    placeHolders.put(placeHolder, value);
                }
            }

            placeHolders.put(QUERY, queryStr);
            return placeHolders;
        }catch (Exception ex){
            if(ex instanceof DBException)
                throw ex;
            else
                throw new DBException(session, ex, E_DB_25);
        }
    }
}
