package org.billing.api.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValueExistValidator implements ConstraintValidator<ValueExist, String> {
  private final List<ValueExistChecker> checkers;
  private ValueExistChecker checker;

  @Override
  public void initialize(ValueExist constraintAnnotation) {
    this.checker = this.checkers.stream()
        .filter(checker -> checker.getValueType().equals(constraintAnnotation.valueType()))
        .findFirst()
        .orElseThrow();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return checker.exist(value);
  }
}
