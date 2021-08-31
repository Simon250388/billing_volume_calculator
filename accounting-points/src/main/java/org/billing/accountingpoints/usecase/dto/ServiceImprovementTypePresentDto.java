package org.billing.accountingpoints.usecase.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceImprovementTypePresentDto {
  Long serviceId;
  Long improvementTypeId;
}
