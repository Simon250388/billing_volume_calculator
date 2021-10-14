package org.billing.accountingpoints.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceImprovementTypeDto {
  UUID id;
  Instant period;
  Instant periodFact;
  UUID accountingPointId;
  UUID improvementTypeId;
}
