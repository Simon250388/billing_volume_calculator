package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {

    Optional<RoomOwner> findOneLastByKeyRoomId(@Param("keyRoomId") long keyRoomId);
    @Async
    @Query(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<RoomOwner> findOneLastByKeyRoomIdAsync(@Param("keyRoomId") long keyRoomId);
    Iterable<RoomOwner> findAllByKeyRoomId(long keyRoomId);
}
