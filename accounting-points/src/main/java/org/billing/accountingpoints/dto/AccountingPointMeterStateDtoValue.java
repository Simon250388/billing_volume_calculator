package org.billing.accountingpoints.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;
import org.billing.accountingpoints.model.MeterState;

@Value
@Builder
public class AccountingPointMeterStateDtoValue implements AccountingPointMeterStateDto {
  Long id;
  Instant period;
  Instant periodFact;
  Long accountPointId;
  Long meterId;
  MeterState meterState;
}
