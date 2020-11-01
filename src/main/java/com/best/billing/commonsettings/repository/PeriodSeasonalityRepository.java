package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.PeriodSeasonality;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface PeriodSeasonalityRepository extends CrudRepository<PeriodSeasonality, Long> {

    CompletableFuture<Iterable<PeriodSeasonality>> findAllLastByPeriodAsync(Date calculationPeriod);
}
