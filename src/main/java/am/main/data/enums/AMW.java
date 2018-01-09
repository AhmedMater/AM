package am.main.data.enums;

/**
 * Created by ahmed.motair on 1/7/2018.
 */
public enum AMW {
    SYS_001("AM-JMS-001: Message ''{0}'' is sent successfully");

    private String value;

    AMW(String value){
        this.value = value;
    }
    AMW(){
    }
    public String value(){
        return value;
    }
    @Override public String toString(){
        return value;
    }
}
