package com.best.billing.common.repository;

import com.best.billing.common.model.KeyRoom;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
    List<KeyRoom> findAllByBuildingId(@NonNull Long buildingId);
    List<KeyRoom> findAllByBuildingIdAndRoomId(@NonNull Long buildingId, @NonNull Long roomId);
}
