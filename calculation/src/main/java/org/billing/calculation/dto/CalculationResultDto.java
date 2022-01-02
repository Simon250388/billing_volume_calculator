package org.billing.calculation.dto;

import lombok.Builder;
import lombok.Value;
import org.billing.calculation.model.CalculationMethod;

@Value
@Builder
public class CalculationResultDto {
  ServiceOfAccountingPointStabilityPeriod stabilityPeriod;
  CalculationMethod calculationMethod;
  CalculationVolume[] volumes;
}
