package org.billing.accountingpoints.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountingPointServiceStateDtoValue implements AccountingPointServiceStateDto {
  Long id;
  Instant period;
  Instant periodFact;
  Long accountPointId;
  Long serviceId;
}
