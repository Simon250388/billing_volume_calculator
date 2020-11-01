package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.MeterValue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.CompletableFuture;

public interface MeterValueRepository extends CrudRepository<MeterValue, Long> {
    @Query(name = MeterValue.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    Iterable<MeterValue> findAllLastByKeyRoomId(@NotNull @Param("keyRoomId") Long keyRoomId);
    @Query(name = MeterValue.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Iterable<MeterValue>> findAllLastByKeyRoomIdAsync(@NotNull @Param("keyRoomId") Long keyRoomId);
}
