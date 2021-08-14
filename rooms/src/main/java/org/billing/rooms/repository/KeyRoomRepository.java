package org.billing.rooms.repository;

import lombok.NonNull;
import org.billing.rooms.model.KeyRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
    List<KeyRoom> findAllByBuildingId(@NonNull Long buildingId);
    List<KeyRoom> findAllByBuildingIdAndRoomId(@NonNull Long buildingId, @NonNull Long roomId);
}
