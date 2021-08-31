package org.billing.accountingpoints.dto;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;

public interface AccountingPointServiceProviderDto {
  @Value("#{target.id}")
  Long getId();

  @Value("#{target.period}")
  Instant getPeriod();

  @Value("#{target.period_fact}")
  Instant getPeriodFact();

  @Value("#{target.accountPointId}")
  Long getAccountPointId();

  @Value("#{target.service_part_id}")
  Long getServiceId();

  @Value("#{target.provider_id}")
  Long getProviderId();

  boolean equals(Object o);

  int hashCode();
}
