package org.billing.accountingpoints.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountingPointStatePresentDto {

  @JsonProperty("serviceId")
  @Null
  Long serviceId;

  @JsonProperty("accountingPoint")
  @NotNull
  AccountingPointPresentDto accountingPoint;

  @JsonProperty("serviceProviders")
  @NotNull
  @Builder.Default
  List<ServiceProviderPresentDto> serviceProviders = new ArrayList<>();

  @JsonProperty("meter")
  @Null
  MeterPresentDto meter;

  @JsonProperty("serviceImprovementTypes")
  @Builder.Default
  List<ServiceImprovementTypePresentDto> serviceImprovementTypes = new ArrayList<>();
}
