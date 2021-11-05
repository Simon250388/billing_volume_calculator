package org.billing.calculation.resolution;

import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.dto.CalculationResult;

public interface CalculationRule {
  CalculationResult volume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod);

  boolean canCalculateVolume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod);
}
