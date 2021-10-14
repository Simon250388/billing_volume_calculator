package org.billing.accountingpoints.service.implementation;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.mapper.ModelMapper;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.billing.accountingpoints.repository.AccountingPointServiceStateRepository;
import org.billing.accountingpoints.service.ServiceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceStateServiceImpl implements ServiceStateService {

  private final AccountingPointServiceStateRepository accountingPointServiceStateDao;
  private final ModelMapper<AccountingPointServiceState, AccountingPointServiceStateDto> mapper;

  @Override
  public Set<AccountingPointServiceStateDto> currentActiveByKeyRoomId(
          UUID keyRoomId, Instant period, Instant periodFact) {
    return Collections.emptySet();
  }

  @Override
  public CompletableFuture<Set<AccountingPointServiceStateDto>>
      currentActiveByEntityServiceIdAsync(Set<UUID> allEntityServiceId) {
    return null;
  }
}
