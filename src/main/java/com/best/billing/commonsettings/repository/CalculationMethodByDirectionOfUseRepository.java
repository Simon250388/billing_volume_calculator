package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface CalculationMethodByDirectionOfUseRepository extends CrudRepository<CalculationMethodByDirectionOfUse, Long> {

    @Query("FROM CalculationMethodByDirectionOfUse " +
            "WHERE (service,directionOfUse,period) IN ( " +
            "SELECT service, directionOfUse, MAX(period) " +
            "FROM CalculationMethodByDirectionOfUse " +
            "WHERE period < :period " +
            "GROUP BY service, directionOfUse)")
    Iterable<CalculationMethodByDirectionOfUse> findAllLastByPeriod(@NonNull LocalDate period);
}
