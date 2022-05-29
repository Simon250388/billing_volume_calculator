package org.billing.api.app.useCase.accountingPoint;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.billing.api.repository.AccountingPointDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("AccountingPointActiveStatusService")
@RequiredArgsConstructor
public class AccountingPointActiveStatusService implements AccountingPointStatusService {

  private final AccountingPointDbService accountingPointDbService;

  @Override
  public ResponseEntity<AccountingPointResponse> update(AccountingPointRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<AccountingPointResponse> save(AccountingPointRequest request) {
    String id = UUID.randomUUID().toString();
    return ResponseEntity.ok(accountingPointDbService.save(id, request));
  }

  @Override
  public void update(String accountingPointId) {}
}
