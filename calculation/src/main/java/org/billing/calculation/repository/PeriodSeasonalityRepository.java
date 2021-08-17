package org.billing.calculation.repository;

import java.util.concurrent.CompletableFuture;
import org.billing.calculation.dto.PeriodSeasonalityDto;
import org.billing.calculation.model.PeriodSeasonality;
import org.springframework.data.repository.CrudRepository;

public interface PeriodSeasonalityRepository extends CrudRepository<PeriodSeasonality, Long> {
  CompletableFuture<Iterable<PeriodSeasonalityDto>> findAllByYear(int year);
}