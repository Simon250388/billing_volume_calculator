package org.billing.calculation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.billing.calculation.dto.AccountingPointProperties;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.NormIndicator;
import org.billing.calculation.dto.RoomProperties;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.mapper.ModelMapper;
import org.billing.calculation.model.CalculationResult;
import org.billing.calculation.repository.CalculationRepository;
import org.billing.calculation.resolution.CalculationRule;
import org.billing.calculation.resolution354.rules.VolumeByAvgCalculationRule;
import org.billing.calculation.resolution354.rules.VolumeByAvgNormCalculationRule;
import org.billing.calculation.resolution354.rules.VolumeByMeterValueCalculationRule;
import org.billing.calculation.resolution354.rules.VolumeByNormCalculationRule;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
      CalculationService.class,
      CalculationServiceTest.TestConfig.class,
      VolumeByAvgCalculationRule.class,
      VolumeByAvgNormCalculationRule.class,
      VolumeByMeterValueCalculationRule.class,
      VolumeByNormCalculationRule.class
    })
class CalculationServiceTest {

  @Autowired private CalculationService calculationService;
  @Autowired private List<CalculationRule> calculationRuleList;

  @TestConfiguration
  public static class TestConfig {
    @Bean
    CalculationRepository calculationRepository() {
      return Mockito.mock(CalculationRepository.class);
    }

    @Bean
    ModelMapper<Iterable<CalculationResult>, CalculationResultDto> mapper() {
      return new ModelMapper<>() {
        @Override
        public Iterable<CalculationResult> toModel(CalculationResultDto dto) {
          return Mockito.anyIterable();
        }

        @Override
        public Collection<Iterable<CalculationResult>> toModel(
                Collection<CalculationResultDto> dtos) {
          return Mockito.anyCollection();
        }

        @Override
        public CalculationResultDto toDto(Iterable<CalculationResult> model) {
          return Mockito.any(CalculationResultDto.class);
        }

        @Override
        public Collection<CalculationResultDto> toDto(
                Collection<Iterable<CalculationResult>> models) {
          return Mockito.anyCollection();
        }
      };
    }

    @Bean
    List<CalculationRule> calculationRuleList(
        @Autowired List<CalculationRule> calculationRuleList) {
      return calculationRuleList;
    }
  }

  @ParameterizedTest
  @MethodSource("provideCalculate")
  @Disabled
  void calculate(
      ServiceOfAccountingPointStabilityPeriod[] stabilityPeriods,
      Collection<CalculationResult> expected) {

    final Collection<CalculationResult> results = calculationService.calculate(stabilityPeriods);

    Assertions.assertAll(
        () -> Assertions.assertEquals(expected.size(), results.size()),
        () -> MatcherAssert.assertThat(results, Matchers.containsInAnyOrder(expected)));
  }

  private static List<Arguments> provideCalculate() {
    return List.of(
        Arguments.of(
            new ServiceOfAccountingPointStabilityPeriod[] {
              ServiceOfAccountingPointStabilityPeriod.builder()
                  .roomProperties(RoomProperties.builder().build())
                  .accountingPoints(AccountingPointProperties.builder().serviceActive(true).build())
                  .normIndicator(NormIndicator.PEOPLE)
                  .calculationPeriodStart(LocalDate.of(2021, 1, 1))
                  .calculationPeriodEnd(LocalDate.of(2021, 2, 1))
                  .periodStart(LocalDateTime.of(2021, 1, 1, 0, 0))
                  .periodEnd(LocalDateTime.of(2021, 2, 1, 0, 0))
                  .build()
            },
            Collections.<CalculationResult>emptyList()));
  }
}
