package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class AMW extends AMCode{
    private static final String LOGGER = "LOG";

    public static AMW W_LOG_1 = new AMW(1, LOGGER, "No External Logger Configuration found, Default Logger Configuration will be used");
    public static AMW W_LOG_2 = new AMW(2, LOGGER, "No Application Configuration found");
//    public static AMW W_LOG_3 = new AMW(3, LOGGER, "No AM-Resources Application Tag found in External Logger Configuration, Default Configuration will be used");
    public static AMW W_LOG_4 = new AMW(4, LOGGER, "More than one Application Tag in the Internal Logger Configuration, Only AM-Resources will be loaded and the rest will be ignored");
    public static AMW W_LOG_5 = new AMW(5, LOGGER, "No AM Config Tag found in the Internal Logger Configuration, Default Configuration will be loaded");
    public static AMW W_LOG_6 = new AMW(6, LOGGER, "No AM Logger Group Tag in the Internal Logger Configuration, Default AM Logger Group will be loaded");
    public static AMW W_LOG_7 = new AMW(7, LOGGER, "Logger Config Property: ''{0}'' isn''t found, and default value: ''{1}'' will be loaded");
    public static AMW W_LOG_8 = new AMW(8, LOGGER, "Value: ''{0}'' isn't valid for Property: ''{1}'', and default value: ''{2}'' will be loaded instead");
    public static AMW W_LOG_9 = new AMW(9, LOGGER, "More than one Logger Group Tag in the Internal Logger Configuration, Only AM Group will be loaded and the rest will be ignored");
    public static AMW W_LOG_10 = new AMW(10, LOGGER, "AM Group isn''t found in the Internal Logger Configuration, Default AM Group will be loaded");
    public static AMW W_LOG_11 = new AMW(11, LOGGER, "Invalid Logger Config Property: ''{0}''");
    public static AMW W_LOG_12 = new AMW(12, LOGGER, "");
    public static AMW W_LOG_13 = new AMW(13, LOGGER, "");

    private AMW(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.WARN, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM");
    }
}
