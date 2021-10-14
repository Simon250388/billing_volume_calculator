package org.billing.metervalues.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class MeterDifferentiationTypeDto {
  UUID id;
  Instant period;
  Instant periodFact;
  UUID meterId;
  UUID differentiationTypeId;
//  @NotNull
//  @Value("#{target.id}")
//  UUID getId();
//
//  @NotNull
//  @Value("#{target.period}")
//  LocalDateTime getPeriod();
//
//  @Null
//  @Value("#{target.period_fact}")
//  LocalDateTime getPeriodFact();
//
//  @NotNull
//  @Value("#{target.accounting_point_key_room_service_id}")
//  UUID getAccountingPointKeyRoomServiceEntityId();
//
//  @NotNull
//  @Value("#{target.meter_id}")
//  UUID getMeterId();
//
//  @NotNull
//  @Value("#{target.differentiation_type_id}")
//  UUID getDifferentiationTypeId();
//
//  boolean equals(Object o);
//
//  int hashCode();
}
