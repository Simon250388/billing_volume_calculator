package org.billing.accountingpoints.repository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.billing.accountingpoints.dto.ServiceImprovementTypeDto;

public interface ServiceImprovementTypeRepository {
  CompletableFuture<ServiceImprovementTypeDto> currentImprovementTypesAsync(Set<Long> servicesId);
}
