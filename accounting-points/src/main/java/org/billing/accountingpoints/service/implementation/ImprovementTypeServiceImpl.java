package org.billing.accountingpoints.service.implementation;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.accountingpoints.dto.ServiceImprovementTypeDto;
import org.billing.accountingpoints.service.ImprovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ImprovementTypeServiceImpl implements ImprovementTypeService {
  @Override
  public CompletableFuture<Set<ServiceImprovementTypeDto>> currentImprovementTypesAsync(
      Set<UUID> accountingPointsId) {
    return CompletableFuture.completedFuture(Collections.emptySet());
  }
}
