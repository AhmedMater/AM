package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class IWC extends AMCode{
    private static final String LOGGER = "LOG";

    public static IWC W_LOG_1 = new IWC(1, LOGGER, "No External Logger Configuration found, Default Logger Configuration will be used");
    public static IWC W_LOG_2 = new IWC(2, LOGGER, "No Application Configuration found");
//    public static IWC W_LOG_3 = new IWC(3, LOGGER, "No AM-Resources Application Tag found in External Logger Configuration, Default Configuration will be used");
    public static IWC W_LOG_4 = new IWC(4, LOGGER, "More than one Application Tag in the Internal Logger Configuration, Only AM-Resources will be loaded and the rest will be ignored");
    public static IWC W_LOG_5 = new IWC(5, LOGGER, "No AM Config Tag found in the Internal Logger Configuration, Default Configuration will be loaded");
    public static IWC W_LOG_6 = new IWC(6, LOGGER, "No AM Logger Group Tag in the Internal Logger Configuration, Default AM Logger Group will be loaded");
    public static IWC W_LOG_7 = new IWC(7, LOGGER, "Logger Config Property: ''{0}'' isn''t found, and default value: ''{1}'' will be loaded");
    public static IWC W_LOG_8 = new IWC(8, LOGGER, "Value: ''{0}'' isn't valid for Property: ''{1}'', and default value: ''{2}'' will be loaded instead");
    public static IWC W_LOG_9 = new IWC(9, LOGGER, "More than one Logger Group Tag in the Internal Logger Configuration, Only AM Group will be loaded and the rest will be ignored");
    public static IWC W_LOG_10 = new IWC(10, LOGGER, "AM Group isn''t found in the Internal Logger Configuration, Default AM Group will be loaded");
    public static IWC W_LOG_11 = new IWC(11, LOGGER, "Invalid Logger Config Property: ''{0}''");
    public static IWC W_LOG_12 = new IWC(12, LOGGER, "");
    public static IWC W_LOG_13 = new IWC(13, LOGGER, "");

    private IWC(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.WARN, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM");
    }
}
