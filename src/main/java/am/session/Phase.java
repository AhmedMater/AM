package am.session;

/**
 * Created by ahmed.motair on 9/20/2017.
 */
public enum Phase {
    INITIAL_APP("Initializing Application");

    private String value;

    Phase(String value){
        this.value = value;
    }
    public String value(){return value;}
}
