package com.best.billing.volumecalculator.controllers.v1.historychange;

import com.best.billing.volumecalculator.dto.ResponseDTO;
import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.historychange.RoomResidentdDTO;
import com.best.billing.volumecalculator.services.historychange.RoomResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/room-resident")
public class RoomResidentController {
    private final RoomResidentService entityService;

    @Autowired
    public RoomResidentController(RoomResidentService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/history/{keyRoomId}")
    public ResponseEntity<ResponseListDTO<RoomResidentdDTO>> doGetHistory(@PathVariable final long keyRoomId ) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(this.entityService.doGetHistoryByKeyRoomId(keyRoomId)),
                HttpStatus.OK);
    }

    @GetMapping("/last/{keyRoomId}")
    public ResponseEntity<ResponseDTO<RoomResidentdDTO>> doGetLast(@PathVariable final long keyRoomId ) {
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
