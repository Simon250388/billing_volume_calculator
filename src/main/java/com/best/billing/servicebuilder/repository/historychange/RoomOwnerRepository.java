package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import lombok.NonNull;
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
    Optional<RoomOwner> findOneLastByKeyRoomId(@NonNull long keyRoomId);

    Iterable<RoomOwner> findAllByKeyRoomId(@NonNull long keyRoomId);
}
