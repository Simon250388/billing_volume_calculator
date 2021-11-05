package org.billing.calculation.resolution354.rules;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.billing.calculation.BillingConst;
import org.billing.calculation.dto.AccountingPointProperties;
import org.billing.calculation.dto.CalculationResult;
import org.billing.calculation.dto.CalculationVolume;
import org.billing.calculation.dto.NormIndicator;
import org.billing.calculation.dto.RoomProperties;
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
@ContextConfiguration(classes = {VolumeByNormCalculationRule.class})
class VolumeByNormCalculationRuleTest {
  @Autowired private VolumeByNormCalculationRule calculationRule;

  @Test
  void volume() {

    final CalculationResult result = calculationRule.volume(mockAccountingPointStabilityPeriod());

    final CalculationResult expected =
        CalculationResult.builder()
            .calculationMethod(CalculationMethod.BY_NORM)
            .stabilityPeriod(mockAccountingPointStabilityPeriod())
            .volumes(
                new CalculationVolume[] {
                  CalculationVolume.builder()
                      .skaleId(null)
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
    Assertions.assertTrue(calculationRule.canCalculateVolume(mockAccountingPointStabilityPeriod()));
  }

  private ServiceOfAccountingPointStabilityPeriod mockAccountingPointStabilityPeriod() {
    return ServiceOfAccountingPointStabilityPeriod.builder()
        .roomProperties(RoomProperties.builder().squareValue(30).build())
        .normIndicator(NormIndicator.SQUARE)
        .accountingPoints(
            AccountingPointProperties.builder()
                .serice(UUID.fromString("e33a9703-e799-4afc-829e-d0ba4f5f1d02"))
                .serviceActive(true)
                .meterActive(false)
                .build())
        .periodStart(Instant.parse("2021-11-01T00:00:00Z"))
        .periodEnd(Instant.parse("2021-12-01T00:00:00Z"))
        .calculationPeriodStart(Instant.parse("2021-11-01T00:00:00Z"))
        .calculationPeriodEnd(Instant.parse("2021-12-01T00:00:00Z"))
        .avgVolumeForAllPointsOfServices(false)
        .meterValuesProvideForAllPointsOfServices(false)
        .normValue(1)
        .coefficientNormValue(1)
        .build();
  }
}
