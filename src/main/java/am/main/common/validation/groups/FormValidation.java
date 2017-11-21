package am.main.common.validation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by ahmed.motair on 11/20/2017.
 */
@GroupSequence({Default.class, RequiredValidation.class, BlankValidation.class, LengthValidation.class, InvalidValidation.class})
public interface FormValidation {
}
