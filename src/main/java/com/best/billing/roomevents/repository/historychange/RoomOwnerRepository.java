package com.best.billing.roomevents.repository.historychange;

import com.best.billing.roomevents.models.RoomOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
    @Query("FROM RoomOwner " +
            "WHERE keyRoom.id = :keyRoomId " +
            "AND period = ( " +
            "SELECT MAX(period) " +
            "FROM RoomOwner " +
            "WHERE keyRoom.id =:keyRoomId)")
    Optional<RoomOwner> findOneLastByKeyRoomId(long keyRoomId);

    Iterable<RoomOwner> findAllByKeyRoomId(long keyRoomId);
}