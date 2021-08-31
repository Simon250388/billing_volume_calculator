package org.billing.accountingpoints.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOperator {
  private final AccountingPointsStateService accountingPointsStateService;

  @Autowired
  public ServiceOperator(AccountingPointsStateService accountingPointsStateService) {
    this.accountingPointsStateService = accountingPointsStateService;
  }

  public boolean saveAccountingPointState(RoomAccountingPoints newState) {

    RoomAccountingPoints currentState =
        accountingPointsStateService.currentState(
            newState.getKeyRoomId(), newState.getPeriod(), newState.getPeriodFact());

    return false;
  }
}
