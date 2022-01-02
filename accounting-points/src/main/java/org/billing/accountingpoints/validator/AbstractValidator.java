package org.billing.accountingpoints.validator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractValidator<T> implements Validator {

  private final javax.validation.Validator validator;

  protected AbstractValidator(javax.validation.Validator validator) {
    this.validator = validator;
  }

  @Override
  public void validate(Object target, Errors errors) {

    final Set<ConstraintViolation<Object>> validates =
        validator.validate(target, target.getClass());

    for (ConstraintViolation<Object> constraintViolation : validates) {
      String property = constraintViolation.getPropertyPath().toString();
      String message = constraintViolation.getMessage();
      errors.rejectValue(property, "", message);
    }

    customValidate((T) target, errors);
  }

  public abstract void customValidate(T target, Errors errors);
}
