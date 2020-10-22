package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface AccountingPointServiceProviderRepository extends CrudRepository<AccountingPointServiceProvider, Long> {
    @Query(name = AccountingPointServiceProvider.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    Iterable<AccountingPointServiceProvider> findAllLastByKeyRoomId(@NotNull @Param("keyRoomId") Long keyRoomId);

    @Query(name = AccountingPointServiceProvider.FIND_ALL_LAST_BY_KEY_ROOM_ID)
    @Async
    CompletableFuture<Iterable<AccountingPointServiceProvider>> findAllLastByKeyRoomIdAsync(@NotNull @Param("keyRoomId") Long keyRoomId);
}
