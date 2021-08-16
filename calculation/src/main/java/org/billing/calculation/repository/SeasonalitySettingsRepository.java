package org.billing.calculation.repository;

import java.time.LocalDate;
import lombok.NonNull;
import org.billing.calculation.dto.SeasonalitySettingDto;
import org.billing.calculation.model.SeasonalitySetting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SeasonalitySettingsRepository extends CrudRepository<SeasonalitySetting, Long> {
  @Query(
      "FROM SeasonalitySetting "
          + "WHERE (service,directionOfUse,building,period) in ( "
          + "SELECT service,directionOfUse,building,MAX(period) "
          + "FROM SeasonalitySetting "
          + "WHERE period < :period "
          + "GROUP BY service,directionOfUse,building) ")
  Iterable<SeasonalitySettingDto> findAllLastByPeriod(@NonNull @Param("period") LocalDate period);
}
