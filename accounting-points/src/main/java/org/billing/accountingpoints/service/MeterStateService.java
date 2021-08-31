package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDtoValue;

public interface MeterStateService {
  CompletableFuture<Set<AccountingPointMeterStateDtoValue>> currentStateByEntityServiceIdAsync(
      Set<Long> accountingPointKeyRoomServiceEntities, Instant period);
}
