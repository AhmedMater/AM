package am.main.data.enums.logger;

/**
 * Created by ahmed.motair on 1/20/2018.
 */
public enum BusLogTypes {
    NEW("N", "New"),
    UPDATE("U", "Update"),
    DELETE("D", "Delete");

    private final String type;
    private final String description;

    BusLogTypes(String type, String description){
        this.type = type;
        this.description = description;
    }

    public String type(){
        return this.type;
    }
    public String description(){
        return this.description;
    }
}
