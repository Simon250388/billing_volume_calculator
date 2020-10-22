package com.best.billing.volumecalculator.controllers.v1.historychange;

import com.best.billing.volumecalculator.dto.ResponseDTO;
import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.services.historychange.RoomOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/room-owner")
public class RoomOwnerController {
    private final RoomOwnerService entityService;

    public RoomOwnerController(RoomOwnerService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/history/{keyRoomId}")
    public ResponseEntity<ResponseListDTO<RoomOwnerDTO>> doGetHistory(@PathVariable final long keyRoomId ) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(this.entityService.doGetHistoryByKeyRoomId(keyRoomId)),
                HttpStatus.OK);
    }

    @GetMapping("/last/{keyRoomId}")
    public ResponseEntity<ResponseDTO<RoomOwnerDTO>> doGetLast(@PathVariable final long keyRoomId ) {
        return this.entityService.doGetLastByKeyRoomId(keyRoomId).map(value->
                new ResponseEntity<>(
                        new ResponseDTO<>(value), HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(
                        new ResponseDTO<>(), HttpStatus.NOT_FOUND
                )
        );
    }
}
