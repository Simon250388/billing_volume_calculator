package org.billing.calculation.resolution354.rules;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.BillingConst;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.CalculationVolume;
import org.billing.calculation.dto.RateZoneMeterValue;
import org.billing.calculation.dto.ScaleMeterValue;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByMeterValueRule")
@Slf4j
public class VolumeByMeterValueCalculationRule implements CalculationRule {

  public CalculationResultDto volume(
      @NonNull final ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {

    CalculationVolume[] volumes = calculationVolumes(stabilityPeriod);

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
        && stabilityPeriod.isMeterValuesProvideForAllPointsOfServices();
  }

  private CalculationMethod getCalculationMethod() {
    return CalculationMethod.BY_METER;
  }

  private CalculationVolume[] calculationVolumes(
      final ServiceOfAccountingPointStabilityPeriod stabilityPeriod) {

    final ScaleMeterValue[] scaleMeterValuesStartOrPeriod =
        Arrays.copyOf(
            stabilityPeriod.getScaleMeterValuesStartOrPeriod(),
            stabilityPeriod.getScaleMeterValuesStartOrPeriod().length);

    final ScaleMeterValue[] scaleMeterValuesEndOrPeriod =
        Arrays.copyOf(
            stabilityPeriod.getScaleMeterValuesEndOrPeriod(),
            stabilityPeriod.getScaleMeterValuesEndOrPeriod().length);

    Arrays.sort(scaleMeterValuesStartOrPeriod, Comparator.comparing(ScaleMeterValue::getScaleId));

    Arrays.sort(scaleMeterValuesEndOrPeriod, Comparator.comparing(ScaleMeterValue::getScaleId));

    CalculationVolume[] volumes = new CalculationVolume[15];

    int resultIndex = 0;

    for (int scaleIndex = 0; scaleIndex < scaleMeterValuesStartOrPeriod.length; scaleIndex++) {
      final RateZoneMeterValue[] rateZoneMeterStartOrPeriod =
          Arrays.copyOf(
              scaleMeterValuesStartOrPeriod[scaleIndex].getRateZoneMeterValues(),
              scaleMeterValuesStartOrPeriod[scaleIndex].getRateZoneMeterValues().length);

      final RateZoneMeterValue[] rateZoneMeterValuesEndOrPeriod =
          Arrays.copyOf(
              scaleMeterValuesEndOrPeriod[scaleIndex].getRateZoneMeterValues(),
              scaleMeterValuesEndOrPeriod[scaleIndex].getRateZoneMeterValues().length);

      Arrays.sort(
          rateZoneMeterStartOrPeriod, Comparator.comparing(RateZoneMeterValue::getRateZone));

      Arrays.sort(
          rateZoneMeterValuesEndOrPeriod, Comparator.comparing(RateZoneMeterValue::getRateZone));

      for (int rateZoneIndex = 0;
          rateZoneIndex < rateZoneMeterStartOrPeriod.length;
          rateZoneIndex++) {

        long volume =
            rateZoneMeterValuesEndOrPeriod[rateZoneIndex].getValue()
                - rateZoneMeterStartOrPeriod[rateZoneIndex].getValue();

        volumes[resultIndex] =
            CalculationVolume.builder()
                .rateZoneId(rateZoneMeterStartOrPeriod[rateZoneIndex].getRateZone())
                .skaleId(scaleMeterValuesEndOrPeriod[scaleIndex].getScaleId())
                .volume(
                    BigDecimal.valueOf(
                        (long) (volume * Math.pow(10, BillingConst.getVolumeScale())),
                        BillingConst.getVolumeScale()))
                .volumeFact(BigDecimal.ZERO)
                .build();

        resultIndex++;
      }
    }
    return Arrays.copyOf(volumes, resultIndex);
  }
}
