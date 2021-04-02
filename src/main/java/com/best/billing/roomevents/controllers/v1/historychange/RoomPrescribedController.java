package com.best.billing.roomevents.controllers.v1.historychange;

import com.best.billing.base.dto.ResponseDTO;
import com.best.billing.base.dto.ResponseListDTO;
import com.best.billing.roomevents.dto.historychange.RoomPrescribedDTO;
import com.best.billing.roomevents.services.historychange.RoomPrescribedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/room-prescribed")
public class RoomPrescribedController {

    private final RoomPrescribedService entityService;

    @Autowired
    public RoomPrescribedController(RoomPrescribedService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/history/{keyRoomId}")
    public ResponseEntity<ResponseListDTO<RoomPrescribedDTO>> doGetHistory(@PathVariable final long keyRoomId ) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(this.entityService.doGetHistoryByKeyRoomId(keyRoomId)),
                HttpStatus.OK);
    }

    @GetMapping("/last/{keyRoomId}")
    public ResponseEntity<ResponseDTO<RoomPrescribedDTO>> doGetLast(@PathVariable final long keyRoomId ) {
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
