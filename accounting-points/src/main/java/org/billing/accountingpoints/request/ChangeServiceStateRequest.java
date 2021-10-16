package org.billing.accountingpoints.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = ChangeServiceStateRequest.ChangeServiceStateRequestBuilder.class)
public class ChangeServiceStateRequest {
  UUID keyRoomId;
  UUID accountingPointId;
  UUID serviceId;
  Instant period;
  Instant periodFact;

  @JsonPOJOBuilder
  public static class ChangeServiceStateRequestBuilder {}
}
