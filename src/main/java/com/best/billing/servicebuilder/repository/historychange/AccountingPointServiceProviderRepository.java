package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceProvider;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountingPointServiceProviderRepository extends CrudRepository<AccountingPointServiceProvider, Long> {
    @Query(" FROM AccountingPointServiceProvider " +
            "WHERE (accountingPointKeyRoomServiceEntity, servicePart, period) IN ( " +
            "SELECT accountingPointKeyRoomServiceEntity,servicePart,MAX(period) " +
            "FROM AccountingPointServiceProvider " +
            "WHERE accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id = :keyRoomId " +
            "GROUP BY accountingPointKeyRoomServiceEntity, servicePart)")
    Iterable<AccountingPointServiceProvider> findAllLastByKeyRoomId(@NonNull long keyRoomId);

}
