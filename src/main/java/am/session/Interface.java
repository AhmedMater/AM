package am.session;

/**
 * Created by ahmed.motair on 9/29/2017.
 */
public enum Interface {
    REST("REST"),
    SOAP("SOAP"),
    JMS("JMS"),
    JOB("Job-Interface"),
    INITIALIZING("Initializing");

    private String value;

    Interface(String value){
        this.value = value;
    }
    public String value(){return value;}
}
