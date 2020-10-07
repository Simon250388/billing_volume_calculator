package com.best.billing.volumecalculator.controllers.v1.catalog;

import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.services.entity.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/key-room")
public class KeyRoomController {
    private final KeyRoomService entityService;

    @Autowired
    public KeyRoomController(@NotNull KeyRoomService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<ResponseListDTO<KeyRoomDTO>> getById(@PathVariable long keyRoomId) {
        Optional<KeyRoomDTO> value = this.entityService.findById(keyRoomId);
        return value.map(keyRoom -> new ResponseEntity<>(
                new ResponseListDTO<>(
                        Collections.singletonList(keyRoom),
                        Collections.emptyMap(),
                        new ArrayList<>()),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new ResponseListDTO<>(
                        new ArrayList<>(),
                        Collections.emptyMap(),
                        Collections.singletonList(String.format("Key room with key %o not found", keyRoomId))),
                HttpStatus.NOT_FOUND));
    }
}
