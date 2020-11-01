package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.SeasonalitySetting;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface SeasonalitySettingsRepository extends CrudRepository<SeasonalitySetting, Long> {
    CompletableFuture<Iterable<SeasonalitySetting>> findAllLastByPeriodAsync(Date calculationPeriod);
}
