package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.KeyNormValue;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface KeyNormValueRepository extends CrudRepository<KeyNormValue, Long> {
    @Query("FROM KeyNormValue " +
            "WHERE (keyNorm,period) IN ( " +
            "SELECT keyNorm,MAX(period) " +
            "FROM KeyNormValue " +
            "WHERE period < :period " +
            "GROUP BY keyNorm)")
    Iterable<KeyNormValue> findAllLastByPeriod(@NonNull LocalDate period);
}
