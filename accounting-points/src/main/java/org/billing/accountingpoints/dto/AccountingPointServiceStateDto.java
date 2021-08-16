package org.billing.accountingpoints.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Value;

public interface AccountingPointServiceStateDto {

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
  @Value("#{target.active}")
  Boolean getServicePartId();

  boolean equals(Object o);

  int hashCode();
}
