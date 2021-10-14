package org.billing.accountingpoints.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import org.billing.accountingpoints.model.MeterState;

/**
 * Dto contract.
 *
 * @see org.billing.accountingpoints.model.AccountingPointMeterState
 * @see org.billing.accountingpoints.repository.AccountingPointMeterStateRepository
 *
 * @author Simanov Aleksey
 */

@Value
@Builder
public class AccountingPointMeterStateDto {
  UUID id;
  Instant period;
  Instant periodFact;
  UUID accountPointId;
  UUID meterId;
  MeterState meterState;
}
