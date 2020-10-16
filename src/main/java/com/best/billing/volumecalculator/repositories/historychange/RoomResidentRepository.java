package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomResident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
    Optional<RoomResident> findOneLastByKeyRoomId(@Param("keyRoomId") long keyRoomId);
    @Query(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<RoomResident> findOneLastByKeyRoomIdAsync(Long keyRoomId);
    Iterable<RoomResident> findAllByKeyRoomId(long keyRoomId);
}
