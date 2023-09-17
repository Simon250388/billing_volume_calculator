package org.billing.api.app.validator;

import lombok.RequiredArgsConstructor;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExistChecker;
import org.billing.api.repository.AccountingPointDbService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointValueExistChecker implements ValueExistChecker {
  private final AccountingPointDbService dbService;
  @Override
  public boolean exist(String value) {
    return dbService.exist(value);
  }

  @Override
  public ExistValueType getValueType() {
    return ExistValueType.ACCOUNTING_POINT;
  }
}
