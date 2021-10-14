package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDto;

public interface MeterStateService {
  CompletableFuture<Set<AccountingPointMeterStateDto>> currentStateByEntityServiceIdAsync(
          Set<UUID> accountingPointKeyRoomServiceEntities, Instant period);
}
