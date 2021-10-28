package org.billing.accountingpoints.validator;

import javax.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.billing.accountingpoints.request.AccountingPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AccountingPointRequestValidator extends AbstractValidator<AccountingPointRequest> {
  @Autowired
  public AccountingPointRequestValidator(Validator validator) {
    super(validator);
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return AccountingPointRequest.class.equals(clazz);
  }

  @Override
  public void customValidate(AccountingPointRequest target, Errors errors) {
    if (target.getId() == null && StringUtils.isEmpty(target.getName())) {
      errors.rejectValue("name", "when id is null then name must be not empty");
    }
  }
}
