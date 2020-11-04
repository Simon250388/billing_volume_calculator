package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceProvider;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface AccountingPointServiceProviderRepository extends CrudRepository<AccountingPointServiceProvider, Long> {
    @Query(name = AccountingPointServiceProvider.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    Iterable<AccountingPointServiceProvider> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    @Query(name = AccountingPointServiceProvider.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    @Async
    CompletableFuture<Iterable<AccountingPointServiceProvider>> findAllLastByKeyRoomIdAsync(@NonNull @Param("keyRoomId") Long keyRoomId);
}
