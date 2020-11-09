package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.KeyNormValue;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public interface KeyNormValueRepository extends CrudRepository<KeyNormValue, Long> {
    CompletableFuture<Iterable<KeyNormValue>> findAllLastByPeriodAsync(@NonNull LocalDate calculationPeriod);
    Iterable<KeyNormValue> findAllLastByPeriod(@NonNull LocalDate calculationPeriod);
}
