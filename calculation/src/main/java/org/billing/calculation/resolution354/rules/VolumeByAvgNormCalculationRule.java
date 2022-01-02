package org.billing.calculation.resolution354.rules;

import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByAvgNormRule")
@Slf4j
public class VolumeByAvgNormCalculationRule extends VolumeByNormCalculationRule
    implements CalculationRule {

  @Override
  public CalculationMethod getCalculationMethod() {
    return CalculationMethod.BY_AVG_NORM;
  }

  @Override
  public boolean canCalculateVolume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {
    return stabilityPeriod.isServiceActive()
        && stabilityPeriod.isMeterActive()
        && !stabilityPeriod.isMeterValuesProvideForAllPointsOfServices()
        && !stabilityPeriod.isAvgVolumeForAllPointsOfServices();
  }
}
