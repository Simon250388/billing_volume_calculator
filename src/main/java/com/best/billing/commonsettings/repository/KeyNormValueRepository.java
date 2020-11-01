package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.KeyNormValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface KeyNormValueRepository extends CrudRepository<KeyNormValue, Long> {
    CompletableFuture<Iterable<KeyNormValue>> findAllLastByPeriodAsync(Date calculationPeriod);
}