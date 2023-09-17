package org.billing.api.app.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.billing.api.app.useCase.KeyRoomUseCaseService;
import org.billing.api.model.keyRoom.KeyRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/key-room")
@RequiredArgsConstructor
@Validated
public class KeyRoomController {

  private final KeyRoomUseCaseService keyRoomUseCaseService;

  @GetMapping
  public ResponseEntity<Collection<KeyRoom>> getAll() {
    return keyRoomUseCaseService.getAll();
  }

  @PostMapping
  @SneakyThrows
  public ResponseEntity<KeyRoom> create(
      @RequestBody @Validated(KeyRoom.KeyRoomCreateValidationGroup.class) KeyRoom request) {
    return keyRoomUseCaseService.create(request);
  }

  @PutMapping
  public ResponseEntity<KeyRoom> update(
      @RequestBody @Validated(KeyRoom.KeyRoomUpdateValidationGroup.class) KeyRoom request) {
    return keyRoomUseCaseService.update(request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<KeyRoom> delete(@PathVariable String id) {
    return keyRoomUseCaseService.delete(id);
  }
}
