package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.RoomResident;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RoomResidentRepository extends CrudRepository<RoomResident, Long> {
    @Query(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    Optional<RoomResident> findOneLastByKeyRoomId(@NonNull Long keyRoomId);

    @Query(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID)
    CompletableFuture<RoomResident> findOneLastByKeyRoomIdAsync(@NonNull Long keyRoomId);

    Iterable<RoomResident> findAllByKeyRoom_Id(@NonNull Long keyRoomId);
}
