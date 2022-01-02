package org.billing.calculation.resolution354.rules;

import java.math.BigDecimal;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.BillingConst;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.CalculationVolume;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByNormRule")
@Slf4j
public class VolumeByNormCalculationRule implements CalculationRule {

  @Override
  public CalculationResultDto volume(
      @NonNull final ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {

    double coefficientNormValue = stabilityPeriod.getCoefficientNormValue();

    double normIndicator = stabilityPeriod.normIndicator();

    double periodPercent = stabilityPeriod.periodPercent();

    double normValue = stabilityPeriod.getNormValue();

    BigDecimal volume =
        BigDecimal.valueOf((normValue * coefficientNormValue * normIndicator * periodPercent))
            .setScale(BillingConst.getVolumeScale());

    return CalculationResultDto.builder()
        .calculationMethod(getCalculationMethod())
        .volumes(
            new CalculationVolume[] {
              CalculationVolume.builder().volume(volume).volumeFact(BigDecimal.ZERO).build()
            })
        .stabilityPeriod(stabilityPeriod)
        .build();
  }

  @Override
  public boolean canCalculateVolume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {
    return stabilityPeriod.isServiceActive() && !stabilityPeriod.isMeterActive();
  }

  public CalculationMethod getCalculationMethod() {
    return CalculationMethod.BY_NORM;
  }
}
