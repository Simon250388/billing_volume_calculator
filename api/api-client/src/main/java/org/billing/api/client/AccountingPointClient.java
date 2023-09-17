package org.billing.api.client;

import java.util.Collection;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.springframework.http.ResponseEntity;

public interface AccountingPointClient {
  ResponseEntity<Collection<AccountingPoint>> getAll(String keyRoomId);

  ResponseEntity<Object> create(AccountingPoint request);

  ResponseEntity<Object> update(AccountingPoint request);
}
