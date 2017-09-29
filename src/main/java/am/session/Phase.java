package am.session;

/**
 * Created by ahmed.motair on 9/20/2017.
 */
public enum Phase {
    INITIAL_APP("Initializing Application"),
    SECURITY("Security"),
    AUTHORIZATION("Authorization"),
    AUTHENTICATION("Authentication"),
    URL_LOGGING("REST-URL-Log"),
    ERROR_LOGGING("Error-Log"),
    INFO_LOGGING("Info-Log"),
    CONFIGURATION("Configuration"),
    NOTIFICATION("Notification"),
    DATABASE("Database")
    ;

    private String value;

    Phase(String value){
        this.value = value;
    }
    public String value(){return value;}
}
