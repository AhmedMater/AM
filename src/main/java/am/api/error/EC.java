package am.api.error;

/**
 * Created by Ahmed Mater on 1/28/2017.
 */
public enum EC {
    SE_0002,

//    ENM_001, ENM_002, ENM_003,
//    ENM_004, ENM_005, ENM_006, ENM_007, ENM_008, ENM_009,

    DB_0001, DB_0002, DB_0003, DB_0004, DB_0005, DB_0006, DB_0007, DB_0008,

    IO_0014, IO_0015, IO_0018, IO_0019, IO_0030;

    private String value;

    EC(String value){
        this.value = value;
    }
    EC(){}
    public String value(){
        return value;
    }
}
