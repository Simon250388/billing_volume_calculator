package org.billing.accountingpoints.repository;

import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDTO;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long> {

    @Query(nativeQuery = true,
            value = "" +
                    "SELECT * " +
                    "FROM ACCOUNTING_POINT_SERVICE_STATE " +
                    "WHERE (ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, PERIOD) IN ( " +
                    "   SELECT ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID, MAX(PERIOD) " +
                    "   FROM ACCOUNTING_POINT_SERVICE_STATE " +
                    "   WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID IN (" +
                    "       SELECT SE.ID" +
                    "       FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY SE" +
                    "       INNER JOIN ACCOUNTING_POINT_KEY_ROOM KR  " +
                    "       ON SE.ACCOUNTING_POINT_KEY_ROOM_ID = KR.ID" +
                    "       WHERE KR.KEY_ROOM_ID = :keyRoomId) " +
                    "   GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID) " +
                    "AND ACTIVE = TRUE")
    List<AccountingPointServiceStateDTO> findAllActiveByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
}
