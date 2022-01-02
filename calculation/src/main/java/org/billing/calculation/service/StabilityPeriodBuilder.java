package org.billing.calculation.service;

import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.repository.StabilityPeriodDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StabilityPeriodBuilder {
  private final StabilityPeriodDaoRepository repository;

  public ServiceOfAccountingPointStabilityPeriod[] build(@NonNull final UUID keyRoomId) {
    return new ServiceOfAccountingPointStabilityPeriod[0];
  }
}
