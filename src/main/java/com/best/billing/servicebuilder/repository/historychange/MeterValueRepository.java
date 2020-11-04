package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.MeterValue;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.CompletableFuture;

public interface MeterValueRepository extends CrudRepository<MeterValue, Long> {
    @Query(name = MeterValue.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    Iterable<MeterValue> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
    @Query(name = MeterValue.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Iterable<MeterValue>> findAllLastByKeyRoomIdAsync(@NonNull @Param("keyRoomId") Long keyRoomId);
}
