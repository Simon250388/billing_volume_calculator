package org.billing.calculation.repository;

import org.billing.calculation.dto.PeriodSeasonalityDTO;
import org.billing.calculation.model.PeriodSeasonality;
import org.springframework.data.repository.CrudRepository;

import java.util.concurrent.CompletableFuture;

public interface PeriodSeasonalityRepository extends CrudRepository<PeriodSeasonality, Long> {
    CompletableFuture<Iterable<PeriodSeasonalityDTO>> findAllByYear(int year);
}
