package com.best.billing.volumecalculator.controllers.v1.historychange;

import com.best.billing.volumecalculator.dto.ResponseDTO;
import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.volumecalculator.services.historychange.AccountingPointMeterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/accounting-point-meter-state")
public class AccountingPointMeterStateController {
    private final AccountingPointMeterStateService entityService;

    @Autowired
    public AccountingPointMeterStateController(AccountingPointMeterStateService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/history/{accountingPointKeyRoomService}/{meterId}")
    public ResponseEntity<ResponseListDTO<AccountingPointMeterStateDTO>> doGetHistory(@PathVariable final long accountingPointKeyRoomServiceId, @PathVariable final long meterId) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(this.entityService.doGetHistoryByAccountingPointKeyRoomService(accountingPointKeyRoomServiceId, meterId)),
                HttpStatus.OK);
    }

    @GetMapping("/last/{accountingPointKeyRoomServiceId}/{meterId}")
    public ResponseEntity<ResponseDTO<AccountingPointMeterStateDTO>> doGetLast(@PathVariable final long accountingPointKeyRoomServiceId, @PathVariable final long meterId) {

        return this.entityService.doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(accountingPointKeyRoomServiceId, meterId).map(value ->
                new ResponseEntity<>(
                        new ResponseDTO<>(value), HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(
                        new ResponseDTO<>(), HttpStatus.NOT_FOUND
                )
        );
    }
}
