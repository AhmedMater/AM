package am.core.logger;

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
    SYS_012(""),

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
    IO_010(""),
    IO_011(""),
    IO_012(""),
    IO_013(""),
    IO_014(""),
    IO_015("");

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
