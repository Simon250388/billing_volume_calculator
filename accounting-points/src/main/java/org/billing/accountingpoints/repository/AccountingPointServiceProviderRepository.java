package org.billing.accountingpoints.repository;

import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.billing.accountingpoints.model.AccountingPointServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceProviderRepository
    extends JpaRepository<AccountingPointServiceProvider, UUID> {
  @Query(
      nativeQuery = true,
      value =
          "SELECT APSP.* "
              + "FROM ACCOUNTING_POINT_SERVICE_PROVIDER AS APSP "
              + "INNER JOIN "
              + "   (SELECT "
              + "       ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID "
              + "       ,SERVICE_PART_ID "
              + "       ,MAX(PERIOD) PERIOD "
              + "       FROM ACCOUNTING_POINT_SERVICE_PROVIDER "
              + "       WHERE ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID in ("
              + AccountingPointKeyRoomServiceEntity.SELECT_ID_BY_KEY_ROOM_ID
              + ") GROUP BY ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID,SERVICE_PART_ID) SQ "
              + " ON APSP.ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID"
              + "              = SQ.ACCOUNTING_POINT_KEY_ROOM_SERVICE_ID "
              + "AND  "
              + "   CASE "
              + "       WHEN APSP.SERVICE_PART_ID IS NULL THEN -1 "
              + "       ELSE APSP.SERVICE_PART_ID "
              + "   END =  "
              + "   CASE "
              + "       WHEN SQ.SERVICE_PART_ID IS NULL THEN -1 "
              + "       ELSE SQ.SERVICE_PART_ID "
              + "   END "
              + "AND APSP.PERIOD = SQ.PERIOD")
  List<AccountingPointServiceProvider> findAllLastByKeyRoomId(
      @NonNull @Param("keyRoomId") UUID keyRoomId);
}
