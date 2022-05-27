package or.billing.api.repository;

import java.time.Instant;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRoomDbService {
  KeyRoomResponse save(String id, KeyRoomRequest request, String userId);

  boolean notExistsById(String keyRoomId);

  void saveHistory(KeyRoomRequest request, Instant instant);

  void deleteById(String keyRoomId);
}
