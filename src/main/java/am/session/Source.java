package am.session;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public enum Source {
    SOAP("SOAP"),
    JMS("JMS"),
    REST("REST"),
    JOB("Backend Job"),
    AM_API("AM");

    private String value;

    Source(String value){
        this.value = value;
    }
    public String value(){return value;}
}
