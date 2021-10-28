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
@JsonDeserialize(builder = ServiceImprovementTypeRequest.ServiceImprovementTypeRequestBuilder.class)
public class ServiceImprovementTypeRequest {
  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID serviceId;

  @JsonDeserialize(using = UUIDDeserializer.class)
  @NotNull
  UUID improvementTypeId;

  @JsonPOJOBuilder
  public static class ServiceImprovementTypeRequestBuilder {}
}
