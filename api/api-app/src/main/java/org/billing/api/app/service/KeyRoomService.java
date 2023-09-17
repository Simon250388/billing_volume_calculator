package org.billing.api.app.service;

import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.keyRoom.KeyRoom;
import org.billing.api.repository.KeyRoomDbService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyRoomService {

  private final KeyRoomDbService keyRoomDbService;

  public KeyRoom save(KeyRoom request, String userId) {
    final String keyRoomId = UUID.randomUUID().toString();
    return keyRoomDbService.save(request.toBuilder().id(keyRoomId).build(), userId);
  }

  public void saveHistory(@NonNull final KeyRoom request, @NonNull final Instant instant) {
    keyRoomDbService.saveHistory(request, instant);
  }

  public void delete(@NonNull final String keyRoomId) {
    keyRoomDbService.deleteById(keyRoomId);
  }
}
