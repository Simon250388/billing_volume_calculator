package org.billing.accountingpoints.usecase;

import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceOperator {
  private final AccountingPointsStateService accountingPointsStateService;

  public boolean saveAccountingPointState(RoomAccountingPoints newState) {

    RoomAccountingPoints currentState =
        accountingPointsStateService.currentState(
            newState.getKeyRoomId(),
            newState.getPeriod().toInstant(),
            Optional.ofNullable(newState.getPeriodFact()).orElse(OffsetDateTime.MIN).toInstant());

    return false;
  }
}
