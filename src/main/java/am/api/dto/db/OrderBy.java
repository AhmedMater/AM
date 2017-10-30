package am.api.dto.db;

import am.api.enums.db.Attribute;
import am.api.enums.db.Order;
import am.api.enums.db.Table;

/**
 * Created by ahmed.motair on 10/4/2017.
 */
public class OrderBy {
    private Table table;
    private Attribute attribute;
    private Order order;

    public OrderBy() {
    }

    public OrderBy(Table table, Attribute attribute, Order order) {
        this.table = table;
        this.attribute = attribute;
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

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBy)) return false;

        OrderBy orderBy = (OrderBy) o;

        if (getTable() != orderBy.getTable()) return false;
        if (getAttribute() != orderBy.getAttribute()) return false;
        return getOrder() == orderBy.getOrder();
    }

    @Override
    public int hashCode() {
        int result = getTable() != null ? getTable().hashCode() : 0;
        result = 31 * result + (getAttribute() != null ? getAttribute().hashCode() : 0);
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderBy{" +
                "table = " + table +
                ", attribute = " + attribute +
                ", order = " + order +
            "}\n";
    }
}
