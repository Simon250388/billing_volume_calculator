package org.billing.accountingpoints.dto;

import java.time.Instant;
import org.billing.accountingpoints.model.MeterState;
import org.springframework.beans.factory.annotation.Value;

/**
 * Dto contract.
 *
 * @see org.billing.accountingpoints.model.AccountingPointMeterState
 * @see org.billing.accountingpoints.repository.AccountingPointMeterStateRepository
 *
 * @author Simanov Aleksey
 */

public interface AccountingPointMeterStateDto {
  @Value("#{target.id}")
  Long getId();

  @Value("#{target.period}")
  Instant getPeriod();

  @Value("#{target.period_fact}")
  Instant getPeriodFact();

  @Value("#{target.accountPointId}")
  Long getAccountPointId();

  @Value("#{target.meter_id}")
  Long getMeterId();

  @Value("#{target.meter_state_id}")
  MeterState getMeterState();

  boolean equals(Object o);

  int hashCode();
}
