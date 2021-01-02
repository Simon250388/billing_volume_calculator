package com.best.billing.volumecalculator.repository;

import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface AccountingPointServiceAvgVolumeRepository extends CrudRepository<AccountingPointServiceAvgVolume, Long> {
    @Query("FROM AccountingPointServiceAvgVolume " +
            "WHERE calculationPeriod = :calculationPeriod")
    Iterable<AccountingPointServiceAvgVolume> findAllByCalculationPeriod(@NonNull LocalDate calculationPeriod);
}
