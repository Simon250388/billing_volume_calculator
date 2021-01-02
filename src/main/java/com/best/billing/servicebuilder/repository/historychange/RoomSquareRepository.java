package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.common.model.enums.SquareType;
import com.best.billing.servicebuilder.models.historychange.RoomSquare;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomSquareRepository extends CrudRepository<RoomSquare, Long> {
    @Query("FROM RoomSquare " +
            "WHERE keyRoom.id = :keyRoomId " +
            "AND squareType = " + SquareType.COMMON_SQUARE_ID + " " +
            "AND period IN ( " +
            "   SELECT MAX(period) " +
            "   FROM RoomSquare " +
            "   WHERE keyRoom.id = :keyRoomId " +
            "   AND squareType =" + SquareType.COMMON_SQUARE_ID + ") ")
    Optional<RoomSquare> findOneLastCommonSquareByKeyRoomId(@NonNull long keyRoomId);

    Iterable<RoomSquare> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
