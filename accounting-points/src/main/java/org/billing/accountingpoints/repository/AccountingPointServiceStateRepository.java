package org.billing.accountingpoints.repository;

import java.util.List;
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
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_BY_KEY_ROOM_ID
              + " ) GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID) "
              + "AND ACTIVE = TRUE")
  List<AccountingPointServiceState> findAllActiveByKeyRoomId(
      @NonNull @Param("keyRoomId") UUID keyRoomId);
}
