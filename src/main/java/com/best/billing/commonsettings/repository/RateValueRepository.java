package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.RateValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface RateValueRepository extends CrudRepository<RateValue, Long> {
    CompletableFuture<Iterable<RateValue>> findAllLastByPeriodAsync(Date calculationPeriod);
}
