package org.billing.api.repository;

import java.time.Instant;
import lombok.NonNull;
import org.billing.api.model.keyRoom.KeyRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRoomDbService {
  KeyRoom save(KeyRoom request, String userId);

  void existOrElseThrow(@NonNull final String id);

  boolean notExistsById(String id);

  void saveHistory(KeyRoom request, Instant instant);

  void deleteById(String id);

  boolean exist(String value);

  void deleteAll();
}
