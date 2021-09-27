package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountResponse.AccountResponseBuilder.class)
public class AccountResponse {
    @JsonProperty("keyRoomId")
    Long buildingId;

    @JsonProperty("accountName")
    String accountName;

    @JsonProperty("accountNumber")
    String accountNumber;
}
