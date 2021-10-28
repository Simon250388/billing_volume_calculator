package org.billing.accountingpoints.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = MeterRequest.MeterRequestBuilder.class)
public class MeterRequest {
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID id;
  @JsonProperty("factory_number")
  @Builder.Default String factoryNumber = "";

  @JsonPOJOBuilder
  public static class MeterRequestBuilder{}
}
