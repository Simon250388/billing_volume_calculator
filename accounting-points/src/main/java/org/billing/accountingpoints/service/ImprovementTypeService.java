package org.billing.accountingpoints.service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.ServiceImprovementTypeDto;

public interface ImprovementTypeService {
  CompletableFuture<Set<ServiceImprovementTypeDto>> currentImprovementTypesAsync(
      Set<Long> accountingPointsId);
}
