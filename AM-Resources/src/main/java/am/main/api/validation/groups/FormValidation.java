package am.main.api.validation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
@GroupSequence({Default.class, RequiredValidation.class, BlankValidation.class, LengthValidation.class, InvalidValidation.class})
public interface FormValidation {
    String REQUIRED = "AM_VAL_1";
    String REGEX = "AM_VAL_2";
    String EMPTY_STR = "AM_VAL_3";
    String MAX_LENGTH = "AM_VAL_4";
    String MIN_LENGTH = "AM_VAL_5";
    String MIN_MAX_LENGTH = "AM_VAL_6";
    String EQ_LENGTH = "AM_VAL_7";
    String MAX_VALUE = "AM_VAL_8";
    String MIN_VALUE = "AM_VAL_9";
    String FUTURE_DATE = "AM_VAL_10";
    String POSITIVE_NUM = "AM_VAL_11";
    String POSITIVE_NUM_AND_ZERO = "AM_VAL_12";
}
