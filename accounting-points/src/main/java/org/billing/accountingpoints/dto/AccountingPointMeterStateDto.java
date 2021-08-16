package org.billing.accountingpoints.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.billing.accountingpoints.model.AccountingPointMeterState;
import org.billing.accountingpoints.model.Meter;
import org.billing.accountingpoints.model.MeterState;
import org.springframework.beans.factory.annotation.Value;

public interface AccountingPointMeterStateDto {
  @NotNull
  @Value("#{target.id}")
  Long getId();

  @NotNull
  @Value("#{target.period}")
  LocalDateTime getPeriod();

  @Null
  @Value("#{target.period_fact}")
  LocalDateTime getPeriodFact();

  @Value("#{target.accounting_point_key_room_service_id}")
  long getAccountingPointKeyRoomServiceEntityId();

  @Value("#{target.meter_id}")
  long getMeterId();

  @NotNull
  @Value("#{target.meter_state_id}")
  MeterState getMeterState();

  default AccountingPointMeterState toEntity() {
    return AccountingPointMeterState.builder()
        .id(getId())
        .period(getPeriod())
        .periodFact(getPeriodFact())
        .accountingPointKeyRoomServiceEntity(
            AccountingPointKeyRoomServiceEntity.builder()
                .id(getAccountingPointKeyRoomServiceEntityId())
                .build())
        .meter(Meter.builder().id(getMeterId()).build())
        .meterState(getMeterState())
        .build();
  }
}
