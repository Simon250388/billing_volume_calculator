package org.billing.calculation.repository;

import java.time.LocalDate;
import lombok.NonNull;
import org.billing.calculation.dto.KeyNormValueDto;
import org.billing.calculation.model.KeyNormValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface KeyNormValueRepository extends CrudRepository<KeyNormValue, Long> {
  @Query(
      "FROM KeyNormValue "
          + "WHERE (keyNorm,period) IN ( "
          + "SELECT keyNorm,MAX(period) "
          + "FROM KeyNormValue "
          + "WHERE period < :period "
          + "GROUP BY keyNorm)")
  Iterable<KeyNormValueDto> findAllLastByPeriod(@NonNull @Param("period") LocalDate period);
}
