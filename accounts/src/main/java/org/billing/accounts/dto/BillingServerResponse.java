package org.billing.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BillingServerResponse.BillingServerResponseBuilder.class)
public class BillingServerResponse {
  @JsonProperty("queryId")
  UUID queryId;
}
