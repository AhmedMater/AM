package am.main.api.components.db;

import am.main.api.dto.db.JoinCondition;
import am.main.api.dto.db.JoinTable;
import am.main.api.dto.db.Select;
import am.main.api.dto.db.WhereCondition;
import am.shared.enums.db.Attribute;
import am.shared.enums.db.Join;
import am.shared.enums.db.Table;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ahmed.motair on 10/3/2017.
 */
public class QueryBuilder {
    private List<Select> selectList;
    private Table mainTable;
    private int order = 0;
    private List<JoinTable> joinTableList;
    private List<WhereCondition> whereConditions;

    public QueryBuilder selectList(Table table, Attribute attribute){
        Select select = new Select(table, attribute);
        selectList.add(select);
        return this;
    }

    public QueryBuilder mainTable(Table table){
        mainTable = table;
        return this;
    }

    public QueryBuilder join(Join joinType, Table table, JoinCondition ... conditions){
        JoinTable joinTable = new JoinTable(order++, joinType, table, Arrays.asList(conditions));
        joinTableList.add(joinTable);
        return this;
    }

}
