package am.api.error;

/**
 * Created by Ahmed Mater on 1/28/2017.
 */
public enum EC {
    AMT_0001, AMT_0002, AMT_0003, AMT_0004, AMT_0005, AMT_0006, AMT_0007, AMT_0008, AMT_0009, AMT_0010,
    AMT_0011, AMT_0012, AMT_0013, AMT_0014, AMT_0015, AMT_0016, AMT_0017, AMT_0018, AMT_0019, AMT_0020;

    private String value;

    EC(String value){
        this.value = value;
    }
    EC(){}
    public String value(){
        return value;
    }
}
