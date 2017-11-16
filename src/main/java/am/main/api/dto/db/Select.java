package am.main.api.dto.db;

import am.shared.enums.db.Attribute;
import am.shared.enums.db.Table;

import java.io.Serializable;

/**
 * Created by ahmed.motair on 10/3/2017.
 */
public class Select implements Serializable{
    private Table table;
    private Attribute attribute;

    public Select() {
    }

    public Select(Table table, Attribute attribute) {
        this.table = table;
        this.attribute = attribute;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Select)) return false;

        Select select = (Select) o;

        if (getTable() != select.getTable()) return false;
        return getAttribute() == select.getAttribute();
    }

    @Override
    public int hashCode() {
        int result = getTable() != null ? getTable().hashCode() : 0;
        result = 31 * result + (getAttribute() != null ? getAttribute().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Select{" +
                "table = " + table +
                ", attribute = " + attribute +
            "}\n";
    }
}
