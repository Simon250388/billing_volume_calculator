package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDto;

public interface AccountingPointServiceProviderService {
  CompletableFuture<Set<AccountingPointServiceProviderDto>> currentProvidersAsync(
          Set<UUID> accountingPointId, Instant period);
}
