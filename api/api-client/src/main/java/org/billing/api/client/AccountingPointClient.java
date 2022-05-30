package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.http.ResponseEntity;

public interface AccountingPointClient {
  ResponseEntity<Collection<AccountingPointResponse>> getAll(String keyRoomId);

  ResponseEntity<Object> create(AccountingPointRequest request);

  ResponseEntity<Object> update(
      String accountingPointId, AccountingPointRequest request);
}
