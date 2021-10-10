package org.billing.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountResponse.AccountResponseBuilder.class)
public class AccountResponse {
  @JsonProperty("keyRoomId")
  Long keyRoomId;

  @JsonProperty("accountKey")
  UUID accountId;
}
