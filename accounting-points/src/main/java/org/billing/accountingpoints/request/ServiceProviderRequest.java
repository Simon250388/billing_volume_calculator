package org.billing.accountingpoints.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ServiceProviderRequest.ServiceProviderRequestBuilder.class)
public class ServiceProviderRequest {
  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID serviceId;

  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID providerId;

  @JsonPOJOBuilder
  public static class ServiceProviderRequestBuilder {}
}
