package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountRequest.AccountRequestBuilder.class)
public class AccountRequest {
  @NotNull
  @Positive
  @JsonProperty("buildingId")
  Long buildingId;

  @Null
  @Positive
  @JsonProperty("roomId")
  Long roomId;

  @NotNull
  @Size(min = 10, max = 300)
  String accountName;
}
