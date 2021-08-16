package org.billing.rooms.repository;

import java.util.List;
import lombok.NonNull;
import org.billing.rooms.model.KeyRoom;
import org.springframework.data.repository.CrudRepository;

public interface KeyRoomRepository extends CrudRepository<KeyRoom, Long> {
  List<KeyRoom> findAllByBuildingId(@NonNull Long buildingId);

  List<KeyRoom> findAllByBuildingIdAndRoomId(@NonNull Long buildingId, @NonNull Long roomId);
}
