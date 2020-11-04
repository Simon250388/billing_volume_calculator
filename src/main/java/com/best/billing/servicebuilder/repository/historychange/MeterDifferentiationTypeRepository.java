package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.MeterDifferentiationType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.CompletableFuture;

public interface MeterDifferentiationTypeRepository  extends CrudRepository<MeterDifferentiationType,Long> {
    @Query(name = MeterDifferentiationType.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    @EntityGraph(value = "meter-differentiation-type-entity-graph")
    Iterable<MeterDifferentiationType> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
    @Query(name = MeterDifferentiationType.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    @EntityGraph(value = "meter-differentiation-type-entity-graph")
    CompletableFuture<Iterable<MeterDifferentiationType>> findAllLastByKeyRoomIdAsync(@NonNull @Param("keyRoomId")  Long keyRoomId);
}
