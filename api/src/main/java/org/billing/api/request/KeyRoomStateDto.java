package org.billing.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = KeyRoomStateDto.KeyRoomStateDtoBuilder.class)
public class KeyRoomStateDto {

  int prescribed;
  int owner;
  int resident;
  BigDecimal debt;
  LocalDate lastPayment;
  BigDecimal commonSquareValue;
  Collection<AccountingPointStateDto> accountingPoints;

  @JsonPOJOBuilder
  public static class KeyRoomStateDtoBuilder {}
}
