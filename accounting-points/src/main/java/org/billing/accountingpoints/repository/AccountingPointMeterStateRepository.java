package org.billing.accountingpoints.repository;

import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.billing.accountingpoints.model.AccountingPointMeterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountingPointMeterStateRepository
    extends JpaRepository<AccountingPointMeterState, UUID> {

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
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_BY_KEY_ROOM_ID
              + " ) GROUP BY accounting_point_key_room_service_id,meter_id) "
              + "AND meter_state_id = 'ACTIVE'")
  List<AccountingPointMeterState> findAllLastActiveByKeyRoomId(
      @NonNull @Param("keyRoomId") UUID keyRoomId);
}
