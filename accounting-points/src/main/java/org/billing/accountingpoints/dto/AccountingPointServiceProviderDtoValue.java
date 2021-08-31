package org.billing.accountingpoints.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountingPointServiceProviderDtoValue implements AccountingPointServiceProviderDto {
  Long id;
  Instant period;
  Instant periodFact;
  Long accountPointId;
  Long serviceId;
  Long providerId;

}
