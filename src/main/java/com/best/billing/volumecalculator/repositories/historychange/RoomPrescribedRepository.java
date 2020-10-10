package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomPrescribedRepository extends CrudRepository<RoomPrescribed, Long> {
    @Query(value = "SELECT c" +
            " FROM RoomPrescribed c" +
            " WHERE c.keyRoom = :keyRoomId" +
            " AND c.period = (  SELECT MAX(c.period)" +
            "                   FROM RoomOwner c" +
            "                   WHERE c.keyRoom =:keyRoomId)")
    Optional<RoomPrescribed> findOneLastByKeyRoomId(@Param("keyRoomId") long keyRoomId);

    Iterable<RoomPrescribed> findAllByKeyRoomId(long keyRoomId);
}
