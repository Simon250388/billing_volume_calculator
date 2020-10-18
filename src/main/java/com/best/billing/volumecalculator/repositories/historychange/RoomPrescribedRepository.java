package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {
    @Query(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomPrescribed> findOneLastByKeyRoomId(@NonNull long keyRoomId);

    @Async
    @Query(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Optional<RoomPrescribed>> findOneLastByKeyRoomIdAsync(@NonNull long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(@NonNull long keyRoomId);
}
