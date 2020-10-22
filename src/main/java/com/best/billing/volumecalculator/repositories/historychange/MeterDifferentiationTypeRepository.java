package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.MeterDifferentiationType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.CompletableFuture;

public interface MeterDifferentiationTypeRepository  extends CrudRepository<MeterDifferentiationType,Long> {
    @Query(name = MeterDifferentiationType.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    Iterable<MeterDifferentiationType> findAllLastByKeyRoomId(@NotNull @Param("keyRoomId") Long keyRoomId);
    @Query(name = MeterDifferentiationType.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Iterable<MeterDifferentiationType>> findAllLastByKeyRoomIdAsync(@NotNull @Param("keyRoomId")  Long keyRoomId);
}
