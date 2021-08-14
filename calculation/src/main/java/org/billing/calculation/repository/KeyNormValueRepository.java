package org.billing.calculation.repository;

import lombok.NonNull;
import org.billing.calculation.dto.KeyNormValueDTO;
import org.billing.calculation.model.KeyNormValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface KeyNormValueRepository extends CrudRepository<KeyNormValue, Long> {
    @Query("FROM KeyNormValue " +
            "WHERE (keyNorm,period) IN ( " +
            "SELECT keyNorm,MAX(period) " +
            "FROM KeyNormValue " +
            "WHERE period < :period " +
            "GROUP BY keyNorm)")
    Iterable<KeyNormValueDTO> findAllLastByPeriod(@NonNull @Param("period") LocalDate period);
}
