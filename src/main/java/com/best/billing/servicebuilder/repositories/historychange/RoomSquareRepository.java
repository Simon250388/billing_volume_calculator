package com.best.billing.servicebuilder.repositories.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomSquareRepository extends CrudRepository<RoomSquare, Long> {
    @Query(name = RoomSquare.FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID)
    Optional<RoomSquare> findOneLastCommonSquareByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    @Query(name = RoomSquare.FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID)
    CompletableFuture<Optional<RoomSquare>> findOneLastCommonSquareByKeyRoomIdAsync(@NonNull @Param("keyRoomId") Long keyRoomId);

    Iterable<RoomSquare> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
