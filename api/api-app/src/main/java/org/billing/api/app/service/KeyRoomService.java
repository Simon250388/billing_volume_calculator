package org.billing.api.app.service;

import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import or.billing.api.repository.KeyRoomDbService;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyRoomService {

  private final KeyRoomDbService keyRoomDbService;

  public KeyRoomResponse save(KeyRoomRequest request, String userId) {
    final String keyRoomId = UUID.randomUUID().toString();
    return keyRoomDbService.save(keyRoomId, request, userId);
  }

  public KeyRoomResponse save(
      @NonNull final String keyRoomId,
      @NonNull final KeyRoomRequest request,
      @NonNull final String userId) {
    return keyRoomDbService.save(keyRoomId, request, userId);
  }

  public void saveHistory(@NonNull final KeyRoomRequest request, @NonNull final Instant instant) {
    keyRoomDbService.saveHistory(request, instant);
  }

  public void delete(@NonNull final String keyRoomId) {
    keyRoomDbService.deleteById(keyRoomId);
  }
}
