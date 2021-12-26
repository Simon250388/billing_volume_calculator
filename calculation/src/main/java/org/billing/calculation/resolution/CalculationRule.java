package org.billing.calculation.resolution;

import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;

public interface CalculationRule {
  CalculationResultDto volume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod);

  boolean canCalculateVolume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod);
}
