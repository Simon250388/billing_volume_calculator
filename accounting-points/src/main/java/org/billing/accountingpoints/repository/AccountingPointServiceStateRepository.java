package org.billing.accountingpoints.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.NonNull;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceStateRepository
    extends CrudRepository<AccountingPointServiceState, UUID> {

  @Query(
      nativeQuery = true,
      value =
          ""
              + "SELECT * "
              + "FROM ACCOUNTING_POINT_SERVICE_STATE "
              + "WHERE (ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, PERIOD) IN ( "
              + "   SELECT ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, MAX(PERIOD) "
              + "   FROM ACCOUNTING_POINT_SERVICE_STATE "
              + "   WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID IN ( "
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_WHERE_KEY_ROOM_ID
              + " ) GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID) "
              + "AND ACTIVE = TRUE")
  List<AccountingPointServiceState> findAllActive(@NonNull @Param("keyRoomId") UUID keyRoomId);

  @Query(
      nativeQuery = true,
      value =
          ""
              + "SELECT * "
              + "FROM ACCOUNTING_POINT_SERVICE_STATE "
              + "WHERE (ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, PERIOD) IN ( "
              + "   SELECT ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, MAX(PERIOD) "
              + "   FROM ACCOUNTING_POINT_SERVICE_STATE "
              + "   WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID IN ( "
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_WHERE_KEY_ROOM_ID_AND_ACC_POINT_ID
              + " ) GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID) "
              + "AND ACTIVE = TRUE")
  AccountingPointServiceState findAllActive(
      @NonNull @Param("keyRoomId") UUID keyRoomId,
      @NonNull @Param("accountingPointId") UUID accountingPointId);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * "
              + "FROM ACCOUNTING_POINT_SERVICE_STATE "
              + "WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID IN ( "
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_WHERE_KEY_ROOM_ID_AND_ACC_POINT_ID
              + " )")
  Set<AccountingPointServiceState> findAll(@NonNull @Param("keyRoomId") UUID keyRoomId);
}
