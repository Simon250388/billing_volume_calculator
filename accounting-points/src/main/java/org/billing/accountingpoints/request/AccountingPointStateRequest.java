package org.billing.accountingpoints.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointStateRequest.AccountingPointStateRequestBuilder.class)
public class AccountingPointStateRequest {
  @NotNull
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID serviceId;

  @NotNull
  @JsonProperty("accountingPoint")
  AccountingPointRequest accountingPoint;

  @JsonProperty("serviceProviders")
  @NotNull @Builder.Default List<ServiceProviderRequest> serviceProviders = Collections.emptyList();

  @JsonProperty("meter")
  MeterRequest meter;

  @Builder.Default
  @JsonProperty("serviceImprovementTypes")
  List<ServiceImprovementTypeRequest> serviceImprovementTypes = Collections.emptyList();

  @JsonPOJOBuilder
  public static class AccountingPointStateRequestBuilder {}
}
