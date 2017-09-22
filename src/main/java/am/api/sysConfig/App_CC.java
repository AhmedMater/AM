package am.api.sysConfig;

/**
 * Created by ahmed.motair on 9/9/2017.
 */
public enum App_CC {
    LOGGER_NAME("Logger_Name"),
    ERROR_MESSAGES("Error_Messages_Property_File_Path"),
    INFO_MESSAGES("Info_Messages_Property_File_Path");

    private String value;

    App_CC(String value){
        this.value = value;
    }
    App_CC(){}
    public String value(){
        return value;
    }
}
