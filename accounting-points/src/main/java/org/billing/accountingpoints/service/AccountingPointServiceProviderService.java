package org.billing.accountingpoints.service;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDtoValue;

public interface AccountingPointServiceProviderService {
  CompletableFuture<Set<AccountingPointServiceProviderDtoValue>> currentProvidersAsync(
      Set<Long> accountingPointId, Instant period);
}
