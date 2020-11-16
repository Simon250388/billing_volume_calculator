package com.best.billing.commonsettings.repository;

import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public interface CalculationMethodByDirectionOfUseRepository extends CrudRepository<CalculationMethodByDirectionOfUse,Long> {
    CompletableFuture<Iterable<CalculationMethodByDirectionOfUse>> findAllLastByPeriodAsync(@NonNull LocalDate calculationPeriod);
    Iterable<CalculationMethodByDirectionOfUse> findAllLastByPeriod(@NonNull LocalDate calculationPeriod);
}
