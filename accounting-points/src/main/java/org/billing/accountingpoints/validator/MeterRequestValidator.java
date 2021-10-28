package org.billing.accountingpoints.validator;

import javax.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.billing.accountingpoints.request.MeterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class MeterRequestValidator extends AbstractValidator<MeterRequest> {
  @Autowired
  public MeterRequestValidator(Validator validator) {
    super(validator);
  }

  @Override
  public void customValidate(MeterRequest target, Errors errors) {
    if (target.getId() == null && StringUtils.isEmpty(target.getFactoryNumber())) {
      errors.rejectValue("factoryNumber", "when id is null then factory_number must be not empty");
    } else if (target.getId() == null && !target.getFactoryNumber().matches("[0-9]{1,5}")) {
      errors.rejectValue("factoryNumber", "must be patter [0-9]{1,5}");
    }
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return MeterRequest.class.equals(clazz);
  }
}
