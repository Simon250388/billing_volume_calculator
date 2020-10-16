package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomSquare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomSquareRepository extends CrudRepository<RoomSquare, Long> {
    Optional<RoomSquare> findOneLastCommonSquareByKeyRoomId(Long keyRoomId);
    @Query(name = RoomSquare.FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID)
    CompletableFuture<RoomSquare> findOneLastCommonSquareByKeyRoomIdAsync(Long keyRoomId);
}
