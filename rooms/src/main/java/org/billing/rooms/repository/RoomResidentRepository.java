package org.billing.rooms.repository;

import java.util.Optional;
import lombok.NonNull;
import org.billing.rooms.model.RoomResident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
  @Query(
      " FROM RoomResident c"
          + " WHERE c.keyRoom.id = :keyRoomId"
          + " AND c.period = (SELECT MAX(c.period)"
          + "                 FROM RoomResident c"
          + "                 WHERE c.keyRoom.id =:keyRoomId)")
  Optional<RoomResident> findOneLastByKeyRoomId(long keyRoomId);

  Iterable<RoomResident> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
