package org.billing.accountingpoints.repository;

import lombok.NonNull;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDTO;
import org.billing.accountingpoints.model.AccountingPointServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountingPointServiceProviderRepository extends JpaRepository<AccountingPointServiceProvider, Long> {
    @Query(nativeQuery = true,
            value = "SELECT APSP.* " +
                    "FROM ACCOUNTING_POINT_SERVICE_PROVIDER AS APSP " +
                    "INNER JOIN " +
                    "   (SELECT ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID,SERVICE_PART_ID,MAX(PERIOD) PERIOD " +
                    "       FROM ACCOUNTING_POINT_SERVICE_PROVIDER " +
                    "       WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID in " +
                    "           (SELECT APS.ID " +
                    "               FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY APS " +
                    "               INNER JOIN ACCOUNTING_POINT_KEY_ROOM AP ON APS.ACCOUNTING_POINT_KEY_ROOM_ID = AP.ID " +
                    "               WHERE AP.KEY_ROOM_ID = :keyRoomId ) " +
                    "       GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID,SERVICE_PART_ID) SQ  " +
                    "ON APSP.ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID = SQ.ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID " +
                    "AND  " +
                    "   CASE " +
                    "       WHEN APSP.SERVICE_PART_ID IS NULL THEN -1 " +
                    "       ELSE APSP.SERVICE_PART_ID " +
                    "   END =  " +
                    "   CASE " +
                    "       WHEN SQ.SERVICE_PART_ID IS NULL THEN -1 " +
                    "       ELSE SQ.SERVICE_PART_ID " +
                    "   END " +
                    "AND APSP.PERIOD = SQ.PERIOD"
    )
    List<AccountingPointServiceProviderDTO> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
}
