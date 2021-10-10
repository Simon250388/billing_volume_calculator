package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ServiceRequest.ServiceRequestBuilder.class)
public class ServiceRequest {
  @NotNull
  @Positive
  @JsonProperty("keyRoomId")
  Long keyRoomId;

  @NotNull
  @Positive
  @JsonProperty("accountingPointId")
  Long accountingPointId;

  @NotNull
  @Positive
  @JsonProperty("serviceId")
  Long serviceId;
}
