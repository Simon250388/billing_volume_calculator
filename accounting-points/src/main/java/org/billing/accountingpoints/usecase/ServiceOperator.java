package org.billing.accountingpoints.usecase;

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
            newState.getKeyRoomId(), newState.getPeriod(), newState.getPeriodFact());

    return false;
  }
}
