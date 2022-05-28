package org.billing.api.app.useCase.keyRoom;

import java.time.Clock;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import or.billing.api.repository.KeyRoomDbService;
import org.billing.api.app.service.KeyRoomService;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyRoomUseCaseService {
  private final KeyRoomDbService keyRoomDbService;

  private final UserProvider userProvider;

  public ResponseEntity<Collection<KeyRoomResponse>> getAll() {
    return ResponseEntity.ok(Collections.emptyList());
  }

  public ResponseEntity<KeyRoomResponse> create(@NonNull final KeyRoomRequest request) {
    final String keyRoomId = UUID.randomUUID().toString();
    return ResponseEntity.ok(keyRoomDbService.save(keyRoomId, request, userProvider.getUser()));
  }

  @Transactional
  public ResponseEntity<KeyRoomResponse> update(
      @NonNull final String id, @NonNull final KeyRoomRequest request) {
    keyRoomDbService.keyRoomExistOrElseThrow(id);
    return ResponseEntity.ok(keyRoomDbService.save(id, request, userProvider.getUser()));
  }

  public ResponseEntity<KeyRoomResponse> delete(@NonNull final String id) {
    keyRoomDbService.keyRoomExistOrElseThrow(id);
    keyRoomDbService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
