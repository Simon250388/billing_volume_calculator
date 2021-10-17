package org.billing.accountingpoints.service.implementation;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDto;
import org.billing.accountingpoints.repository.AccountingPointServiceProviderRepository;
import org.billing.accountingpoints.service.AccountingPointServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointServiceProviderServiceImpl
    implements AccountingPointServiceProviderService {

  private final AccountingPointServiceProviderRepository dao;

  @Override
  public CompletableFuture<Set<AccountingPointServiceProviderDto>> currentProvidersAsync(
      Set<UUID> accountingPointId, Instant period) {
    return CompletableFuture.completedFuture(Collections.emptySet());
  }
}
