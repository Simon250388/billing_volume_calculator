package org.billing.metervalues.repository;

import lombok.NonNull;
import org.billing.metervalues.model.MeterValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeterValueRepository extends CrudRepository<MeterValue, Long> {
  @Query(
      "FROM MeterValue "
          + "WHERE (accountingPointKeyRoomServiceEntity, meter, period) IN ( "
          + "SELECT accountingPointKeyRoomServiceEntity, meter, MAX(period) "
          + "FROM MeterValue "
          + "WHERE accountingPointKeyRoomServiceEntity"
          + "           .accountingPointKeyRoom"
          + "             .keyRoom.id = :keyRoomId "
          + "GROUP BY accountingPointKeyRoomServiceEntity, meter)")
  Iterable<MeterValue> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
}
