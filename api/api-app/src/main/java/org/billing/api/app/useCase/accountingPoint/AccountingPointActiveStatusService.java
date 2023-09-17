package org.billing.api.app.useCase.accountingPoint;

import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.billing.api.repository.AccountingPointDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("AccountingPointActiveStatusService")
@RequiredArgsConstructor
public class AccountingPointActiveStatusService implements AccountingPointStatusService {

  private final AccountingPointDbService accountingPointDbService;

  @Override
  public ResponseEntity<AccountingPoint> update(String accountingPointId, AccountingPoint request) {
    return null;
  }

  @Override
  public ResponseEntity<AccountingPoint> save(AccountingPoint request) {
    return ResponseEntity.ok(accountingPointDbService.save(request));
  }

  @Override
  public void update(String accountingPointId) {}
}
