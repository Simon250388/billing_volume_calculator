package org.billing.api.app.useCase.accountingPoint;

import org.billing.api.model.accountingPoint.AccountingPoint;
import org.springframework.http.ResponseEntity;

public interface AccountingPointStatusService {
  ResponseEntity<AccountingPoint> update(
      String accountingPointId, AccountingPoint request);

  ResponseEntity<AccountingPoint> save(AccountingPoint request);

  void update(String accountingPointId);
}
