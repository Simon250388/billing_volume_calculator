package org.billing.accountingpoints.dto;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;

public interface AccountingPointServiceStateDto {
  @Value("#{target.id}")
  Long getId();

  @Value("#{target.period}")
  Instant getPeriod();

  @Value("#{target.period_fact}")
  Instant getPeriodFact();

  @Value("#{target.accountPointId}")
  Long getAccountPointId();

  @Value("#{target.serviceId}")
  Long getServiceId();

  boolean equals(Object ob);

  int hashCode();
}
