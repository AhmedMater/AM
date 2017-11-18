package am.main.data.enums;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
public enum AM_CC {
    ERROR_HANDLER("ErrorHandler"),
    INFO_HANDLER("InfoHandler"),
    APP_CONFIG("ApplicationConfiguration"),
    EMAIL_NOTIFICATION_MANAGER("EMailNotificationManager"),
    LOGGERS_CONFIG("LoggerConfiguration");

    private String value;
    AM_CC(String value){
        this.value = value;
    }
    AM_CC(){}
    public String value(){
        return value;
    }
}
