package lt.techin.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface User {

  String message() default "User must be lowercase letters (numbers allowed), at least 4\n" +
          "characters long.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
