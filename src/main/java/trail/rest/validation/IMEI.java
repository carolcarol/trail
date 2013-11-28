package trail.rest.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import trail.rest.beans.Device;



@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IMEI.Validator.class)
public @interface IMEI {
 
    String message() default "{trail.validation.constraints.imei}";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
    public class Validator implements ConstraintValidator<IMEI, Device> {

        public void initialize(final IMEI msisdn) {
        }

        public boolean isValid(final Device device, final ConstraintValidatorContext constraintValidatorContext) {
            return ! device.getImei ().isEmpty ();//TODO proper validation
        }
    }
}
