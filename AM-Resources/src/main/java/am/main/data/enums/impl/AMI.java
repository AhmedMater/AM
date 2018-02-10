package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public class AMI extends AMCode {
    private static final String SYSTEM = "SYS";
    private static final String NOTIFICATION = "NOT";
    private static final String LOGGER = "LOG";
    private static final String IO = "IO";
    private static final String JMS = "JMS";
    private static final String DB = "DB";
    private static final String MSG_HANDLER = "MH";
    private static final String SECURITY = "SEC";
    private static final String CONFIG = "CFG";
    private static final String VALIDATION = "VAL";

    public static AMI I_VAL_1 = new AMI(1, VALIDATION, "Start validating ''{0}'' data");
    public static AMI I_VAL_2 = new AMI(2, VALIDATION, "''{0}'' is validated successfully");
    public static AMI I_VAL_3 = new AMI(3, VALIDATION, "");
    public static AMI I_VAL_4 = new AMI(4, VALIDATION, "");
    public static AMI I_VAL_5 = new AMI(5, VALIDATION, "");
    public static AMI I_VAL_6 = new AMI(6, VALIDATION, "");
    public static AMI I_VAL_7 = new AMI(7, VALIDATION, "");
    public static AMI I_VAL_8 = new AMI(8, VALIDATION, "");
    public static AMI I_VAL_9 = new AMI(9, VALIDATION, "");
    public static AMI I_VAL_10 = new AMI(10, VALIDATION, "");


    public static AMI I_CFG_1 = new AMI(1, CONFIG, "Application Configuration are loaded successfully in the system");
    public static AMI I_CFG_2 = new AMI(2, CONFIG, "Start Reading value of Configuration ''{0}''");
    public static AMI I_CFG_3 = new AMI(3, CONFIG, "Configuration: ''{0}'' value is read successfully");
    public static AMI I_CFG_4 = new AMI(4, CONFIG, "Start updating Value of Configuration ''{0}''");
    public static AMI I_CFG_5 = new AMI(5, CONFIG, "''{0}'' Configuration is updated with the new value successfully");

    public static AMI I_SYS_1 = new AMI(1, SYSTEM, "Start Initializing Application Component: ''{0}''");
    public static AMI I_SYS_2 = new AMI(2, SYSTEM, "Application Component: ''{0}'' is loaded successfully");

    public static AMI I_IO_1 = new AMI(1, IO, "Start reading File: ''{0}''");
    public static AMI I_IO_2 = new AMI(2, IO, "File: ''{0}'' is read successfully");
    public static AMI I_IO_3 = new AMI(3, IO, "Start reading Property: ''{0}'' from File: ''{0}''");
    public static AMI I_IO_4 = new AMI(4, IO, "Property: ''{0}'' from File: ''{0}'' is read successfully");
    public static AMI I_IO_5 = new AMI(5, IO, "Start writing File: ''{0}''");
    public static AMI I_IO_6 = new AMI(6, IO, "File: ''{0}'' is wrote successfully");

    public static AMI I_SEC_1 = new AMI(1, SECURITY, "Start generating Access Token");
    public static AMI I_SEC_2 = new AMI(2, SECURITY, "Access Token is generated successfully");
    public static AMI I_SEC_3 = new AMI(3, SECURITY, "Start Hashing Password using ''{0}'' Algorithm");
    public static AMI I_SEC_4 = new AMI(4, SECURITY, "Password is Hashed successfully");

    public static AMI I_DB_1 = new AMI(1, DB, "Start inserting ''{0}'' Entity in the Database");
    public static AMI I_DB_2 = new AMI(2, DB, "Record of Entity ''{0}'' is inserted successfully in Database");
    public static AMI I_DB_3 = new AMI(3, DB, "Start selecting Entity: ''{0}'' with ID: ''{1}'' from Database");
    public static AMI I_DB_4 = new AMI(4, DB, "Entity: ''{0}'' with ID: ''{1}'' is selected successfully from Database");
    public static AMI I_DB_5 = new AMI(5, DB, "Start selecting all ''{0}'' entities from Database");
    public static AMI I_DB_6 = new AMI(6, DB, "Selecting all ''{0}'' entities are selected successfully from Database");
    public static AMI I_DB_7 = new AMI(7, DB, "Start updating ''{0}'' Entity in Database");
    public static AMI I_DB_8 = new AMI(8, DB, "''{0}'' Entity is updated successfully in Database");
    public static AMI I_DB_9 = new AMI(9, DB, "Start removing ''{0}'' Entity from Database");
    public static AMI I_DB_10 = new AMI(10, DB, "''{0}'' Entity is removed successfully from Database");
    public static AMI I_DB_11 = new AMI(11, DB, "Start executing Query: ''{0}''");
    public static AMI I_DB_12 = new AMI(12, DB, "''{0}'' Query is executed successfully");


    public static AMI I_MH_1 = new AMI(1, MSG_HANDLER, "Error Messages are loaded successfully in the system");
    public static AMI I_MH_2 = new AMI(2, MSG_HANDLER, "Info Messages are loaded successfully in the system");
    public static AMI I_MH_3 = new AMI(3, MSG_HANDLER, "Warning Messages are loaded successfully in the system");
    public static AMI I_MH_4 = new AMI(4, MSG_HANDLER, "Start reading Raw Message of Code: ''{0}''");
    public static AMI I_MH_5 = new AMI(5, MSG_HANDLER, "Raw Message of Code: ''{0}'' is read successfully");
    public static AMI I_MH_6 = new AMI(6, MSG_HANDLER, "Start Formatting Message");
    public static AMI I_MH_7 = new AMI(7, MSG_HANDLER, "Messages is formatted successfully");
    public static AMI I_MH_8 = new AMI(8, MSG_HANDLER, "Start reading Full Message of Code: ''{0}''");
    public static AMI I_MH_9 = new AMI(9, MSG_HANDLER, "Full Message of Code: ''{0}'' is read successfully");



//    public static AMI SYS_3 = new AMI(3, SYSTEM,"Message: ''{0}'' is formatted successfully");


//    public static AMI IO_1 = new AMI(1, IO, "Resource File: ''{0}'' is read successfully");
//    public static AMI IO_2 = new AMI(2, IO, "Value of Property: ''{0}'' if read successfully from File:''{1}''");
//    public static AMI IO_3 = new AMI(3, IO, "''{0}'' of Code: ''{1}'' is read successfully");
//    public static AMI IO_4 = new AMI(4, IO, "''{0}'' of Code: ''{1}'' is changed successfully");

    public static AMI I_JMS_1 = new AMI(1, JMS, "Application JMS Configuration is read successfully");
    public static AMI I_JMS_2 = new AMI(2, JMS, "JMS Queue ''{0}'' is initiated successfully");
    public static AMI I_JMS_3 = new AMI(3, JMS, "Start sending Message ''{0}'' to Queue: ''{1}'' successfully");
    public static AMI I_JMS_4 = new AMI(4, JMS, "Message ''{0}'' is sent Queue ''{1}'' successfully");

    public static AMI I_LOG_1 = new AMI(1, LOGGER, "Template Log4j2.xml file is read successfully");
    public static AMI I_LOG_2 = new AMI(2, LOGGER, "External Logger Configuration is read successfully");
    public static AMI I_LOG_3 = new AMI(3, LOGGER, "Logger: ''{0}'' in Group: ''{1}'' is created successfully");
    public static AMI I_LOG_4 = new AMI(4, LOGGER, "Group Logger: ''{0}'' is created successfully");
    public static AMI I_LOG_5 = new AMI(5, LOGGER, "Log4j2.xml file is recreated successfully");
    public static AMI I_LOG_6 = new AMI(6, LOGGER, "Log4j2.xml file is reloaded successfully");
    public static AMI I_LOG_7 = new AMI(7, LOGGER, "Logger: ''{0}'' is loaded successfully");
    public static AMI I_LOG_8 = new AMI(8, LOGGER, "All Loggers are loaded successfully");
    public static AMI I_LOG_9 = new AMI(9, LOGGER, "Start loading ''{0}'' Logger Configuration");
    public static AMI I_LOG_10 = new AMI(10, LOGGER, "''{0}'' Logger Configuration are loaded successfully");
    public static AMI I_LOG_11 = new AMI(11, LOGGER, "Logger Config Property: ''{0}'' is found");
    public static AMI I_LOG_12 = new AMI(12, LOGGER, "Start checking Logger Configuration");
    public static AMI I_LOG_13 = new AMI(13, LOGGER, "Logger Configuration is loaded successfully");
    public static AMI I_LOG_14 = new AMI(14, LOGGER, "Logger Config Property: ''{0}'' is found and loaded successfully");
    public static AMI I_LOG_15 = new AMI(15, LOGGER, "General Logger for Application: ''{0}'' is generated");
    public static AMI I_LOG_16 = new AMI(16, LOGGER, "Logger Config Property: ''{0}'' is will be overridden with the Value: ''{1}'' in external Logger Configuration");
    public static AMI I_LOG_17 = new AMI(17, LOGGER, "");
    public static AMI I_LOG_18 = new AMI(18, LOGGER, "");
    public static AMI I_LOG_19 = new AMI(19, LOGGER, "");

    private AMI(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.INFO, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM");
    }
}
