package com.best.billingvolumecalculator.repositories.historyChange;

import com.best.billingvolumecalculator.models.historyChange.RoomOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
    @Query(value = "SELECT ro.* FROM room_owners ro" +
            " INNER JOIN (SELECT MAX(r.period)" +
            "             FROM room_owners r" +
            "             WHERE r.key_room_id =:KeyRoomId) maxPeriod" +
            " ON ro.key_room_id = maxPeriod.key_room_id" +
            " AND ro.period = maxPeriod.period" +
            " AND ro.key_room_id =:KeyRoomId",
    nativeQuery = true)
    Optional<RoomOwner> getLastByKeyRoom(@Param("KeyRoomId") long keyRoomId);
}
