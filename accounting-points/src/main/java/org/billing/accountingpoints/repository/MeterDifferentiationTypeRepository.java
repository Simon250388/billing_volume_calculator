package org.billing.accountingpoints.repository;

import java.util.List;
import lombok.NonNull;
import org.billing.accountingpoints.dto.MeterDifferentiationTypeDto;
import org.billing.accountingpoints.model.MeterDifferentiationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeterDifferentiationTypeRepository
    extends CrudRepository<MeterDifferentiationType, Long> {

  @Query(
      value =
          "SELECT * "
              + "FROM meter_differentiation_type "
              + "WHERE (accounting_point_key_room_service_id, meter_id, period) IN ( "
              + "   SELECT accounting_point_key_room_service_id, meter_id, MAX(period) "
              + "   FROM meter_differentiation_type "
              + "   WHERE accounting_point_key_room_service_id IN "
              + "      (SELECT APS.ID "
              + "        FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY APS "
              + "        INNER JOIN ACCOUNTING_POINT_KEY_ROOM AP"
              + "          ON APS.ACCOUNTING_POINT_KEY_ROOM_ID = AP.ID "
              + "        WHERE AP.KEY_ROOM_ID = :keyRoomId ) "
              + "   GROUP BY accounting_point_key_room_service_id, meter_id)",
      nativeQuery = true)
  List<MeterDifferentiationTypeDto> findAllLastByKeyRoomId(
      @NonNull @Param("keyRoomId") Long keyRoomId);
}
