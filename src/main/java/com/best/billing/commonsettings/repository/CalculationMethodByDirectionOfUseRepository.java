package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface CalculationMethodByDirectionOfUseRepository extends CrudRepository<CalculationMethodByDirectionOfUse,Long> {
    CompletableFuture<Iterable<CalculationMethodByDirectionOfUse>> findAllLastByPeriodAsync(Date calculationPeriod);
    Iterable<CalculationMethodByDirectionOfUse> findAllLastByPeriod(Date calculationPeriod);
}
