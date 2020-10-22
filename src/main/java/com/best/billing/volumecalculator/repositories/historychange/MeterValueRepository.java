package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.MeterValue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface MeterValueRepository extends CrudRepository<MeterValue, Long> {
    Iterable<MeterValue> findAllLastByKeyRoomId(@NotNull Long keyRoomId);
}
