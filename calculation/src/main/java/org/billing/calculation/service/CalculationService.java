package org.billing.calculation.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.dto.CalculationResultDto;
import org.billing.calculation.dto.ServiceOfAccountingPointStabilityPeriod;
import org.billing.calculation.mapper.ModelMapper;
import org.billing.calculation.model.CalculationResult;
import org.billing.calculation.repository.CalculationRepository;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CalculationService {
  private final List<CalculationRule> calculationRules;
  private final CalculationRepository calculationRepository;
  private final ModelMapper<Iterable<CalculationResult>, CalculationResultDto> mapper;

  public Collection<CalculationResult> calculate(
     @NonNull final ServiceOfAccountingPointStabilityPeriod[] stabilityPeriods) {

    List<CalculationResult> calculationResults = new LinkedList<>();

    for (ServiceOfAccountingPointStabilityPeriod stabilityPeriod : stabilityPeriods) {
      for (CalculationRule calculationRule : calculationRules) {
        if (calculationRule.canCalculateVolume(stabilityPeriod)) {
          final Iterable<CalculationResult> results =
              mapper.toModel(calculationRule.volume(stabilityPeriod));
          results.forEach(calculationResults::add);
        }
      }
    }
    return calculationResults;
  }

  @Async
  public CompletableFuture<Collection<CalculationResult>> calculateAndSave(
      ServiceOfAccountingPointStabilityPeriod[] stabilityPeriods) {
    final Collection<CalculationResult> calculationResult = calculate(stabilityPeriods);
    calculationRepository.saveAll(calculationResult);
    return CompletableFuture.completedFuture(calculationResult);
  }
}
