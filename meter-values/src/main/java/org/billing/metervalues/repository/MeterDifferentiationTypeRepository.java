package org.billing.metervalues.repository;

import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.billing.metervalues.model.MeterDifferentiationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeterDifferentiationTypeRepository
    extends CrudRepository<MeterDifferentiationType, UUID> {

  @Query(
      value =
          "SELECT * "
              + "FROM meter_differentiation_type "
              + "WHERE (meter_id, period) IN ( "
              + "   SELECT meter_id, MAX(period) "
              + "   FROM meter_differentiation_type"
              + "   GROUP BY meter_id)",
      nativeQuery = true)
  List<MeterDifferentiationType> findAllLastByKeyRoomId(
      @NonNull @Param("keyRoomId") UUID keyRoomId);
}
