package lt.techin.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<User, String> {

  @Override
  public boolean isValid(String user, ConstraintValidatorContext constraintValidatorContext) {
    return user != null && user.matches("^[a-z0-9]{4,}$");
  }
}
