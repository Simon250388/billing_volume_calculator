package org.billing.accountingpoints.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountingPointServiceProviderDto {
  UUID id;
  Instant period;
  Instant periodFact;
  UUID accountPointId;
  UUID serviceId;
  UUID providerId;

//  @Value("#{T(java.util.UUID).fromString(target.id.toString())}")
//  UUID getId();
//
//  @Value("#{target.period}")
//  Instant getPeriod();
//
//  @Value("#{target.period_fact}")
//  Instant getPeriodFact();
//
//  @Value("#{T(java.util.UUID).nameUUIDFromBytes(target.accountPointId)}")
//  UUID getAccountPointId();
//
//  @Value("#{T(java.util.UUID).nameUUIDFromBytes(target.service_part_id)}")
//  UUID getServiceId();
//
//  @Value("#{T(java.util.UUID).nameUUIDFromBytes(target.provider_id)}")
//  UUID getProviderId();
//
//  boolean equals(Object o);
//
//  int hashCode();
}
