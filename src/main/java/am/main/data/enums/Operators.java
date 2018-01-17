package am.main.data.enums;

/**
 * Created by ahmed.motair on 12/29/2017.
 */
public enum Operators {
    LIKE("Like", "LIKE"),
    IN("In", "IN"),
    GT_EQ("Greater Than or Equal", ">="),
    ST_EQ("Smaller Than or Equal", "<="),
    EQ("Equal", "="),
    BETWEEN("Date Between", "BETWEEN :{0} AND :{1}");

    private final String name;
    private final String operator;

    Operators(String name, String operator){
        this.name = name;
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public String getOperator() {
        return operator;
    }
}
