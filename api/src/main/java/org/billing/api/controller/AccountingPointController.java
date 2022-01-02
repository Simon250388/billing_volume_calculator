package org.billing.api.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.billing.api.request.KeyRoomStateDto;
import org.billing.api.service.AccountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounting-point")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointController {
  private final AccountingPointService accountingPointService;

  @GetMapping("/{keyRoomId}")
  public ResponseEntity<KeyRoomStateDto> getAll(
      @PathVariable String keyRoomIdStr) {

    final UUID keyRoomID = UUID.fromString(keyRoomIdStr);

    return ResponseEntity.ok(accountingPointService.getAll(keyRoomID));
  }

  @PostMapping
  public ResponseEntity<KeyRoomStateDto> save(
      @RequestBody KeyRoomStateDto currentState) {

    accountingPointService.save(currentState);

    return ResponseEntity.ok(currentState);
  }
}
