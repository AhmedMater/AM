package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class IEC extends AMCode {
    private static final String SYSTEM = "SYS";
    private static final String NOTIFICATION = "NOT";
    private static final String LOGGER = "LOG";
    private static final String IO = "IO";
    private static final String JMS = "JMS";
    private static final String DB = "DB";
    private static final String SECURITY = "SEC";
    private static final String MSG_HANDLER = "MH";
    private static final String CONFIG = "CFG";
    private static final String VALIDATION = "VAL";

    public static IEC E_VAL_0 = new IEC(0, VALIDATION, "{0} Form validation failed");
    public static IEC E_VAL_1 = new IEC(1, VALIDATION, "Field: ''{0}'' is a Mandatory field");
    public static IEC E_VAL_2 = new IEC(2, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only ''{2}''");
    public static IEC E_VAL_3 = new IEC(3, VALIDATION, "Field: ''{0}'' is invalid as It doesn''t allow Empty String");
    public static IEC E_VAL_4 = new IEC(4, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows max length ''{2}'' char");
    public static IEC E_VAL_5 = new IEC(5, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows min length ''{2}'' char");
    public static IEC E_VAL_6 = new IEC(6, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows length of min ''{2}'' to max ''{3}'' char");
    public static IEC E_VAL_7 = new IEC(7, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows value of length ''{2}'' char");
    public static IEC E_VAL_8 = new IEC(8, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows max value ''{2}''");
    public static IEC E_VAL_9 = new IEC(9, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows min value ''{2}''");
    public static IEC E_VAL_10 = new IEC(10, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only Future Date");
    public static IEC E_VAL_11 = new IEC(11, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only positive numbers");
    public static IEC E_VAL_12 = new IEC(12, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only positive numbers and zero");
    public static IEC E_VAL_13 = new IEC(13, VALIDATION, "");
    public static IEC E_VAL_14 = new IEC(14, VALIDATION, "");
    public static IEC E_VAL_15 = new IEC(15, VALIDATION, "");
    public static IEC E_VAL_16 = new IEC(16, VALIDATION, "");
    public static IEC E_VAL_17 = new IEC(17, VALIDATION, "");
    public static IEC E_VAL_18 = new IEC(18, VALIDATION, "");
    public static IEC E_VAL_19 = new IEC(19, VALIDATION, "");
    public static IEC E_VAL_20 = new IEC(20, VALIDATION, "AM Code ''{0}'' isn't found in the System");
    public static IEC E_VAL_21 = new IEC(21, VALIDATION, "Field Name is Empty String");
    public static IEC E_VAL_22 = new IEC(22, VALIDATION, "Incorrect Length Annotation Attributes");
    public static IEC E_VAL_23 = new IEC(23, VALIDATION, "");
    public static IEC E_VAL_24 = new IEC(24, VALIDATION, "");

    public static IEC E_LOG_1 = new IEC(1, LOGGER, "Failed to load ''{0}'' Logger Configuration");
    public static IEC E_LOG_2 = new IEC(2, LOGGER, "Failed to load AM-Resources Internal Logger Configuration");
    public static IEC E_LOG_3 = new IEC(3, LOGGER, "Internal Logger Configuration has no Application Tags");
    public static IEC E_LOG_4 = new IEC(4, LOGGER, "AM-Resources Application Tag isn't found in the Internal Logger Configuration");
    public static IEC E_LOG_5 = new IEC(5, LOGGER, "Failed while validating AM-Resources Application Tag");

    public static IEC E_CFG_1 = new IEC(1, CONFIG, "Value's type is null");
    public static IEC E_CFG_2 = new IEC(2, CONFIG, "''{0}'' isn''t a supported as a Value Type");
    public static IEC E_CFG_3 = new IEC(3, CONFIG, "Failed to read Configuration: ''{0}''");
    public static IEC E_CFG_4 = new IEC(4, CONFIG, "Failed while updating value of Configuration ''{0}''");

    public static IEC E_SYS_0 = new IEC(0, SYSTEM, "Internal Server Error");
    public static IEC E_SYS_1 = new IEC(1, SYSTEM, "Internal Server Error, Can''t initiate Component: ''{0}'', Due to: ''{1}''");
    public static IEC E_SYS_2 = new IEC(2, SYSTEM, "Internal Server Error, Can''t read Raw Message of Code: ''{0}'', Due to: ''{1}''");
    public static IEC E_SYS_3 = new IEC(3, SYSTEM, "Internal Server Error, Can''t read Full Message of Code: ''{0}'', Due to: ''{1}''");
    public static IEC E_SYS_4 = new IEC(4, SYSTEM, "AM Code is null");

    public static IEC E_MH_1 = new IEC(1, MSG_HANDLER, "Can''t use Configuration Code Here");
    public static IEC E_MH_2 = new IEC(2, MSG_HANDLER, "The Message doesn''t need Placeholder arguments");
//    public static IEC E_MH_3 = new IEC(3, MSG_HANDLER, "AM Code is null");
    public static IEC E_MH_4 = new IEC(4, MSG_HANDLER, "Arguments: ''{0}'' provided aren't compatible with placeholders num: #{1} needed in the message");
    public static IEC E_MH_5 = new IEC(5, MSG_HANDLER, "Failed to format message: ''{0}''");

//    public static IEC SYS_2  = new IEC(2, SYSTEM, "Please provide Placeholder arguments for Message");
//    public static IEC SYS_3  = new IEC(3, SYSTEM, "Too Many arguments for the Placeholders in the message");
//    public static IEC SYS_4  = new IEC(4, SYSTEM, "Too Many Placeholders in the message than the arguments provided");
//    public static IEC SYS_5  = new IEC(5, SYSTEM, "Formatting Message: ''{0}'' failed");
//    public static IEC SYS_7  = new IEC(7, SYSTEM, "''{0}'' of Code can''t be null");
//    public static IEC SYS_8  = new IEC(8, SYSTEM, "");
//    public static IEC SYS_9  = new IEC(9, SYSTEM, "");
    public static IEC SYS_11 = new IEC(10, SYSTEM, "Session Object is null, Can't use ''{0}'' Handler to log the ''{1}'' message of Code ''{2}''");
    public static IEC SYS_12 = new IEC(11, SYSTEM, "Failed while getting ''{0}'' Message of Code ''{1}''");
    public static IEC SYS_15 = new IEC(12, SYSTEM, "Both Exception and Error Code are null, Can't Log this Error");
    public static IEC SYS_16 = new IEC(13, SYSTEM, "Value of the System Config property can''t be null");
    public static IEC SYS_17 = new IEC(14, SYSTEM, "Invalid Logging Level");



    public static IEC E_IO_1 = new IEC(1, IO, "Can''t load File: ''{0}''");
    public static IEC E_IO_2 = new IEC(2, IO, "Invalid File Name: ''{0}''");
    public static IEC E_IO_3 = new IEC(3, IO, "File: ''{0}'' isn''t found");
    public static IEC E_IO_4 = new IEC(4, IO, "File path: ''{0}'' is invalid");
    public static IEC E_IO_5 = new IEC(5, IO, "File: ''{0}'' is empty");
    public static IEC E_IO_6 = new IEC(6, IO, "Accessing File: ''{0}'' is denied");
    public static IEC E_IO_7 = new IEC(7, IO, "Can''t Write File: ''{0}''");
    public static IEC E_IO_8 = new IEC(8, IO, "Property File: ''{0}'' isn't loaded in the System or Empty");
    public static IEC E_IO_9 = new IEC(9, IO, "Property: ''{0}'' isn''t found in File: ''{1}''");
    public static IEC E_IO_10 = new IEC(10, IO, "Value of Property: ''{0}'' in File: ''{1}'' is empty");
    public static IEC E_IO_11 = new IEC(11, IO, "Reading Value of Property: ''{0}'' from File: ''{1}'' failed");

    //    public static IEC IO_1 = new IEC(1, IO, "Invalid File Name: ''{0}'' of ''{1}'' Component");
//    public static IEC IO_4 = new IEC(4, IO, "Reading Resource File: ''{0}'' failed");
//    public static IEC IO_5 = new IEC(5, IO, "Property File: ''{0}'' isn''t loaded in the System or Empty");
    public static IEC IO_8 = new IEC(8, IO, "Reading ''{0}'' of Code: ''{1}'' failed");
    public static IEC IO_9 = new IEC(9, IO, "Parameter: ''{0}'' is missing from File: ''{1}''");
    public static IEC IO_11 = new IEC(11, IO, "Changing value of ''{0}'' of Code: ''{1}'' failed");

    public static IEC E_SEC_1 = new IEC(1, SECURITY, "Generating Hashed Password failed");
//    public static IEC SEC_2 = new IEC(2, SECURITY, "Request ''{0}'' isn't Authorized to Access our Services");
    public static IEC E_SEC_3 = new IEC(3, SECURITY, "Invalid Hashing Algorithm ''{0}''");
    public static IEC E_SEC_4 = new IEC(4, SECURITY, "Initializing the Hashing Algorithm Failed");

    public static IEC E_DB_1 = new IEC(1, DB, "This Record ''{0}'' already exists in the Database");
    public static IEC E_DB_2 = new IEC(2, DB, "''{0}'' Entity has illegal Arguments");
    public static IEC E_DB_3 = new IEC(3, DB, "Function needs to be Transactional");
    public static IEC E_DB_4 = new IEC(4, DB, "Flushing the Hibernate ''{0}'' Operation failed");
    public static IEC E_DB_5 = new IEC(5, DB, "Hibernate Constraints ''{0}'' are violated while inserting record ''{1}''");
    public static IEC E_DB_6 = new IEC(6, DB, "Invalid Hibernate Entity: ''{0}'' or Invalid ID: ''{1}'' for Entity: ''{2}''");
    public static IEC E_DB_7 = new IEC(7, DB, "Lookup ''{0}'' of ID: ''{1}'' isn''t found in the System");
    public static IEC E_DB_8 = new IEC(8, DB, "Failed while inserting record ''{0}'' in Database");
    public static IEC E_DB_9 = new IEC(9, DB, "Failed while selecting entity: ''{0}'' with ID: ''{1}'' from Database");
    public static IEC E_DB_10 = new IEC(10, DB, "Failed while updating record ''{0}'' in Database");
    public static IEC E_DB_11 = new IEC(11, DB, "Failed while removing record ''{0}'' from Database");
//    public static IEC DB_12 = new IEC(12, DB, "Invalid HQL Query Parameter or Parameter Type ''{0}''");
//    public static IEC DB_13 = new IEC(13, DB, "Invalid HQL Query Hint ''{0}''");
    public static IEC E_DB_14 = new IEC(14, DB, "No Parameters provided for the Query");
    public static IEC E_DB_15 = new IEC(15, DB, "More than one record returned from ''{0}'' table with attributes [''{1}''] = [''{2}'']");
    public static IEC E_DB_16 = new IEC(16, DB, "No records return from ''{0}'' table with attributes [''{1}''] = [''{2}'']");
    public static IEC E_DB_17 = new IEC(17, DB, "Invalid Hibernate operation with Query ''{0}''");
    public static IEC E_DB_18 = new IEC(18, DB, "The Query ''{0}'' exceeds its timeout Limit, and Only this Query is rolled back");
    public static IEC E_DB_19 = new IEC(19, DB, "Locking Data Resources failed, while executing Query ''{0}''");
    public static IEC E_DB_20 = new IEC(20, DB, "Locking Data Resources exceeds its timeout Limit, while executing Query ''{0}''");
    public static IEC E_DB_21 = new IEC(21, DB, "This Query ''{0}'' needs to be Transactional");
    public static IEC E_DB_22 = new IEC(22, DB, "The Query ''{0}'' exceeds its timeout Limit, and The Transaction is rolled back");
    public static IEC E_DB_23 = new IEC(23, DB, "Failed while checking record of Entity: ''{0}'' found in Database");
    public static IEC E_DB_24 = new IEC(24, DB, "Failed while selecting single record from Entity: ''{0}''");
    public static IEC E_DB_25 = new IEC(25, DB, "Failed while constructing Query");
    public static IEC E_DB_26 = new IEC(26, DB, "Invalid Hibernate Entity: ''{0}''");
    public static IEC E_DB_27 = new IEC(27, DB, "Selecting all entities of Type: ''{0}'' from Database failed");
    public static IEC E_DB_28 = new IEC(28, DB, "Entity: ''{0}'' is not found in Database or Persistence Context");
    public static IEC E_DB_29 = new IEC(29, DB, "Failed while selecting list of records from Entity: ''{0}''");

    public static IEC E_JMS_1 = new IEC(1, JMS, "Failed to Initialize Queue ''{0}''");
    public static IEC E_JMS_2 = new IEC(2, JMS, "Queue Session of Queue ''{0}'' isn''t initiated");
    public static IEC E_JMS_3 = new IEC(3, JMS, "JMS Sender of Queue ''{0}'' isn''t initiated");
    public static IEC E_JMS_4 = new IEC(4, JMS, "Failed to send ''{0}'' Message to ''{0}'' Queue");
    public static IEC E_JMS_5 = new IEC(5, JMS, "Message of invalid type in Queue: ''{0}''");
    public static IEC E_JMS_6 = new IEC(6, JMS, "");
    public static IEC E_JMS_7 = new IEC(7, JMS, "");
    public static IEC E_JMS_8 = new IEC(8, JMS, "");


    private IEC(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.ERROR, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM");
    }

    
}
