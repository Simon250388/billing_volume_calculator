package org.billing.calculation.repository;

import java.time.LocalDate;
import lombok.NonNull;
import org.billing.calculation.dto.RateValueDto;
import org.billing.calculation.model.RateValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RateValueRepository extends CrudRepository<RateValue, Long> {
  @Query(
      "FROM RateValue "
          + "WHERE (rateGroup,service,period) IN ( "
          + "SELECT rateGroup,service,MAX(period) "
          + "FROM RateValue "
          + "WHERE period < :period "
          + "GROUP BY rateGroup,service)")
  Iterable<RateValueDto> findAllLastByPeriod(@NonNull @Param("period") LocalDate period);
}
