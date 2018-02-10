package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class AMC extends AMCode {

    public AMC(Integer CODE_ID, String CODE_NAME) {
        super(CodeTypes.CONFIGURATION, false, CODE_ID, CODE_NAME, "", "AM");
    }
}
