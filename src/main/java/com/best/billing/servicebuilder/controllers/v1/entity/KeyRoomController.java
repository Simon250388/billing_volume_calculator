package com.best.billing.servicebuilder.controllers.v1.entity;

import com.best.billing.base.dto.ResponseDTO;
import com.best.billing.base.dto.ResponseListDTO;
import com.best.billing.servicebuilder.dto.entity.KeyRoomDTO;
import com.best.billing.servicebuilder.dto.helpers.KeyRoomDetailPropertyDTO;
import com.best.billing.servicebuilder.services.entity.KeyRoomService;
import com.best.billing.servicebuilder.services.helpers.KeyRoomDetailProperty;
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
    private final KeyRoomDetailProperty detailPropertyService;

    @Autowired
    public KeyRoomController(KeyRoomService entityService, KeyRoomDetailProperty detailPropertyService) {
        this.entityService = entityService;
        this.detailPropertyService = detailPropertyService;
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

    @GetMapping("/last-details-property/{keyRoomId}")
    public ResponseEntity<ResponseDTO<KeyRoomDetailPropertyDTO>> doGetLastDetails(@PathVariable(name = "keyRoomId") @NotNull Long keyRoomId) {
        return new ResponseEntity<>(
                new ResponseDTO<>(detailPropertyService.doGetLastDetails(keyRoomId)),HttpStatus.OK);

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
