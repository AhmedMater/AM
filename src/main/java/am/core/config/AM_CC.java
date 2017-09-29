package am.core.config;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
public enum AM_CC {
    ERROR_HANDLER("ErrorHandler"),
    INFO_HANDLER("InfoHandler"),
    APP_CONFIG("ApplicationConfiguration"),
    EMAIL_NOTIFICATION_MANAGER("EMailNotificationManager"),
    VALIDATION_HANDLER("ValidationHandler"),
    APP_LOG_FLAG("AppLogFlag"),
    AM_LIBRARY_LOG_FLAG("AMLibraryLogFlag"),
    REST_LOG_FLAG("RESTLogFlag"),
    SOAP_LOG_FLAG("SOAPLogFlag"),
    JMS_LOG_FLAG("JMSLogFlag"),
    DB_LOG_FLAG("DBLogFlag"),
    SERVICE_LOG_FLAG("ServiceLogFlag");

    private String value;
    AM_CC(String value){
        this.value = value;
    }
    AM_CC(){}
    public String value(){
        return value;
    }
}
