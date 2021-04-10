package com.best.billing.roomevents.repository;

import com.best.billing.roomevents.models.MeterDifferentiationType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeterDifferentiationTypeRepository extends CrudRepository<MeterDifferentiationType, Long> {
    @Query("FROM MeterDifferentiationType " +
            "WHERE (accountingPointKeyRoomServiceEntity, meter, period) IN ( " +
            "SELECT accountingPointKeyRoomServiceEntity, meter, MAX(period) " +
            "FROM MeterDifferentiationType " +
            "WHERE accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id = :keyRoomId " +
            "GROUP BY accountingPointKeyRoomServiceEntity, meter)")
    @EntityGraph(value = "meter-differentiation-type-entity-graph")
    Iterable<MeterDifferentiationType> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

}
