package com.best.billing.roomevents.repository.entity;

import com.best.billing.roomevents.models.entity.KeyRoom;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
    List<KeyRoom> findAllByBuildingId(@NonNull Long buildingId);
    List<KeyRoom> findAllByBuildingIdAndRoomId(@NonNull Long buildingId, @NonNull Long roomId);
}
