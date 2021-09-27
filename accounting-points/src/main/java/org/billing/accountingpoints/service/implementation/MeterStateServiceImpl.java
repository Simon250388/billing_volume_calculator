package org.billing.accountingpoints.service.implementation;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDtoValue;
import org.billing.accountingpoints.repository.AccountingPointMeterStateRepository;
import org.billing.accountingpoints.service.MeterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterStateServiceImpl implements MeterStateService {

    @Autowired
  public MeterStateServiceImpl(AccountingPointMeterStateRepository meterStateDao) {
    }

  @Override
  public CompletableFuture<Set<AccountingPointMeterStateDtoValue>>
      currentStateByEntityServiceIdAsync(
          Set<Long> accountingPointKeyRoomServiceEntities, Instant period) {
    return CompletableFuture.completedFuture(Collections.emptySet());
  }
}