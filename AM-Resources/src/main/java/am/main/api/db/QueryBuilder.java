package am.main.api.db;

import am.main.api.AppLogger;
import am.main.data.dto.ListResultSet;
import am.main.data.dto.SortingInfo;
import am.main.data.vto.PaginationInfo;
import am.main.session.AppSession;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static am.main.data.enums.Operators.LIKE;

/**
 * Created by ahmed.motair on 12/24/2017.
 */
public class QueryBuilder<T> {
    private String dataSelect;
    private String from;
    private List<HQLCondition> conditions;
    private List<String> placeHolders;
    private String where;
    private String groupBy;
    private SortingInfo sorting;

    private Integer pageNum;
    private Integer pageSize;

    private ListResultSet<T> resultSet;
    private Class<T> className;

    private AppLogger logger;
    private AppSession session;

    private final String CLASS;

    public QueryBuilder(Class<T> className, AppLogger logger, AppSession session) {
        this.className = className;
        this.conditions = new ArrayList<>();
        this.placeHolders = new ArrayList<>();
        this.where = "";
        this.logger = logger;
        this.CLASS = QueryBuilder.class.getSimpleName();
        this.session = session.updateSession(CLASS, "Constructor");
    }
    public QueryBuilder(List<HQLCondition> conditions, String where) {
        this.conditions = conditions;
        this.where = where;
        this.CLASS = QueryBuilder.class.getSimpleName();
        this.session = session.updateSession(CLASS, "Constructor");
    }

    public List<HQLCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<HQLCondition> conditions) {
        this.conditions = conditions;
    }

    public String constructWhere() {
        String FN_NAME = "constructWhere";
        session = session.updateSession(FN_NAME);
        logger.startDebug(session);

        if(this.conditions.size()>0) {
            this.where += " WHERE " + conditions.get(0).constructCondition();

            for (int i=1; i<conditions.size(); i++)
                this.where += " AND " + conditions.get(i).constructCondition();
        }

        logger.endDebug(session, where);
        return where;
    }
    public void setWhere(String where) {
        this.where = where;
    }

    public String getDataSelect() {
        return dataSelect;
    }
    public void setDataSelect(String dataSelect) {
        this.dataSelect = dataSelect;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public SortingInfo getSorting() {
        return sorting;
    }
    public void setSorting(SortingInfo sorting) {
        this.sorting = sorting;
    }

    public void setPagingInfo(Integer pageNum, Integer pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public ListResultSet<T> getResultSet() {
        return resultSet;
    }

    public String getGroupBy() {
        return groupBy;
    }
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public void addCondition(HQLCondition condition) {
        String FN_NAME = "addCondition";
        session = session.updateSession(FN_NAME);
        logger.startDebug(session, condition);

        if(condition.getApplicable()) {
            if(condition.getType().equals(Date.class)) {
                if(condition.getFrom() != null)
                    condition.setFromPH(randomPlaceHolder());

                if(condition.getTo() != null)
                    condition.setToPH(randomPlaceHolder());
            }else
                condition.setPlaceHolder(randomPlaceHolder());

            this.conditions.add(condition);
        }

        logger.endDebug(session);
    }

    @Transactional
    public void executeQuery(EntityManager em){
        String FN_NAME = "executeQuery";
        session = session.updateSession(FN_NAME);
        logger.startDebug(session, em);

        this.constructWhere();

        String dataQueryStr = dataSelect + " " + from + " " + where + " " + sorting.getSortStatement();
        String countQueryStr = "SELECT COUNT(*) " + from + " " + where;

        if(groupBy != null) {
            dataQueryStr += " GROUP BY " + groupBy;
            countQueryStr += " GROUP BY " + groupBy;
        }

        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        countQuery = this.addParameters(countQuery);
        int total = countQuery.getResultList().get(0).intValue();

        PaginationInfo pagination = new PaginationInfo(total, pageSize, pageNum);

        TypedQuery<T> dataQuery = em.createQuery(dataQueryStr, className)
                .setFirstResult(pageSize*pageNum)
                .setMaxResults(pageSize);
        dataQuery = this.addParameters(dataQuery);
        List<T> data = dataQuery.getResultList();

        resultSet = new ListResultSet<T>();
        resultSet.setData(data);
        resultSet.setPagination(pagination);

        logger.endDebug(session);
    }

    private <E> TypedQuery<E> addParameters(TypedQuery<E> query){
        String FN_NAME = "addParameters";
        session = session.updateSession(FN_NAME);
        logger.startDebug(session, query);

        for (HQLCondition condition : conditions) {
            if(condition.getType().equals(Date.class)) {
                if(condition.getFromPH() != null)
                    query.setParameter(condition.getFromPH(), condition.getFrom());

                if(condition.getToPH() != null)
                    query.setParameter(condition.getToPH(), condition.getTo());
            }else if(condition.getOperator().equals(LIKE))
                query.setParameter(condition.getPlaceHolder(), "%" + condition.getValue() + "%");
            else
                query.setParameter(condition.getPlaceHolder(), condition.getValue());
        }
        logger.endDebug(session, query);
        return query;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String randomPlaceHolder() {
        int count = 25;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        for (String _placeHolder : placeHolders)
            if (_placeHolder.equals(builder.toString()))
                randomPlaceHolder();

        this.placeHolders.add(builder.toString());
        return builder.toString();
    }
}
