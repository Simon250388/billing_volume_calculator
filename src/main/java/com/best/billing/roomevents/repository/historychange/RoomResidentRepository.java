package com.best.billing.roomevents.repository.historychange;

import com.best.billing.roomevents.models.RoomResident;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
    @Query(" FROM RoomResident c" +
            " WHERE c.keyRoom.id = :keyRoomId" +
            " AND c.period = (SELECT MAX(c.period)" +
            "                 FROM RoomResident c" +
            "                 WHERE c.keyRoom.id =:keyRoomId)")
    Optional<RoomResident> findOneLastByKeyRoomId(long keyRoomId);

    Iterable<RoomResident> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
