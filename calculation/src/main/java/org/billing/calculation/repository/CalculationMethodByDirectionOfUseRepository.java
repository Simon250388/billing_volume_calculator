package org.billing.calculation.repository;

import lombok.NonNull;
import org.billing.calculation.dto.CalculationMethodByDirectionOfUseDTO;
import org.billing.calculation.model.CalculationMethodByDirectionOfUse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CalculationMethodByDirectionOfUseRepository extends CrudRepository<CalculationMethodByDirectionOfUse, Long> {

    @Query("FROM CalculationMethodByDirectionOfUse " +
            "WHERE (service,directionOfUse,period) IN ( " +
            "SELECT service, directionOfUse, MAX(period) " +
            "FROM CalculationMethodByDirectionOfUse " +
            "WHERE period < :period " +
            "GROUP BY service, directionOfUse)")
    Iterable<CalculationMethodByDirectionOfUseDTO> findAllLastByPeriod(@NonNull @Param("period") LocalDate period);
}
