package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.SeasonalitySetting;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface SeasonalitySettingsRepository extends CrudRepository<SeasonalitySetting, Long> {
    CompletableFuture<Iterable<SeasonalitySetting>> findAllLastByPeriodAsync(@NonNull LocalDate calculationPeriod);
    Iterable<SeasonalitySetting> findAllLastByPeriod(@NonNull LocalDate calculationPeriod);
}
