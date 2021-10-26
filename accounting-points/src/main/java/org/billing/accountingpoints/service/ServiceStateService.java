package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;

public interface ServiceStateService {

  Set<AccountingPointServiceStateDto> currentActive(@NonNull UUID keyRoomId);

  Set<AccountingPointServiceStateDto> currentActive(
      @NonNull UUID keyRoomId, @NonNull Instant period, @NonNull Instant periodFact);

  Set<AccountingPointServiceStateDto> currentActive(@NonNull UUID keyRoomId, @NonNull Instant period);

  CompletableFuture<Set<AccountingPointServiceStateDto>> currentActiveByEntityServiceIdAsync(
      Set<UUID> allEntityServiceId);

  AccountingPointServiceStateDto changeState(AccountingPointServiceStateDto request);

  Collection<AccountingPointServiceStateDto> getHistory(UUID keyRoomId, Instant from, Instant to);

  Collection<AccountingPointServiceStateDto> getHistory(UUID keyRoomId, Instant from);

  Collection<AccountingPointServiceStateDto> getHistory(UUID keyRoomId);
}
