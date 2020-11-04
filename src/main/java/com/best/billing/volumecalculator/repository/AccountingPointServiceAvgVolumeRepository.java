package com.best.billing.volumecalculator.repository;

import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface AccountingPointServiceAvgVolumeRepository extends CrudRepository<AccountingPointServiceAvgVolume, Long> {
    CompletableFuture<Iterable<AccountingPointServiceAvgVolume>> findAllLastByPeriodAsync(Date calculationPeriod);
    Iterable<AccountingPointServiceAvgVolume> findAllLastByPeriod(Date calculationPeriod);
}
