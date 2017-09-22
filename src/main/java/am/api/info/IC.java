package am.api.info;

/**
 * Created by ahmed.motair on 9/8/2017.
 */
public enum IC {
    IO_0001("Start loading file: '{0}' in the System"),
    IO_0002("File '{0}' is loaded successfully"),
    IO_0003("Loading file: '{0}' in the System failed"),
    IO_0004("Start formatting the Message: '{0}'"),
    IO_0005("Message: '{0}' is formatted successfully"),
    IO_0006("Formatting Message: '{0}' failed"),
    IO_0007("Start reading value of Key: '{0}' from File: '{1}'"),
    IO_0008("Value of Key: '{0}' from File: '{1}' is read successfully"),
    IO_0009("Reading the value of Key: '{0}' from File: '{1}' failed"),

    DB_0001, DB_0002, DB_0003, DB_0004, DB_0005, DB_0006, DB_0007, DB_0008, DB_0009,

    ENM_001, ENM_002, ENM_003, ENM_004, ENM_005, ENM_006,
//    ENM_007, ENM_008, ENM_009,

    INFO_00001, INFO_00002, INFO_00003, INFO_00004, INFO_00005, INFO_00006, INFO_00007, INFO_00008
    ;

    private String value;

    IC(String value){
        this.value = value;
    }
    IC(){
    }
    public String value(){
        return value;
    }
}
