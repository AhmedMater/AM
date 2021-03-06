package am.main.data.enums;

/**
 * Created by ahmed.motair on 9/29/2017.
 */
public enum Interface {
    REST("REST"),
    SOAP("SOAP"),
    JMS("JMS"),
    JOB("Job-Interface"),
    QUARTZ("Quartz"),
    ARQUILLIAN("Arquillian"),
    INITIALIZING_COMPONENT("Init-Component"),
    INTERNAL_PROC("Internal-Processing");

    private String value;

    Interface(String value){
        this.value = value;
    }
    public String value(){return value;}
}
