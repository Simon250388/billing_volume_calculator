package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
    @Query(value = "SELECT c" +
            " FROM RoomOwner c" +
            " WHERE c.keyRoom = :keyRoomId" +
            " AND c.period = (  SELECT MAX(c.period)" +
            "                   FROM RoomOwner c" +
            "                   WHERE c.keyRoom =:keyRoomId)")
    Optional<RoomOwner> getLastByKeyRoom(@Param("keyRoomId") long keyRoomId);

    Iterable<RoomOwner> findAllByKeyRoomId(long keyRoomId);
}
