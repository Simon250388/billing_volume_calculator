package com.best.billing.volumecalculator.controllers.v1.entity;

import com.best.billing.volumecalculator.dto.ResponseDTO;
import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.services.entity.KeyRoomService;
import com.best.billing.volumecalculator.services.historychange.RoomOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/key-room")
public class KeyRoomController {
    private final KeyRoomService entityService;

    @Autowired
    public KeyRoomController(@NotNull KeyRoomService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<ResponseDTO<KeyRoomDTO>> getById(@PathVariable long keyRoomId) {
        Optional<KeyRoomDTO> value = this.entityService.findById(keyRoomId);
        return value.map(keyRoom -> new ResponseEntity<>(
                new ResponseDTO<>(keyRoom),HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(
                new ResponseDTO<>(
                        null,
                        Collections.emptyMap(),
                        Collections.singletonList(String.format("Key room with key %o not found", keyRoomId))),
                HttpStatus.NOT_FOUND));
    }

    @GetMapping("/last-details/{keyRoomId}")
    public ResponseEntity doGetLastDetails(@PathVariable(name = "keyRoomId") @NotNull Long keyRoomId) {
        // entityService.doGetLastDetails(keyRoomId);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<ResponseListDTO<KeyRoomDTO>> getAll(
            @RequestParam(value = "building", required = false) Long buildingId,
            @RequestParam(value = "room", required = false) Long roomId ) {

        if (buildingId == null ) {
            return new ResponseEntity<>(
                    new ResponseListDTO<>(
                            this.entityService.findAll()),HttpStatus.OK);
        } else if (roomId == null) {
            return new ResponseEntity<>(
                    new ResponseListDTO<>(
                            this.entityService.findAll(buildingId)),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ResponseListDTO<>(
                            this.entityService.findAll(buildingId, roomId)),HttpStatus.OK);
        }
    }
}
