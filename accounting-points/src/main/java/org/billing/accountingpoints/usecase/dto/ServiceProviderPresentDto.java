package org.billing.accountingpoints.usecase.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceProviderPresentDto {
  @NotNull UUID serviceId;
  @NotNull UUID providerId;
}
