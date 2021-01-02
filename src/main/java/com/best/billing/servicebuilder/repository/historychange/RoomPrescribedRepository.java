package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomPrescribed;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {
    @Query(" FROM RoomPrescribed " +
            "WHERE keyRoom.id = :keyRoomId " +
            "AND period = (SELECT MAX(period) " +
            "FROM RoomPrescribed " +
            "WHERE keyRoom.id =:keyRoomId)")
    Optional<RoomPrescribed> findOneLastByKeyRoomId(@NonNull long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(@NonNull long keyRoomId);
}
