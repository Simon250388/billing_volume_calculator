package org.billing.api.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.billing.api.app.useCase.KeyRoomUseCaseService;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
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

    private final ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<KeyRoomResponse>> getAll() {
        return keyRoomUseCaseService.getAll();
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<KeyRoomResponse> create(
            @RequestBody @Validated(KeyRoomRequest.KeyRoomCreateRequestValidationGroup.class) KeyRoomRequest request) {
        return keyRoomUseCaseService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyRoomResponse> update(@PathVariable String id, @RequestBody @Valid KeyRoomRequest request) {
        return keyRoomUseCaseService.update(id, request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KeyRoomResponse> delete(@PathVariable String id) {
        return keyRoomUseCaseService.delete(id);
    }
}
