package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.RoomResident;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
    @Query(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomResident> findOneLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    @Query(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<Optional<RoomResident>> findOneLastByKeyRoomIdAsync(@NonNull @Param("keyRoomId") Long keyRoomId);

    Iterable<RoomResident> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
