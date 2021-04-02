package com.best.billing.calculationsettings.repository;

import com.best.billing.calculationsettings.model.RateValue;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface RateValueRepository extends CrudRepository<RateValue, Long> {
    @Query("FROM RateValue " +
            "WHERE (rateGroup,service,period) IN ( " +
            "SELECT rateGroup,service,MAX(period) " +
            "FROM RateValue " +
            "WHERE period < :period " +
            "GROUP BY rateGroup,service)")
    Iterable<RateValue> findAllLastByPeriod(@NonNull LocalDate period);
}
