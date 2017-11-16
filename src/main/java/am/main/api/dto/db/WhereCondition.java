package am.main.api.dto.db;


import am.shared.enums.db.Attribute;
import am.shared.enums.db.ConditionOperator;
import am.shared.enums.db.LogicalOperator;
import am.shared.enums.db.Table;

import java.util.Date;

/**
 * Created by ahmed.motair on 10/3/2017.
 */
public class WhereCondition {
    private int order;
    private Table table;
    private Attribute attribute;
    private Object value;
    private Date dateFrom;
    private Date dateTo;
    private ConditionOperator conditionOperator;
    private LogicalOperator logicalOperator;

    public WhereCondition() {
    }

    public WhereCondition(int order, Table table, Attribute attribute, Object value, Date dateFrom, Date dateTo, ConditionOperator conditionOperator, LogicalOperator logicalOperator) {
        this.order = order;
        this.table = table;
        this.attribute = attribute;
        this.value = value;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.conditionOperator = conditionOperator;
        this.logicalOperator = logicalOperator;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }

    public Attribute getAttribute() {
        return attribute;
    }
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    public Date getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ConditionOperator getConditionOperator() {
        return conditionOperator;
    }
    public void setConditionOperator(ConditionOperator conditionOperator) {
        this.conditionOperator = conditionOperator;
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }
    public void setLogicalOperator(LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WhereCondition)) return false;

        WhereCondition that = (WhereCondition) o;

        if (getOrder() != that.getOrder()) return false;
        if (getTable() != that.getTable()) return false;
        if (getAttribute() != that.getAttribute()) return false;
        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;
        if (getDateFrom() != null ? !getDateFrom().equals(that.getDateFrom()) : that.getDateFrom() != null)
            return false;
        if (getDateTo() != null ? !getDateTo().equals(that.getDateTo()) : that.getDateTo() != null) return false;
        if (getConditionOperator() != null ? !getConditionOperator().equals(that.getConditionOperator()) : that.getConditionOperator() != null)
            return false;
        return getLogicalOperator() == that.getLogicalOperator();
    }

    @Override
    public int hashCode() {
        int result = getOrder();
        result = 31 * result + (getTable() != null ? getTable().hashCode() : 0);
        result = 31 * result + (getAttribute() != null ? getAttribute().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getDateFrom() != null ? getDateFrom().hashCode() : 0);
        result = 31 * result + (getDateTo() != null ? getDateTo().hashCode() : 0);
        result = 31 * result + (getConditionOperator() != null ? getConditionOperator().hashCode() : 0);
        result = 31 * result + (getLogicalOperator() != null ? getLogicalOperator().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WhereCondition{" +
                "order = " + order +
                ", table = " + table +
                ", attribute = " + attribute +
                ", value = " + value +
                ", dateFrom = " + dateFrom +
                ", dateTo = " + dateTo +
                ", conditionOperator = " + conditionOperator +
                ", logicalOperator = " + logicalOperator +
                "}\n";
    }
}
