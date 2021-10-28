package org.billing.accountingpoints.controller.v1;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.billing.accountingpoints.request.RoomAccountingPointsRequest;
import org.billing.accountingpoints.usecase.AccountingPointsStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/current-state")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentStateController {
  private final AccountingPointsStateService currentStateService;

    @GetMapping("/{keyRoomId}")
  public ResponseEntity<RoomAccountingPointsRequest> getCurrentState(@PathVariable UUID keyRoomId) {
    return ResponseEntity.ok(currentStateService.currentState(keyRoomId));
  }
}
