package com.best.billing.volumecalculator.repository;

import com.best.billing.volumecalculator.model.StabPeriod;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface StabPeriodRepository extends CrudRepository<StabPeriod, Long> {
    @Query("FROM StabPeriod" +
            " WHERE (accountingPointKeyRoomServiceEntity, calculationPeriod, registrationPeriod) IN " +
            "   (SELECT accountingPointKeyRoomServiceEntity, calculationPeriod, MAX(registrationPeriod)" +
            "   FROM StabPeriod" +
            "   WHERE (accountingPointKeyRoomServiceEntity, calculationPeriod) IN " +
            "       (SELECT accountingPointKeyRoomServiceEntity, MAX(calculationPeriod)" +
            "       FROM StabPeriod" +
            "       WHERE calculationPeriod < :currentCalculationPeriod" +
            "       GROUP BY accountingPointKeyRoomServiceEntity)" +
            "   GROUP BY accountingPointKeyRoomServiceEntity, calculationPeriod)")
    Iterable<StabPeriod> findAllLastOnCurrentCalculationPeriod(@NonNull @Param("currentCalculationPeriod") LocalDate currentCalculationPeriod);

    Iterable<StabPeriod> findAllByCalculationPeriod(@NonNull LocalDate calculationPeriod);
}
