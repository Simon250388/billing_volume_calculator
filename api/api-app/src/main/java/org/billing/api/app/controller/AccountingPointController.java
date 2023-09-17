package org.billing.api.app.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.accountingPoint.AccountingPointUseCaseService;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounting-point")
@RequiredArgsConstructor
public class AccountingPointController {
  private final AccountingPointUseCaseService accountingPointUseCaseService;

  @GetMapping("/{keyRoomId}")
  public ResponseEntity<Collection<AccountingPoint>> list(@PathVariable String keyRoomId) {
    return accountingPointUseCaseService.list(keyRoomId);
  }

  @PostMapping
  public ResponseEntity<AccountingPoint> create(
      @RequestBody @Validated(AccountingPoint.AccountingPointCreateValidationGroup.class)
      AccountingPoint request) {
    return accountingPointUseCaseService.create(request);
  }

  @PatchMapping
  public ResponseEntity<AccountingPoint> update(
      @RequestBody @Validated(AccountingPoint.AccountingPointUpdateValidationGroup.class) AccountingPoint request) {
    return accountingPointUseCaseService.update(request);
  }

  @DeleteMapping("{accountingPointId}")
  public ResponseEntity<Void> delete(
      @PathVariable(name = "accountingPointId") String accountingPointId) {
    return accountingPointUseCaseService.delete(accountingPointId);
  }
}
