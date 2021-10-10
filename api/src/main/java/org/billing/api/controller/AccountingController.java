package org.billing.api.controller;

import lombok.RequiredArgsConstructor;
import org.billing.api.dto.AccountRequest;
import org.billing.api.dto.BillingServerResponse;
import org.billing.api.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingController {

  private final AccountingService accountingService;

  @PostMapping("/create")
  public Mono<ResponseEntity<BillingServerResponse>> createAccount(
      @RequestBody AccountRequest accountRequest) {
    return accountingService.createAccount(accountRequest).map(ResponseEntity::ok);
  }

  @PostMapping("/terminate")
  public Mono<ResponseEntity<BillingServerResponse>> terminateAccount(
      @RequestBody AccountRequest accountRequest) {
    return accountingService.terminateAccount(accountRequest).map(ResponseEntity::ok);
  }

  @PostMapping("/change")
  public Mono<ResponseEntity<BillingServerResponse>> changeAccountCustomer(
      @RequestBody AccountRequest accountRequest) {
    return accountingService.changeAccountCustomer(accountRequest).map(ResponseEntity::ok);
  }
}
