package trail.rest.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import trail.rest.beans.Device;



@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Msisdn.Validator.class)
public @interface Msisdn {
 
    String message() default "{trail.validation.constraints.msisdn}";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
    public class Validator implements ConstraintValidator<Msisdn, Device> {

        public void initialize(final Msisdn msisdn) {
        }

        public boolean isValid(final Device device, final ConstraintValidatorContext constraintValidatorContext) {
            return !device.getMsisdn ().isEmpty ();
        }
    }
}
