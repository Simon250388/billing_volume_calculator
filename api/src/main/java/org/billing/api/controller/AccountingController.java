package org.billing.api.controller;

import org.billing.api.dto.CreateAccountRequest;
import org.billing.api.dto.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounting")
@Validated
public class AccountingController {

  @PostMapping("/create")
  public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
    return new ResponseEntity<>(AccountResponse.builder().build(), HttpStatus.OK);
  }

  @PostMapping("/terminate")
  public ResponseEntity<AccountResponse> terminateAccount(
      @RequestBody CreateAccountRequest createAccountRequest) {
    return new ResponseEntity<>(AccountResponse.builder().build(), HttpStatus.OK);
  }

  @PostMapping("/change")
  public ResponseEntity<AccountResponse> changeAccountCustomer(@RequestBody CreateAccountRequest accountRequest) {
    return new ResponseEntity<>(AccountResponse.builder().build(), HttpStatus.OK);
  }
}
