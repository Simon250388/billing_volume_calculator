package org.billing.api.app.useCase;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.billing.api.repository.KeyRoomDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyRoomUseCaseService {
  private final KeyRoomDbService keyRoomDbService;

  public ResponseEntity<Collection<KeyRoomResponse>> getAll() {
    return ResponseEntity.ok(Collections.emptyList());
  }

  public ResponseEntity<KeyRoomResponse> create(@NonNull final KeyRoomRequest request) {
    final String keyRoomId = UUID.randomUUID().toString();
    final String userName =
        SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    return ResponseEntity.ok(keyRoomDbService.save(keyRoomId, request, userName));
  }

  @Transactional
  public ResponseEntity<KeyRoomResponse> update(
      @NonNull final String id, @NonNull final KeyRoomRequest request) {
    final String userName =
             SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    keyRoomDbService.existOrElseThrow(id);
    return ResponseEntity.ok(keyRoomDbService.save(id, request, userName));
  }

  public ResponseEntity<KeyRoomResponse> delete(@NonNull final String id) {
    keyRoomDbService.existOrElseThrow(id);
    keyRoomDbService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
