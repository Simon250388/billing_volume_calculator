package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomResident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
    @Query(value = "" +
            " FROM RoomPrescribed c" +
            " WHERE c.keyRoom = :keyRoomId" +
            " AND c.period = (SELECT MAX(c.period)" +
            "                 FROM RoomOwner c" +
            "                 WHERE c.keyRoom =:keyRoomId)")
    Optional<RoomResident> findOneLastByKeyRoomId(@Param("keyRoomId") long keyRoomId);

    Iterable<RoomResident> findAllByKeyRoomId(long keyRoomId);
}
