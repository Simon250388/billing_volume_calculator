package com.best.billing.calculationsettings.repository;

import com.best.billing.calculationsettings.model.PeriodSeasonality;
import org.springframework.data.repository.CrudRepository;

import java.util.concurrent.CompletableFuture;

public interface PeriodSeasonalityRepository extends CrudRepository<PeriodSeasonality, Long> {
    CompletableFuture<Iterable<PeriodSeasonality>> findAllByYear(int year);
}
