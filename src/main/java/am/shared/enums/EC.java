package am.shared.enums;

/**
 * Created by Ahmed Mater on 1/28/2017.
 */
public enum EC {
    AMT_0000, AMT_0001, AMT_0002, AMT_0003, AMT_0004, AMT_0005, AMT_0006, AMT_0007, AMT_0008, AMT_0009,
    AMT_0010, AMT_0011, AMT_0012, AMT_0013, AMT_0014, AMT_0015, AMT_0016, AMT_0017, AMT_0018, AMT_0019,
    AMT_0020, AMT_0021, AMT_0022, AMT_0023;

    @Override
    public String toString() {
        return super.toString().replaceAll("_","-");
    }
}
