package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.MeterDifferentiationType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface MeterDifferentiationTypeRepository  extends CrudRepository<MeterDifferentiationType,Long> {
    Iterable<MeterDifferentiationType> findAllLastByKeyRoomId(@NotNull Long keyRoomId);
}
