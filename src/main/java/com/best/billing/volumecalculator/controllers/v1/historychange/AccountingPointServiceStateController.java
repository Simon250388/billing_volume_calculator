package com.best.billing.volumecalculator.controllers.v1.historychange;

import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import com.best.billing.volumecalculator.services.helpers.ActiveAccountingPointDetails;
import com.best.billing.volumecalculator.services.historychange.AccountingPointServiceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("v1/service-state")
public class AccountingPointServiceStateController {
    private final AccountingPointServiceStateService entityService;
    private final ActiveAccountingPointDetails detailsService;

    @Autowired
    public AccountingPointServiceStateController(AccountingPointServiceStateService entityService, ActiveAccountingPointDetails detailsService) {
        this.entityService = entityService;
        this.detailsService = detailsService;
    }

    @GetMapping("/active-all/{keyRoomId}")
    public ResponseEntity<ResponseListDTO<ActiveAccountingPointDetailsDTO>> doGetAllActiveByKeyRoomId(@PathVariable @NotNull final long keyRoomId) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(detailsService.doGetAllActiveByKeyRoomId(keyRoomId)),
                HttpStatus.OK);
    }
}
