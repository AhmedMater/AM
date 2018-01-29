package am.main.api.db;

import am.main.data.enums.Operators;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static am.main.data.enums.Operators.*;

/**
 * Created by ahmed.motair on 12/24/2017.
 */
public class HQLCondition<T>{
    //For Normal Value
    private String placeHolder;
    private T value;
    private List<T> values;

    // For Date
    private String fromPH;
    private String toPH;
    private Date from;
    private Date to;

    private String tableAlias;
    private String attribute;
    private Boolean isApplicable;

    private Class<?> type;
    private Operators operator;

    public HQLCondition() {
    }
    public HQLCondition(String attribute, Operators operator, T value) {
        this(value, null, attribute, operator);
    }
    public HQLCondition(String attribute, T ... values) {
        this(null, null, attribute, Operators.IN);
        this.values = Arrays.asList(values);
    }
    public HQLCondition(Date from, Date to, String attribute) {
        this(from, to, null, attribute);
    }
    public HQLCondition(T value, String tableAlias, String attribute, Operators operator) {
        this.value = value;
        this.attribute = (tableAlias != null) ? tableAlias + "." + attribute : attribute;
        this.operator = operator;
        if(value != null) {
            this.isApplicable = true;
            this.type = value.getClass();
        }else
            this.isApplicable = false;
    }
    public HQLCondition(Date from, Date to, String tableAlias, String attribute) {
        this.from = from;
        this.to = to;
        this.attribute = (tableAlias != null) ? tableAlias + "." + attribute : attribute;
        this.isApplicable = !(from == null && to == null);
        this.type = Date.class;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getFromPH() {
        return fromPH;
    }
    public void setFromPH(String fromPH) {
        this.fromPH = fromPH;
    }

    public String getToPH() {
        return toPH;
    }
    public void setToPH(String toPH) {
        this.toPH = toPH;
    }

    public Date getFrom() {
        return from;
    }
    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }
    public void setTo(Date to) {
        this.to = to;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
        this.isApplicable = (value != null);
    }

    public String constructCondition() {
        if(type.equals(Date.class)) {
            if (from == null && to != null)
                return attribute + " " + ST_EQ.getOperator() + " :" + getToPH();
            else if (from != null && to == null)
                return attribute + " " + GT_EQ.getOperator() + " :" + this.getFromPH();
            else
                return attribute + " " + MessageFormat.format(BETWEEN.getOperator(), getFromPH(), getToPH());
        }else
            return attribute + " " + operator.getOperator() + " :" +  getPlaceHolder();
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Boolean getApplicable() {
        return isApplicable;
    }

    public Class<?> getType() {
        return type;
    }

    public Operators getOperator() {
        return operator;
    }
    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    public List<T> getValues() {
        return values;
    }
    public void setValues(List<T> values) {
        this.values = values;
    }

    public String getTableAlias() {
        return tableAlias;
    }
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setApplicable(Boolean applicable) {
        isApplicable = applicable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HQLCondition)) return false;

        HQLCondition<?> that = (HQLCondition<?>) o;

        if (getPlaceHolder() != null ? !getPlaceHolder().equals(that.getPlaceHolder()) : that.getPlaceHolder() != null) return false;
        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;
        if (getValues() != null ? !getValues().equals(that.getValues()) : that.getValues() != null) return false;
        if (getFromPH() != null ? !getFromPH().equals(that.getFromPH()) : that.getFromPH() != null) return false;
        if (getToPH() != null ? !getToPH().equals(that.getToPH()) : that.getToPH() != null) return false;
        if (getFrom() != null ? !getFrom().equals(that.getFrom()) : that.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(that.getTo()) : that.getTo() != null) return false;
        if (getTableAlias() != null ? !getTableAlias().equals(that.getTableAlias()) : that.getTableAlias() != null) return false;
        if (getAttribute() != null ? !getAttribute().equals(that.getAttribute()) : that.getAttribute() != null) return false;
        if (isApplicable != null ? !isApplicable.equals(that.isApplicable) : that.isApplicable != null) return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        return getOperator() == that.getOperator();
    }

    @Override
    public int hashCode() {
        int result = getPlaceHolder() != null ? getPlaceHolder().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getValues() != null ? getValues().hashCode() : 0);
        result = 31 * result + (getFromPH() != null ? getFromPH().hashCode() : 0);
        result = 31 * result + (getToPH() != null ? getToPH().hashCode() : 0);
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getTableAlias() != null ? getTableAlias().hashCode() : 0);
        result = 31 * result + (getAttribute() != null ? getAttribute().hashCode() : 0);
        result = 31 * result + (isApplicable != null ? isApplicable.hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HQLCondition{" +
                "placeHolder = " + placeHolder +
                ", value = " + value +
                ", values = " + values +
                ", fromPH = " + fromPH +
                ", toPH = " + toPH +
                ", from = " + from +
                ", to = " + to +
                ", tableAlias = " + tableAlias +
                ", attribute = " + attribute +
                ", isApplicable = " + isApplicable +
                ", type = " + type +
                ", operator = " + operator +
                "}\n";
    }
}
