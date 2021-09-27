package org.billing.api.controller;

import org.billing.api.dto.AccountRequest;
import org.billing.api.dto.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key-room")
@Validated
public class KeyRoomController {

    @GetMapping("{keyRoomId}/details")
    public ResponseEntity<AccountResponse> getDetails(@PathVariable Long keyRoomId) {
        return new ResponseEntity(AccountResponse.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createKeyRoom(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity(AccountResponse.builder().build(), HttpStatus.OK);
    }

}
