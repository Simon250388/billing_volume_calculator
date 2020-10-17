package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
    @Query(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomOwner> findOneLastByKeyRoomId(@NonNull Long keyRoomId);

    @Async
    @Query(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<RoomOwner> findOneLastByKeyRoomIdAsync(@NonNull Long keyRoomId);

    Iterable<RoomOwner> findAllByKeyRoom_Id(@NonNull Long keyRoomId);
}
