package org.billing.rooms.repository;

import lombok.NonNull;
import org.billing.rooms.model.RoomSquare;
import org.billing.rooms.model.SquareType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomSquareRepository extends CrudRepository<RoomSquare, Long> {
    @Query("FROM RoomSquare " +
            "WHERE keyRoom.id = :keyRoomId " +
            "AND squareType = " + SquareType.COMMON_SQUARE + " " +
            "AND period IN ( " +
            "   SELECT MAX(period) " +
            "   FROM RoomSquare " +
            "   WHERE keyRoom.id = :keyRoomId " +
            "   AND squareType =" + SquareType.COMMON_SQUARE + ") ")
    Optional<RoomSquare> findOneLastCommonSquareByKeyRoomId(long keyRoomId);

    Iterable<RoomSquare> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
