package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountRequest.AccountRequestBuilder.class)
public class AccountRequest {
  @NotNull
  @Positive
  @JsonProperty("keyRoomId")
  Long keyRoomId;

  @NotNull
  @Positive
  @JsonProperty("customerId")
  Long customerId;
}
