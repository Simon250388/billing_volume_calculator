package org.billing.api.app.cucumber;

import io.cucumber.java.Before;
import org.billing.api.repository.AccountServiceDbService;
import org.billing.api.repository.AccountingPointDbService;
import org.billing.api.repository.KeyRoomDbService;
import org.billing.api.repository.ProviderDbService;
import org.springframework.beans.factory.annotation.Autowired;

public class Hook {
  @Autowired private AccountServiceDbService accountServiceDbService;
  @Autowired private KeyRoomDbService keyRoomRepository;
  @Autowired private AccountingPointDbService accountingPointDbService;
  @Autowired private ProviderDbService providerDbService;

  @Before
  public void clearDb() {
    accountServiceDbService.deleteAll();
    keyRoomRepository.deleteAll();
    accountingPointDbService.deleteAll();
    providerDbService.deleteAll();
  }
}
