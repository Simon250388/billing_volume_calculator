package org.billing.api.app.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.accountingPoint.AccountingPointRequest;
import org.billing.api.app.useCase.accountingPoint.AccountingPointResponse;
import org.billing.api.app.useCase.accountingPoint.AccountingPointUseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounting-point")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointController {
    private final AccountingPointUseCaseService accountingPointUseCaseService;

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<Collection<AccountingPointResponse>> list(@PathVariable String keyRoomId) {
        return accountingPointUseCaseService.list(keyRoomId);
    }

    @PostMapping
    public ResponseEntity<AccountingPointResponse> save(@RequestBody AccountingPointRequest request) {
        return accountingPointUseCaseService.save(request);
    }

    @PutMapping
    public ResponseEntity<AccountingPointResponse> update(@RequestBody AccountingPointRequest request) {
        return accountingPointUseCaseService.update(request);
    }

    @DeleteMapping("/{keyRoomId}/{accountingPointId}}")
    public ResponseEntity<Void> delete(@PathVariable(name = "keyRoomId") String keyRoomId,
                                       @PathVariable(name = "accountingPointId") String accountingPointId) {
        return accountingPointUseCaseService.delete(keyRoomId, accountingPointId);
    }


}
