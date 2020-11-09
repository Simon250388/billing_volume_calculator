package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.RateValue;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface RateValueRepository extends CrudRepository<RateValue, Long> {
    CompletableFuture<Iterable<RateValue>> findAllLastByPeriodAsync(@NonNull LocalDate calculationPeriod);
    Iterable<RateValue> findAllLastByPeriod(@NonNull LocalDate calculationPeriod);
}
