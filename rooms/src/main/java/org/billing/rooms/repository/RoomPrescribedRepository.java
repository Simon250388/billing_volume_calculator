package org.billing.rooms.repository;

import org.billing.rooms.model.RoomPrescribed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {
    @Query(" FROM RoomPrescribed " +
            "WHERE keyRoom.id = :keyRoomId " +
            "AND period = (SELECT MAX(period) " +
            "FROM RoomPrescribed " +
            "WHERE keyRoom.id =:keyRoomId)")
    Optional<RoomPrescribed> findOneLastByKeyRoomId(long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(long keyRoomId);
}
