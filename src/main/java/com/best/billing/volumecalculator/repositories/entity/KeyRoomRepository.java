package com.best.billing.volumecalculator.repositories.entity;

import com.best.billing.volumecalculator.models.entity.KeyRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
    List<KeyRoom> findAllByBuildingId(long buildingId);
    List<KeyRoom> findAllByBuildingIdAndRoomId(long buildingId, long roomId);
}
