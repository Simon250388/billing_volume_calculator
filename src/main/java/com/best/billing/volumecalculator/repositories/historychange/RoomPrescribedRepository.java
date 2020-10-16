package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {

    Optional<RoomPrescribed> findOneLastByKeyRoomId(@Param("keyRoomId") long keyRoomId);
    @Async
    @Query(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<RoomPrescribed> findOneLastByKeyRoomIdAsync(@Param("keyRoomId") long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(long keyRoomId);
}
