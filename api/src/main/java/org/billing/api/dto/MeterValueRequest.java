package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = MeterValueRequest.MeterValueRequestBuilder.class)
public class MeterValueRequest {
  @NotNull
  @Positive
  @JsonProperty("meterId")
  Instant period;

  @NotNull
  @Positive
  @JsonProperty("meterId")
  Long meterId;

  @NotNull
  @Positive
  @JsonProperty("value")
  BigDecimal value;
}
