package am.main.api.validation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
@GroupSequence({Default.class, RequiredValidation.class, BlankValidation.class, LengthValidation.class, InvalidValidation.class})
public interface FormValidation {
    String REQUIRED = "AMT_0003";
    String REGEX = "AMT_0004";
    String EMPTY_STR = "AMT_0005";
    String MAX_LENGTH = "AMT_0006";
    String MIN_LENGTH = "AMT_0007";
    String MIN_MAX_LENGTH = "AMT_0008";
    String EQ_LENGTH = "AMT_0009";
    String MAX_VALUE = "AMT_0010";
    String MIN_VALUE = "AMT_0011";
    String FUTURE_DATE = "AMT_0012";
    String POSITIVE_NUM = "AMT_0013";
    String POSITIVE_NUM_AND_ZERO = "AMT_0023";
}
