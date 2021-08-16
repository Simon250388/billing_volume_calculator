package org.billing.accountingpoints.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Value;

public interface AccountingPointServiceProviderDto {
  @NotNull
  @Value("#{target.id}")
  long getId();

  @NotNull
  @Value("#{target.period}")
  LocalDateTime getPeriod();

  @Null
  @Value("#{target.period_fact}")
  LocalDateTime getPeriodFact();

  @NotNull
  @Value("#{target.accounting_point_key_room_service_id}")
  Long getAccountingPointKeyRoomServiceEntityId();

  @Null
  @Value("#{target.service_part_id}")
  Long getServicePartId();

  @NotNull
  @Value("#{target.provider_id}")
  long getProviderId();

  boolean equals(Object o);

  int hashCode();
}
