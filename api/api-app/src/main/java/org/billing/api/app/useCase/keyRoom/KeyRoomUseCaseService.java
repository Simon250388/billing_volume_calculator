package org.billing.api.app.useCase.keyRoom;

import java.time.Clock;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.service.KeyRoomService;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyRoomUseCaseService {
  private final KeyRoomService keyRoomService;

  private final Clock clock;

  private final UserProvider userProvider;

  public ResponseEntity<Collection<KeyRoomResponse>> getAll() {
    return ResponseEntity.ok(Collections.emptyList());
  }

  public ResponseEntity<KeyRoomResponse> create(@NonNull final KeyRoomRequest request) {
    return ResponseEntity.ok(keyRoomService.save(request, userProvider.getUser()));
  }

  @Transactional
  public ResponseEntity<KeyRoomResponse> update(
      @NonNull final String id, @NonNull final KeyRoomRequest request) {
    keyRoomService.keyRoomExistOrElseThrow(id);
    keyRoomService.saveHistory(request, Instant.now(clock));
    return ResponseEntity.ok(keyRoomService.save(id, request, userProvider.getUser()));
  }

  public ResponseEntity<KeyRoomResponse> delete(@NonNull final String id) {
    keyRoomService.keyRoomExistOrElseThrow(id);
    keyRoomService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
