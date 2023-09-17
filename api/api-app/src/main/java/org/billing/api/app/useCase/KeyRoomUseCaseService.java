package org.billing.api.app.useCase;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.keyRoom.KeyRoom;
import org.billing.api.repository.KeyRoomDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyRoomUseCaseService {
  private final KeyRoomDbService keyRoomDbService;

  public ResponseEntity<Collection<KeyRoom>> getAll() {
    return ResponseEntity.ok(Collections.emptyList());
  }

  public ResponseEntity<KeyRoom> create(@NonNull final KeyRoom request) {
    final String userName =
            SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    final String keyRoomId = UUID.randomUUID().toString();
    var requestWithId = request.toBuilder().id(keyRoomId).build();
    return ResponseEntity.ok(keyRoomDbService.save(requestWithId, userName));
  }

  @Transactional
  public ResponseEntity<KeyRoom> update(@NonNull final KeyRoom request) {
    final String userName =
             SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    return ResponseEntity.ok(keyRoomDbService.save(request, userName));
  }

  public ResponseEntity<KeyRoom> delete(@NonNull final String id) {
    keyRoomDbService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
