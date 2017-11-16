package am.main.api.dto.db;


import am.shared.enums.db.Attribute;
import am.shared.enums.db.ConditionOperator;
import am.shared.enums.db.LogicalOperator;
import am.shared.enums.db.Table;

import java.io.Serializable;

/**
 * Created by ahmed.motair on 10/3/2017.
 */
public class JoinCondition implements Serializable{
    private int order;
    private LogicalOperator logicalOperator;
    private Table upperTable;
    private Attribute upperAttribute;
    private Table lowerTable;
    private Attribute lowerAttribute;
    private ConditionOperator conditionOperator;
    private Object value;

    public JoinCondition() {
    }
    public JoinCondition(int order, LogicalOperator logicalOperator, Table upperTable, Attribute upperAttribute, Table lowerTable, Attribute lowerAttribute, ConditionOperator conditionOperator, Object value) {
        this.order = order;
        this.logicalOperator = logicalOperator;
        this.upperTable = upperTable;
        this.upperAttribute = upperAttribute;
        this.lowerTable = lowerTable;
        this.lowerAttribute = lowerAttribute;
        this.conditionOperator = conditionOperator;
        this.value = value;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }
    public void setLogicalOperator(LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public Table getUpperTable() {
        return upperTable;
    }
    public void setUpperTable(Table upperTable) {
        this.upperTable = upperTable;
    }

    public Attribute getUpperAttribute() {
        return upperAttribute;
    }
    public void setUpperAttribute(Attribute upperAttribute) {
        this.upperAttribute = upperAttribute;
    }

    public Table getLowerTable() {
        return lowerTable;
    }
    public void setLowerTable(Table lowerTable) {
        this.lowerTable = lowerTable;
    }

    public Attribute getLowerAttribute() {
        return lowerAttribute;
    }
    public void setLowerAttribute(Attribute lowerAttribute) {
        this.lowerAttribute = lowerAttribute;
    }

    public ConditionOperator getConditionOperator() {
        return conditionOperator;
    }
    public void setConditionOperator(ConditionOperator conditionOperator) {
        this.conditionOperator = conditionOperator;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoinCondition)) return false;

        JoinCondition that = (JoinCondition) o;

        if (getOrder() != that.getOrder()) return false;
        if (getLogicalOperator() != that.getLogicalOperator()) return false;
        if (getUpperTable() != that.getUpperTable()) return false;
        if (getUpperAttribute() != that.getUpperAttribute()) return false;
        if (getLowerTable() != that.getLowerTable()) return false;
        if (getLowerAttribute() != that.getLowerAttribute()) return false;
        if (getConditionOperator() != null ? !getConditionOperator().equals(that.getConditionOperator()) : that.getConditionOperator() != null) return false;
        return getValue() != null ? getValue().equals(that.getValue()) : that.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrder();
        result = 31 * result + (getLogicalOperator() != null ? getLogicalOperator().hashCode() : 0);
        result = 31 * result + (getUpperTable() != null ? getUpperTable().hashCode() : 0);
        result = 31 * result + (getUpperAttribute() != null ? getUpperAttribute().hashCode() : 0);
        result = 31 * result + (getLowerTable() != null ? getLowerTable().hashCode() : 0);
        result = 31 * result + (getLowerAttribute() != null ? getLowerAttribute().hashCode() : 0);
        result = 31 * result + (getConditionOperator() != null ? getConditionOperator().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JoinCondition{" +
                "order = " + order +
                ", logicalOperator = " + logicalOperator +
                ", upperTable = " + upperTable +
                ", upperAttribute = " + upperAttribute +
                ", lowerTable = " + lowerTable +
                ", lowerAttribute = " + lowerAttribute +
                ", conditionOperator = " + conditionOperator +
                ", value = " + value +
            "}\n";
    }
}
