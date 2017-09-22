package am.core.config;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
public enum AM_CC {
    ERROR_HANDLER("ErrorHandler"),
    INFO_HANDLER("InfoHandler"),
    APP_CONFIG("ApplicationConfiguration"),
    EMAIL_NOTFICATION_MANAGER("EMailNotificationManager"),
    VALIDATION_HANDLER("ValidationHandler");

    private String value;
    AM_CC(String value){
        this.value = value;
    }
    AM_CC(){}
    public String value(){
        return value;
    }
}
