package org.billing.calculation.resolution354.rules;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.billing.calculation.BillingConst;
import org.billing.calculation.dto.AccountingPointProperties;
import org.billing.calculation.dto.AvgRateZoneVolume;
import org.billing.calculation.dto.AvgScaleVolume;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.CalculationVolume;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.model.CalculationMethod;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VolumeByAvgCalculationRule.class})
class VolumeByAvgCalculationRuleTest {

  @Autowired private VolumeByAvgCalculationRule volumeByAvgCalculationRule;

  @Test
  void volume() {

    final CalculationResultDto result =
        volumeByAvgCalculationRule.volume(mockAccountingPointStabilityPeriod());

    final CalculationResultDto expected =
        CalculationResultDto.builder()
            .calculationMethod(CalculationMethod.BY_AVG_VOLUME)
            .stabilityPeriod(mockAccountingPointStabilityPeriod())
            .volumes(
                new CalculationVolume[] {
                  CalculationVolume.builder()
                      .skaleId(UUID.fromString("1d7582c8-f83d-43c2-9533-150347cc47df"))
                      .rateZoneId(null)
                      .volume(
                          BigDecimal.valueOf(
                              (long) (30 * Math.pow(10, BillingConst.getVolumeScale())),
                              BillingConst.getVolumeScale()))
                      .volumeFact(BigDecimal.ZERO)
                      .build()
                })
            .build();

    MatcherAssert.assertThat(result, Matchers.samePropertyValuesAs(expected));
  }

  @Test
  void canCalculateVolume() {
    Assertions.assertTrue(
        volumeByAvgCalculationRule.canCalculateVolume(mockAccountingPointStabilityPeriod()));
  }

  private ServiceOfAccountingPointStabilityPeriod mockAccountingPointStabilityPeriod() {
    return ServiceOfAccountingPointStabilityPeriod.builder()
        .accountingPoints(
            AccountingPointProperties.builder()
                .service(UUID.fromString("e33a9703-e799-4afc-829e-d0ba4f5f1d02"))
                .serviceActive(true)
                .meterActive(true)
                .build())
        .periodStart(LocalDateTime.of(2021, 11, 1, 0, 0))
        .periodEnd(LocalDateTime.of(2021, 12, 1, 0, 0))
        .calculationPeriodStart(LocalDate.of(2021, 11, 1))
        .calculationPeriodEnd(LocalDate.of(2021, 12, 1))
        .avgVolumeForAllPointsOfServices(true)
        .meterValuesProvideForAllPointsOfServices(false)
        .avgScaleVolumes(
            new AvgScaleVolume[] {
              AvgScaleVolume.builder()
                  .scaleId(UUID.fromString("1d7582c8-f83d-43c2-9533-150347cc47df"))
                  .rateZoneVolume(
                      new AvgRateZoneVolume[] {
                        AvgRateZoneVolume.builder()
                            .rateZoneId(null)
                            .avgVolume(
                                BigDecimal.valueOf(
                                    (long) (30 * Math.pow(10, BillingConst.getVolumeScale())),
                                    BillingConst.getVolumeScale()))
                            .build()
                      })
                  .build()
            })
        .build();
  }
}
