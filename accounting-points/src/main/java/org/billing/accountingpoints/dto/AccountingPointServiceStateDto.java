package org.billing.accountingpoints.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointServiceStateDto.AccountingPointServiceStateDtoBuilder.class)
@AllArgsConstructor
public class AccountingPointServiceStateDto {
  UUID id;
  Instant period;
  Instant periodFact;
  UUID keyRoomID;
  UUID accountPointId;
  UUID serviceId;
  boolean active;

  @JsonPOJOBuilder
  public static class AccountingPointServiceStateDtoBuilder {}
}
