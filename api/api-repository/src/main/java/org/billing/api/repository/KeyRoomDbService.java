package org.billing.api.repository;

import java.time.Instant;
import lombok.NonNull;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRoomDbService {
  KeyRoomResponse save(String id, KeyRoomRequest request, String userId);

  void keyRoomExistOrElseThrow(@NonNull final String keyRoomId);

  boolean notExistsById(String keyRoomId);

  void saveHistory(KeyRoomRequest request, Instant instant);

  void deleteById(String keyRoomId);

  boolean exist(String value);
}
