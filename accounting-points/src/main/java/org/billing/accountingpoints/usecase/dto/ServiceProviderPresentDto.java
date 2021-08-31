package org.billing.accountingpoints.usecase.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceProviderPresentDto {
  @NotNull Long serviceId;
  @NotNull Long providerId;
}
