package com.best.billing.volumecalculator.repository;

import com.best.billing.volumecalculator.model.StabPeriod;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface StabPeriodRepository extends CrudRepository<StabPeriod, Long> {
    @Query(name = StabPeriod.FIND_ALL_LAST_ON_CURRENT_CALCULATION_PERIOD)
    Iterable<StabPeriod> findAllLastOnCurrentCalculationPeriod(@NonNull @Param("currentCalculationPeriod") Date currentCalculationPeriod);
    Iterable<StabPeriod> findAllByCalculationPeriod(@NonNull Date calculationPeriod);
}
