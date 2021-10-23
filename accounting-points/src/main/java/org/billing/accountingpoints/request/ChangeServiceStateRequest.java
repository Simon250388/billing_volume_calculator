package org.billing.accountingpoints.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = ChangeServiceStateRequest.ChangeServiceStateRequestBuilder.class)
public class ChangeServiceStateRequest {
  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID keyRoomId;

  @JsonProperty("accountingPoint")
  @NotNull
  AccountingPointRequest accountingPoint;

  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID serviceId;

  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID meterId;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXXXX")
  OffsetDateTime period;

  OffsetDateTime periodFact;

  @JsonPOJOBuilder
  public static class ChangeServiceStateRequestBuilder {}
}
