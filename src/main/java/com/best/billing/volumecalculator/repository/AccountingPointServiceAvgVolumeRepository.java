package com.best.billing.volumecalculator.repository;

import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface AccountingPointServiceAvgVolumeRepository extends CrudRepository<AccountingPointServiceAvgVolume, Long> {
    CompletableFuture<Iterable<AccountingPointServiceAvgVolume>> findAllLastByPeriodAsync(@NonNull LocalDate calculationPeriod);
    Iterable<AccountingPointServiceAvgVolume> findAllLastByPeriod(@NonNull LocalDate calculationPeriod);
}
