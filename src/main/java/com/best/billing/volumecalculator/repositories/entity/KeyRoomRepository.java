package com.best.billing.volumecalculator.repositories.entity;

import com.best.billing.volumecalculator.models.entity.KeyRoom;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
    List<KeyRoom> findAllByBuildingId(@NotNull Long buildingId);
    List<KeyRoom> findAllByBuildingIdAndRoomId(@NotNull Long buildingId, @NotNull Long roomId);
}
