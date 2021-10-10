package org.billing.accounts.controller;

import java.util.UUID;
import org.billing.accounts.dto.AccountRequest;
import org.billing.accounts.dto.BillingServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @PostMapping("/create")
  public ResponseEntity<BillingServerResponse> createAccount(
      @RequestBody AccountRequest accountRequest) {
    return ResponseEntity.ok(BillingServerResponse.builder().queryId(UUID.randomUUID()).build());
  }

  @PostMapping("/terminate")
  public ResponseEntity<BillingServerResponse> terminateAccount(
      @RequestBody AccountRequest accountRequest) {
    return ResponseEntity.ok(BillingServerResponse.builder().queryId(UUID.randomUUID()).build());
  }

  @PostMapping("/change")
  public ResponseEntity<BillingServerResponse> changeAccountCustomer(
      @RequestBody AccountRequest accountRequest) {
    return ResponseEntity.ok(BillingServerResponse.builder().queryId(UUID.randomUUID()).build());
  }
}
