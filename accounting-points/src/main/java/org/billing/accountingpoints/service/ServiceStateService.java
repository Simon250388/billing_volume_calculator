package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDtoValue;

public interface ServiceStateService {

  Set<AccountingPointServiceStateDtoValue> currentActiveByKeyRoomId(
          Long keyRoomId, Instant period, Instant periodFact);

  CompletableFuture<Set<AccountingPointServiceStateDtoValue>> currentActiveByEntityServiceIdAsync(
      Set<Long> allEntityServiceId);
}
