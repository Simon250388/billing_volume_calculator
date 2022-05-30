package org.billing.api.repository;

import java.time.Instant;
import lombok.NonNull;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRoomDbService {
  KeyRoomResponse save(String id, KeyRoomRequest request, String userId);

  void existOrElseThrow(@NonNull final String id);

  boolean notExistsById(String id);

  void saveHistory(KeyRoomRequest request, Instant instant);

  void deleteById(String id);

  boolean exist(String value);

  void deleteAll();
}
