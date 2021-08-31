package org.billing.accountingpoints.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceImprovementTypeDto {
  Long id;
  Instant period;
  Instant periodFact;
  Long accountingPointId;
  Long improvementTypeId;
}
