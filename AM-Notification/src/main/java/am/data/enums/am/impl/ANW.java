package am.data.enums.am.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 2/7/2018.
 */
public class ANW extends AMCode {
    private static final String RECEIVE = "NTF-REC";
    private static final String VALIDATE = "NTF-VAL";
    private static final String PROCESS = "NTF-PROC";
    private static final String MANAGER = "MGR";

    public static final ANW W_VN_1 = new ANW(1, VALIDATE, "App: ''{0}'' has reached its max quota today, and EventID: ''{1}'' will be quarantined, until next charge");

    public static final ANW W_PN_1 = new ANW(1, PROCESS, "User: ''{0}'' doesn''t provide Email Address");
    public static final ANW W_PN_2 = new ANW(2, PROCESS, "User: ''{0}'' doesn''t provide Mobile Phone");
    public static final ANW W_PN_3 = new ANW(3, PROCESS, "User: ''{0}'' doesn''t provide Web ID");

    private ANW(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.WARN, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AMN");
    }
}
