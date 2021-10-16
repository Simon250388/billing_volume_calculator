package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;

public interface ServiceStateService {

  Set<AccountingPointServiceStateDto> currentActiveByKeyRoomId(
          UUID keyRoomId, Instant period, Instant periodFact);

  CompletableFuture<Set<AccountingPointServiceStateDto>> currentActiveByEntityServiceIdAsync(
      Set<UUID> allEntityServiceId);

  AccountingPointServiceStateDto changeState(AccountingPointServiceStateDto request);
}
