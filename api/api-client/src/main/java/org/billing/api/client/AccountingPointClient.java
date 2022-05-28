package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.http.ResponseEntity;

public interface AccountingPointClient {
  ResponseEntity<Collection<AccountingPointResponse>> getAllAccountingPoints();

  ResponseEntity<Object> createAccountingPoint(AccountingPointRequest request);

  ResponseEntity<Object> updateAccountingPoint(
      String accountingPointId, AccountingPointRequest request);
}
