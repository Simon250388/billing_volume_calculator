package com.best.billing.servicebuilder.repositories.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomPrescribed;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {
    @Query(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomPrescribed> findOneLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    @Async
    @Query(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Optional<RoomPrescribed>> findOneLastByKeyRoomIdAsync(@NonNull  @Param("keyRoomId") Long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(@NonNull long keyRoomId);
}
