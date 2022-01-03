package org.billing.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointStateDto.AccountingPointStateDtoBuilder.class)
public class AccountingPointStateDto {

  @JsonPOJOBuilder
  public static class AccountingPointStateDtoBuilder {}
}
