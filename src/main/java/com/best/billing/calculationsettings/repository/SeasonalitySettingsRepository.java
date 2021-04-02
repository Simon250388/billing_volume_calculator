package com.best.billing.calculationsettings.repository;

import com.best.billing.calculationsettings.model.SeasonalitySetting;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface SeasonalitySettingsRepository extends CrudRepository<SeasonalitySetting, Long> {
    @Query("FROM SeasonalitySetting " +
            "WHERE (service,directionOfUse,building,period) in ( " +
            "SELECT service,directionOfUse,building,MAX(period) " +
            "FROM SeasonalitySetting " +
            "WHERE period < :period " +
            "GROUP BY service,directionOfUse,building) ")
    Iterable<SeasonalitySetting> findAllLastByPeriod(@NonNull LocalDate period);
}
