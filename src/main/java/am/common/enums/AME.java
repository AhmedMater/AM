package am.common.enums;

/**
 * Created by ahmed.motair on 9/21/2017.
 */
public enum AME {
    SYS_001("AM-SYS-001: The Message doesn''t need Placeholder arguments"),
    SYS_002("AM-SYS-002: Please provide Placeholder arguments for Message"),
    SYS_003("AM-SYS-003: Too Many arguments for the Placeholders in the message"),
    SYS_004("AM-SYS-004: Too Many Placeholders in the message than the arguments provided"),
    SYS_005("AM-SYS-005: Formatting Message: ''{0}'' failed,\nDue to: ''{1}''"),
    SYS_006("AM-SYS-006: Internal Server Error, Can''t initiate Component: ''{0}'',\nDue to: ''{1}''"),
    SYS_007("AM-SYS-007: ''{0}'' of Code can''t be null"),
    SYS_008("AM-SYS-008: Class type of the System Config value can''t be null"),
    SYS_009("AM-SYS-009: ''{0}'' isn''t a supported System Config Property Type"),
    SYS_010("AM-SYS-010: Failed while Logging ''{0}'' Code: ''{1}''"),
    SYS_011("AM-SYS-011: Session Object is null, Can't use ''{0}'' Handler to log the ''{1}'' message of Code ''{2}''"),
    SYS_012("AM-SYS-012: Failed while getting ''{0}'' Message of Code ''{1}'',\nDue to: ''{2}''"),

    ENM_001("AM-ENM-001: Invalid ''{0}'' E-Mail Address"),
    ENM_002("AM-ENM-002: Failed to send the Message: ''{0}'', To: ''{1}''"),
    ENM_003("AM-ENM-003: Sending Email: '{0}' To: '{1} failed,\nDue to: ''{2}''"),

    IO_001("AM-IO-001: Invalid File Name: ''{0}'' of ''{1}'' Component"),
    IO_002("AM-IO-002: File: ''{0}'' isn''t found"),
    IO_003("AM-IO-003: Can''t load File: ''{0}'',\nDue To: ''{1}''"),
    IO_004("AM-IO-004: Reading Resource File: ''{0}'' failed,\nDue to: ''{1}''"),
    IO_005("AM-IO-005: Property File: ''{0}'' isn''t loaded in the System or Empty"),
    IO_006("AM-IO-006: Key: ''{0}'' isn''t found in Property File: ''{1}''"),
    IO_007("AM-IO-007: Reading Value of Property: ''{0}'' from File:''{1}'' failed,\nDue to: ''{2}''"),
    IO_008("AM-IO-008: Reading ''{0}'' of Code: ''{1}'' failed,\nDue to: ''{2}''"),
    IO_009("AM-IO-009: Parameter: ''{0}'' is missing from File: ''{1}''"),

    SEQ_001("AMT-SEQ-001: Generating Hashed Password failed"),
    SEQ_002("AMT-SEQ-002: Request ''{0}'' isn't Authorized to Access our Services"),
    SEQ_003("AMT-SEQ-003: Invalid Hashing Algorithm ''{0}''"),
    SEQ_004("AMT-SEQ-004: Initializing the Hashing Algorithm Failed"),

    DB_001("AM-DB-001: This Record ''{0}'' already exists in the Database"),
    DB_002("AM-DB-002: Invalid Hibernate Entity: ''{0}''"),
    DB_003("AM-DB-003: This Function needs to be Transactional"),
    DB_004("AM-DB-004: Flushing the Hibernate ''{0}'' Operation failed"),
    DB_005("AM-DB-005: Hibernate Constraints ''{0}'' are violated while inserting record ''{1}''"),
    DB_006("AM-DB-006: Invalid Hibernate Entity: ''{0}'' or Invalid ID: ''{1}'' for Entity: ''{2}''"),
    DB_007("AM-DB-007: Invalid Hibernate Entity: ''{0}'' or this entity is removed before"),
    DB_008("AM-DB-008: Inserting record ''{0}'' in Database failed,\nDue to: ''{1}''"),
    DB_009("AM-DB-009: Selecting Entity: ''{0}'' with ID: ''{1}'' from Database failed,\nDue to: ''{2}''"),
    DB_010("AM-DB-010: Updating record ''{0}'' in Database failed,\nDue to: ''{1}''"),
    DB_011("AM-DB-011: Removing record ''{0}'' from Database failed,\nDue to: ''{1}''"),
    DB_012("AM-DB-012: Invalid HQL Query Parameter or Parameter Type ''{0}''"),
    DB_013("AM-DB-013: Invalid HQL Query Hint ''{0}''"),
    DB_014("AM-DB-014: Executing Query: ''{0}'' failed,\nDue to: ''{1}''"),
    DB_015("AM-DB-015: More than one record returned from ''{0}'' table with attribute ''{1}'' = ''{2}''"),
    DB_016("AM-DB-016: No records return from ''{0}'' table with attribute ''{1}'' = ''{2}''"),
    DB_017("AM-DB-017: Invalid Function with the hibernate operation with Query ''{0}''"),
    DB_018("AM-DB-018: The Query ''{0}'' exceeds its timeout Limit, and Only this Query is rolled back"),
    DB_019("AM-DB-019: Locking Data Resources failed, while executing Query ''{0}''"),
    DB_020("AM-DB-020: Locking Data Resources exceeds its timeout Limit, while executing Query ''{0}''"),
    DB_021("AM-DB-021: This Query ''{0}'' needs to be Transactional"),
    DB_022("AM-DB-022: The Query ''{0}'' exceeds its timeout Limit, and The Transaction is rolled back"),
    DB_023("AM-DB-023: Checking record of Entity: ''{0}'' isn't found in Database failed,\nDue to: ''{1}''"),
    DB_024("AM-DB-024: Selecting single record from Entity: ''{0}'' failed,\nDue to: ''{1}''");

    private String value;

    AME(String value){
        this.value = value;
    }
    AME(){
    }
    public String value(){
        return value;
    }
    @Override public String toString(){
        return value;
    }

}
