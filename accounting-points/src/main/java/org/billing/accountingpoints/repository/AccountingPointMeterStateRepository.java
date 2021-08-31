package org.billing.accountingpoints.repository;

import java.util.List;
import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointMeterStateDto;
import org.billing.accountingpoints.model.AccountingPointMeterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountingPointMeterStateRepository
    extends JpaRepository<AccountingPointMeterState, Long> {

  @Query(
      nativeQuery = true,
      value =
          ""
              + "SELECT * "
              + "FROM accounting_point_meter_states "
              + "WHERE (accounting_point_key_room_service_id, meter_id, period) IN  ( "
              + "       SELECT accounting_point_key_room_service_id,meter_id,MAX(period) "
              + "       FROM accounting_point_meter_states "
              + "       WHERE accounting_point_key_room_service_id IN ( "
              + "           SELECT APS.ID "
              + "            FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY APS "
              + "            INNER JOIN ACCOUNTING_POINT_KEY_ROOM AP"
              + "               ON APS.ACCOUNTING_POINT_KEY_ROOM_ID = AP.ID "
              + "            WHERE AP.KEY_ROOM_ID = :keyRoomId) "
              + "GROUP BY accounting_point_key_room_service_id,meter_id) "
              + "AND meter_state_id = 'ACTIVE'")
  List<AccountingPointMeterStateDto> findAllLastActiveByKeyRoomId(
      @NonNull @Param("keyRoomId") long keyRoomId);
}
