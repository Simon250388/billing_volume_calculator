package org.billing.calculation.resolution354.rules;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.BillingConst;
import org.billing.calculation.dto.AvgRateZoneVolume;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.CalculationVolume;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByAvgRule")
@Slf4j
public class VolumeByAvgCalculationRule implements CalculationRule {
  @Override
  public CalculationResultDto volume(
      @NonNull final ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {

    CalculationVolume[] volumes = calculateVolume(stabilityPeriod);

    return CalculationResultDto.builder()
        .calculationMethod(getCalculationMethod())
        .volumes(volumes)
        .stabilityPeriod(stabilityPeriod)
        .build();
  }

  @Override
  public boolean canCalculateVolume(ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {
    return stabilityPeriod.isServiceActive()
        && stabilityPeriod.isMeterActive()
        && !stabilityPeriod.isMeterValuesProvideForAllPointsOfServices()
        && stabilityPeriod.isAvgVolumeForAllPointsOfServices();
  }

  public CalculationMethod getCalculationMethod() {
    return CalculationMethod.BY_AVG_VOLUME;
  }

  private CalculationVolume[] calculateVolume(
      final ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {

    CalculationVolume[] result =
        new CalculationVolume[stabilityPeriod.getAvgScaleVolumes().length * 3];

    int resultIndex = 0;

    for (int scaleIndex = 0;
        scaleIndex < stabilityPeriod.getAvgScaleVolumes().length;
        scaleIndex++) {
      final AvgRateZoneVolume[] rateZoneVolume =
          stabilityPeriod.getAvgScaleVolumes()[scaleIndex].getRateZoneVolume();

      final UUID scaleId = stabilityPeriod.getAvgScaleVolumes()[scaleIndex].getScaleId();

      for (AvgRateZoneVolume avgRateZoneVolume : rateZoneVolume) {

        BigDecimal volume =
            avgRateZoneVolume
                .getAvgVolume()
                .multiply(BigDecimal.valueOf(stabilityPeriod.periodPercent()))
                .setScale(BillingConst.getVolumeScale());

        result[resultIndex] =
            CalculationVolume.builder()
                .skaleId(scaleId)
                .rateZoneId(avgRateZoneVolume.getRateZoneId())
                .volume(volume)
                .volumeFact(BigDecimal.ZERO)
                .build();
        resultIndex++;
      }
    }

    return Arrays.copyOf(result, resultIndex);
  }
}
