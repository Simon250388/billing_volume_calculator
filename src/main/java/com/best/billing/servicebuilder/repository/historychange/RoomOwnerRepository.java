package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
    @Query(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomOwner> findOneLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    @Async
    @Query(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Optional<RoomOwner>> findOneLastByKeyRoomIdAsync(@NonNull @Param("keyRoomId") Long keyRoomId);

    Iterable<RoomOwner> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
