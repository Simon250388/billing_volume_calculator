package org.billing.api.app.service;

import java.util.Collection;
import java.util.Collections;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.billing.api.model.exception.AccountingPointNotFoundException;
import org.billing.api.repository.AccountingPointDbService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointService {

  private final AccountingPointDbService dbService;

  public Collection<AccountingPoint> getAll(@NonNull final String keyRoomID) {
    return Collections.emptyList();
  }

  public void accountingPointExistOrElseThrow(@NonNull final String accountingPointId) {
    dbService
        .findById(accountingPointId)
        .orElseThrow(() -> new AccountingPointNotFoundException(accountingPointId));
  }

  public AccountingPoint save(AccountingPoint request) {
    return dbService.save(request);
  }

  public void delete(String accountingPointId) {
    dbService.deleteById(accountingPointId);
  }
}
