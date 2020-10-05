package com.best.billingvolumecalculator.controllers.v1.catalog;

import com.best.billingvolumecalculator.models.entity.KeyRoom;
import com.best.billingvolumecalculator.services.entity.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/key-room")
public class KeyRoomController {
    private KeyRoomService entityService;

    @Autowired
    public KeyRoomController(@NotNull KeyRoomService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<KeyRoom> getById(@PathVariable long keyRoomId) {
        Optional<KeyRoom> value = this.entityService.findById(keyRoomId);
        if (!value.isPresent()) {
            // TODO FIX ME
            return new ResponseEntity(String.format("Key room with key %o not found", keyRoomId), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<KeyRoom>(value.get(), HttpStatus.OK);
    }
}
