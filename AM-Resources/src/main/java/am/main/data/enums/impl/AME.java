package am.main.data.enums.impl;

import am.main.data.enums.CodeTypes;
import am.main.spi.AMCode;

/**
 * Created by ahmed.motair on 1/22/2018.
 */
public class AME extends AMCode {
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

    public static AME E_VAL_0 = new AME(0, VALIDATION, "{0} Form validation failed");
    public static AME E_VAL_1 = new AME(1, VALIDATION, "Field: ''{0}'' is a Mandatory field");
    public static AME E_VAL_2 = new AME(2, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only ''{2}''");
    public static AME E_VAL_3 = new AME(3, VALIDATION, "Field: ''{0}'' is invalid as It doesn''t allow Empty String");
    public static AME E_VAL_4 = new AME(4, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows max length ''{2}'' char");
    public static AME E_VAL_5 = new AME(5, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows min length ''{2}'' char");
    public static AME E_VAL_6 = new AME(6, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows length of min ''{2}'' to max ''{3}'' char");
    public static AME E_VAL_7 = new AME(7, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows value of length ''{2}'' char");
    public static AME E_VAL_8 = new AME(8, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows max value ''{2}''");
    public static AME E_VAL_9 = new AME(9, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows min value ''{2}''");
    public static AME E_VAL_10 = new AME(10, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only Future Date");
    public static AME E_VAL_11 = new AME(11, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only positive numbers");
    public static AME E_VAL_12 = new AME(12, VALIDATION, "Value: ''{0}'' is invalid for Field: ''{1}'', as It allows only positive numbers and zero");
    public static AME E_VAL_13 = new AME(13, VALIDATION, "{0} DTO validation failed");
    public static AME E_VAL_14 = new AME(14, VALIDATION, "");
    public static AME E_VAL_15 = new AME(15, VALIDATION, "");
    public static AME E_VAL_16 = new AME(16, VALIDATION, "");
    public static AME E_VAL_17 = new AME(17, VALIDATION, "");
    public static AME E_VAL_18 = new AME(18, VALIDATION, "");
    public static AME E_VAL_19 = new AME(19, VALIDATION, "");
    public static AME E_VAL_20 = new AME(20, VALIDATION, "AM Code ''{0}'' isn't found in the System");
    public static AME E_VAL_21 = new AME(21, VALIDATION, "Field Name is Empty String");
    public static AME E_VAL_22 = new AME(22, VALIDATION, "Incorrect Length Annotation Attributes");
    public static AME E_VAL_23 = new AME(23, VALIDATION, "");
    public static AME E_VAL_24 = new AME(24, VALIDATION, "");

    public static AME E_LOG_1 = new AME(1, LOGGER, "Failed to load ''{0}'' Logger Configuration");
    public static AME E_LOG_2 = new AME(2, LOGGER, "Failed to load AM-Resources Internal Logger Configuration");
    public static AME E_LOG_3 = new AME(3, LOGGER, "Internal Logger Configuration has no Application Tags");
    public static AME E_LOG_4 = new AME(4, LOGGER, "AM-Resources Application Tag isn't found in the Internal Logger Configuration");
    public static AME E_LOG_5 = new AME(5, LOGGER, "Failed while validating AM-Resources Application Tag");

    public static AME E_CFG_1 = new AME(1, CONFIG, "Value's type is null");
    public static AME E_CFG_2 = new AME(2, CONFIG, "''{0}'' isn''t a supported as a Value Type");
    public static AME E_CFG_3 = new AME(3, CONFIG, "Failed to read Configuration: ''{0}''");
    public static AME E_CFG_4 = new AME(4, CONFIG, "Failed while updating value of Configuration ''{0}''");

    public static AME E_SYS_0 = new AME(0, SYSTEM, "Internal Server Error");
    public static AME E_SYS_1 = new AME(1, SYSTEM, "Internal Server Error, Can''t initiate Component: ''{0}'', Due to: ''{1}''");
    public static AME E_SYS_2 = new AME(2, SYSTEM, "Internal Server Error, Can''t read Raw Message of Code: ''{0}'', Due to: ''{1}''");
    public static AME E_SYS_3 = new AME(3, SYSTEM, "Internal Server Error, Can''t read Full Message of Code: ''{0}'', Due to: ''{1}''");
    public static AME E_SYS_4 = new AME(4, SYSTEM, "AM Code is null");

    public static AME E_MH_1 = new AME(1, MSG_HANDLER, "Can''t use Configuration Code Here");
    public static AME E_MH_2 = new AME(2, MSG_HANDLER, "The Message doesn''t need Placeholder arguments");
//    public static AME E_MH_3 = new AME(3, MSG_HANDLER, "AM Code is null");
    public static AME E_MH_4 = new AME(4, MSG_HANDLER, "Arguments: ''{0}'' provided aren't compatible with placeholders num: #{1} needed in the message");
    public static AME E_MH_5 = new AME(5, MSG_HANDLER, "Failed to format message: ''{0}''");

//    public static AME SYS_2  = new AME(2, SYSTEM, "Please provide Placeholder arguments for Message");
//    public static AME SYS_3  = new AME(3, SYSTEM, "Too Many arguments for the Placeholders in the message");
//    public static AME SYS_4  = new AME(4, SYSTEM, "Too Many Placeholders in the message than the arguments provided");
//    public static AME SYS_5  = new AME(5, SYSTEM, "Formatting Message: ''{0}'' failed");
//    public static AME SYS_7  = new AME(7, SYSTEM, "''{0}'' of Code can''t be null");
//    public static AME SYS_8  = new AME(8, SYSTEM, "");
//    public static AME SYS_9  = new AME(9, SYSTEM, "");
    public static AME SYS_11 = new AME(10, SYSTEM, "Session Object is null, Can't use ''{0}'' Handler to log the ''{1}'' message of Code ''{2}''");
    public static AME SYS_12 = new AME(11, SYSTEM, "Failed while getting ''{0}'' Message of Code ''{1}''");
    public static AME SYS_15 = new AME(12, SYSTEM, "Both Exception and Error Code are null, Can't Log this Error");
    public static AME SYS_16 = new AME(13, SYSTEM, "Value of the System Config property can''t be null");
    public static AME SYS_17 = new AME(14, SYSTEM, "Invalid Logging Level");



    public static AME E_IO_1 = new AME(1, IO, "Can''t load File: ''{0}''");
    public static AME E_IO_2 = new AME(2, IO, "Invalid File Name: ''{0}''");
    public static AME E_IO_3 = new AME(3, IO, "File: ''{0}'' isn''t found");
    public static AME E_IO_4 = new AME(4, IO, "File path: ''{0}'' is invalid");
    public static AME E_IO_5 = new AME(5, IO, "File: ''{0}'' is empty");
    public static AME E_IO_6 = new AME(6, IO, "Accessing File: ''{0}'' is denied");
    public static AME E_IO_7 = new AME(7, IO, "Can''t Write File: ''{0}''");
    public static AME E_IO_8 = new AME(8, IO, "Property File: ''{0}'' isn't loaded in the System or Empty");
    public static AME E_IO_9 = new AME(9, IO, "Property: ''{0}'' isn''t found in File: ''{1}''");
    public static AME E_IO_10 = new AME(10, IO, "Value of Property: ''{0}'' in File: ''{1}'' is empty");
    public static AME E_IO_11 = new AME(11, IO, "Reading Value of Property: ''{0}'' from File: ''{1}'' failed");

    //    public static AME IO_1 = new AME(1, IO, "Invalid File Name: ''{0}'' of ''{1}'' Component");
//    public static AME IO_4 = new AME(4, IO, "Reading Resource File: ''{0}'' failed");
//    public static AME IO_5 = new AME(5, IO, "Property File: ''{0}'' isn''t loaded in the System or Empty");
    public static AME IO_8 = new AME(8, IO, "Reading ''{0}'' of Code: ''{1}'' failed");
//    public static AME IO_9 = new AME(9, IO, "Parameter: ''{0}'' is missing from File: ''{1}''");
    public static AME IO_11 = new AME(11, IO, "Changing value of ''{0}'' of Code: ''{1}'' failed");

    public static AME E_SEC_1 = new AME(1, SECURITY, "Generating Hashed Password failed");
//    public static AME SEC_2 = new AME(2, SECURITY, "Request ''{0}'' isn't Authorized to Access our Services");
    public static AME E_SEC_3 = new AME(3, SECURITY, "Invalid Hashing Algorithm ''{0}''");
    public static AME E_SEC_4 = new AME(4, SECURITY, "Initializing the Hashing Algorithm Failed");

    public static AME E_DB_1 = new AME(1, DB, "This Record ''{0}'' already exists in the Database");
    public static AME E_DB_2 = new AME(2, DB, "''{0}'' Entity has illegal Arguments");
    public static AME E_DB_3 = new AME(3, DB, "Function needs to be Transactional");
    public static AME E_DB_4 = new AME(4, DB, "Flushing the Hibernate ''{0}'' Operation failed");
    public static AME E_DB_5 = new AME(5, DB, "Hibernate Constraints ''{0}'' are violated while inserting record ''{1}''");
    public static AME E_DB_6 = new AME(6, DB, "Invalid Hibernate Entity: ''{0}'' or Invalid ID: ''{1}'' for Entity: ''{2}''");
    public static AME E_DB_7 = new AME(7, DB, "Lookup ''{0}'' of ID: ''{1}'' isn''t found in the System");
    public static AME E_DB_8 = new AME(8, DB, "Failed while inserting record ''{0}'' in Database");
    public static AME E_DB_9 = new AME(9, DB, "Failed while selecting entity: ''{0}'' with ID: ''{1}'' from Database");
    public static AME E_DB_10 = new AME(10, DB, "Failed while updating record ''{0}'' in Database");
    public static AME E_DB_11 = new AME(11, DB, "Failed while removing record ''{0}'' from Database");
//    public static AME DB_12 = new AME(12, DB, "Invalid HQL Query Parameter or Parameter Type ''{0}''");
//    public static AME DB_13 = new AME(13, DB, "Invalid HQL Query Hint ''{0}''");
    public static AME E_DB_14 = new AME(14, DB, "No Parameters provided for the Query");
    public static AME E_DB_15 = new AME(15, DB, "More than one record returned from ''{0}'' table with attributes [''{1}''] = [''{2}'']");
    public static AME E_DB_16 = new AME(16, DB, "No records return from ''{0}'' table with attributes [''{1}''] = [''{2}'']");
    public static AME E_DB_17 = new AME(17, DB, "Invalid Hibernate operation with Query ''{0}''");
    public static AME E_DB_18 = new AME(18, DB, "The Query ''{0}'' exceeds its timeout Limit, and Only this Query is rolled back");
    public static AME E_DB_19 = new AME(19, DB, "Locking Data Resources failed, while executing Query ''{0}''");
    public static AME E_DB_20 = new AME(20, DB, "Locking Data Resources exceeds its timeout Limit, while executing Query ''{0}''");
    public static AME E_DB_21 = new AME(21, DB, "This Query ''{0}'' needs to be Transactional");
    public static AME E_DB_22 = new AME(22, DB, "The Query ''{0}'' exceeds its timeout Limit, and The Transaction is rolled back");
    public static AME E_DB_23 = new AME(23, DB, "Failed while checking record of Entity: ''{0}'' found in Database");
    public static AME E_DB_24 = new AME(24, DB, "Failed while selecting single record from Entity: ''{0}''");
    public static AME E_DB_25 = new AME(25, DB, "Failed while constructing Query");
    public static AME E_DB_26 = new AME(26, DB, "Invalid Hibernate Entity: ''{0}''");
    public static AME E_DB_27 = new AME(27, DB, "Selecting all entities of Type: ''{0}'' from Database failed");
    public static AME E_DB_28 = new AME(28, DB, "Entity: ''{0}'' is not found in Database or Persistence Context");
    public static AME E_DB_29 = new AME(29, DB, "Failed while selecting list of records from Entity: ''{0}''");

    public static AME E_JMS_1 = new AME(1, JMS, "Failed to Initialize Queue ''{0}''");
    public static AME E_JMS_2 = new AME(2, JMS, "Queue Session of Queue ''{0}'' isn''t initiated");
    public static AME E_JMS_3 = new AME(3, JMS, "JMS Sender of Queue ''{0}'' isn''t initiated");
    public static AME E_JMS_4 = new AME(4, JMS, "Failed to send ''{0}'' Message to ''{0}'' Queue");
    public static AME E_JMS_5 = new AME(5, JMS, "Message of invalid type in Queue: ''{0}'', Expected: ''{1}'' and actual: ''{2}''");
    public static AME E_JMS_6 = new AME(6, JMS, "");
    public static AME E_JMS_7 = new AME(7, JMS, "");
    public static AME E_JMS_8 = new AME(8, JMS, "");


    private AME(Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG) {
        super(CodeTypes.ERROR, true, CODE_ID, CODE_NAME, INTERNAL_MSG, "AM");
    }

    
}
