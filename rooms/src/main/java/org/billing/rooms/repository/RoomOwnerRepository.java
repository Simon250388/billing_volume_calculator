package org.billing.rooms.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.billing.rooms.model.RoomOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomOwnerRepository extends CrudRepository<RoomOwner, Long> {
  @Query(
      "FROM RoomOwner "
          + "WHERE keyRoom.id = :keyRoomId "
          + "AND period = ( "
          + "SELECT MAX(period) "
          + "FROM RoomOwner "
          + "WHERE keyRoom.id =:keyRoomId)")
  Optional<RoomOwner> findOneLastByKeyRoomId(long keyRoomId);

  Iterable<RoomOwner> findAllByKeyRoomId(long keyRoomId);

  Iterable<RoomOwner> findAllByKeyRoomIdAndPeriodBetween(
      long keyRoomId, LocalDate periodStart, LocalDate periodEnd);
}