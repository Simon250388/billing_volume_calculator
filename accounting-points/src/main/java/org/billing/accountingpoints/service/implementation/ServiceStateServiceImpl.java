package org.billing.accountingpoints.service.implementation;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDtoValue;
import org.billing.accountingpoints.repository.AccountingPointServiceStateRepository;
import org.billing.accountingpoints.service.ServiceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceStateServiceImpl implements ServiceStateService {

  private final AccountingPointServiceStateRepository accountingPointServiceStateDao;

  @Autowired
  public ServiceStateServiceImpl(
      AccountingPointServiceStateRepository accountingPointServiceStateDao) {
    this.accountingPointServiceStateDao = accountingPointServiceStateDao;
  }

  @Override
  public Set<AccountingPointServiceStateDtoValue> currentActiveByKeyRoomId(
      Long keyRoomId, Instant period, Instant periodFact) {
    return Collections.emptySet();
  }

  @Override
  public CompletableFuture<Set<AccountingPointServiceStateDtoValue>>
      currentActiveByEntityServiceIdAsync(Set<Long> allEntityServiceId) {
    return null;
  }
}
