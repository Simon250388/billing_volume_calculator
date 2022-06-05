package org.billing.api.app.controller;

import java.util.Collection;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.billing.api.app.useCase.accountingPoint.AccountingPointUseCaseService;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounting-point")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointController {
  private final AccountingPointUseCaseService accountingPointUseCaseService;

  @GetMapping("/{keyRoomId}")
  public ResponseEntity<Collection<AccountingPointResponse>> list(@PathVariable String keyRoomId) {
    return accountingPointUseCaseService.list(keyRoomId);
  }

  @PostMapping
  public ResponseEntity<AccountingPointResponse> create(
      @RequestBody @Validated(AccountingPointRequest.AccountingPointCreateValidationGroup.class)
          AccountingPointRequest request) {
    return accountingPointUseCaseService.save(request);
  }

  @PutMapping("{accountingPointId}")
  public ResponseEntity<AccountingPointResponse> update(
          @PathVariable String accountingPointId,
      @RequestBody @Validated AccountingPointRequest request) {
    return accountingPointUseCaseService.update(accountingPointId, request);
  }

  @DeleteMapping("{accountingPointId}}")
  public ResponseEntity<Void> delete(@PathVariable(name = "accountingPointId") String accountingPointId) {
    return accountingPointUseCaseService.delete(accountingPointId);
  }
}
