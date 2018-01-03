package am.main.api.db;

import am.main.data.enums.Operators;

import java.text.MessageFormat;
import java.util.Date;

import static am.main.data.enums.Operators.*;

/**
 * Created by ahmed.motair on 12/24/2017.
 */
public class HQLCondition<T>{
    //For Normal Value
    private String placeHolder;
    private T value;

    // For Date
    private String fromPH;
    private String toPH;
    private T from;
    private T to;

    private String tableAlias;
    private String attribute;
    private Boolean isApplicable;

    private Class<?> type;
    private Operators operator;

    public HQLCondition() {
    }
    public HQLCondition(T value, String attribute, Operators operator) {
        this(value, null, attribute, operator);
    }
    public HQLCondition(T from, T to, String attribute) {
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
    public HQLCondition(T from, T to, String tableAlias, String attribute) {
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

    public T getFrom() {
        return from;
    }
    public void setFrom(T from) {
        this.from = from;
    }

    public T getTo() {
        return to;
    }
    public void setTo(T to) {
        this.to = to;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
        this.isApplicable = (value != null);
    }

    public String getCondition() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HQLCondition)) return false;

        HQLCondition<?> that = (HQLCondition<?>) o;

        if (getPlaceHolder() != null ? !getPlaceHolder().equals(that.getPlaceHolder()) : that.getPlaceHolder() != null)
            return false;
        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;
        return getCondition() != null ? getCondition().equals(that.getCondition()) : that.getCondition() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlaceHolder() != null ? getPlaceHolder().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getCondition() != null ? getCondition().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HQLCondition{" +
                "placeHolder = " + placeHolder +
                ", value = " + value +
                ", attribute = " + attribute +
                "}\n";
    }
}
