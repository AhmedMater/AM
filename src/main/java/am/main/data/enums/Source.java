package am.main.data.enums;

/**
 * Created by mohamed.elewa on 13/05/2017.
 */
public enum Source {
    AM("AM-Library"),
    APP_SERVICES("App-Services"),
    JOB("Job-Source");

    private String value;

    Source(String value){
        this.value = value;
    }
    public String value(){return value;}

}
