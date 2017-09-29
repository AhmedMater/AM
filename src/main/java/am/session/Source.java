package am.session;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public enum Source {
    SOAP("SOAP"),
    JMS("JMS"),
    REST("REST"),
    DB("DB"),
    Service("Service"),
    AM("AM");

    private String value;

    Source(String value){
        this.value = value;
    }
    public String value(){return value;}
}
