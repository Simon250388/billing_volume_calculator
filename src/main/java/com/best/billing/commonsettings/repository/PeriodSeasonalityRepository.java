package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.PeriodSeasonality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface PeriodSeasonalityRepository extends CrudRepository<PeriodSeasonality, Long> {
    CompletableFuture<Iterable<PeriodSeasonality>> findAllByYear(int year);
}
