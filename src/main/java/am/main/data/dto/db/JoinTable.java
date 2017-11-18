package am.main.data.dto.db;

import am.shared.enums.db.Join;
import am.shared.enums.db.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ahmed.motair on 10/3/2017.
 */
public class JoinTable implements Serializable {
    private int order;
    private Join join;
    private Table table;
    private List<JoinCondition> conditionList;

    public JoinTable() {
    }

    public JoinTable(int order, Join join, Table table, List<JoinCondition> conditionList) {
        this.order = order;
        this.join = join;
        this.table = table;
        this.conditionList = conditionList;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public Join getJoin() {
        return join;
    }
    public void setJoin(Join join) {
        this.join = join;
    }

    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }

    public List<JoinCondition> getConditionList() {
        return conditionList;
    }
    public void setConditionList(List<JoinCondition> conditionList) {
        this.conditionList = conditionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoinTable)) return false;

        JoinTable joinTable = (JoinTable) o;

        if (getOrder() != joinTable.getOrder()) return false;
        if (getJoin() != joinTable.getJoin()) return false;
        if (getTable() != joinTable.getTable()) return false;
        return getConditionList() != null ? getConditionList().equals(joinTable.getConditionList()) : joinTable.getConditionList() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrder();
        result = 31 * result + (getJoin() != null ? getJoin().hashCode() : 0);
        result = 31 * result + (getTable() != null ? getTable().hashCode() : 0);
        result = 31 * result + (getConditionList() != null ? getConditionList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JoinTable{" +
                "order = " + order +
                ", join = " + join +
                ", table = " + table +
                ", conditionList = " + conditionList +
            "}\n";
    }
}
