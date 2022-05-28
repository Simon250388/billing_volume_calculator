package org.billing.api.app.cucumber.step;

import io.cucumber.java.en.Given;
import org.billing.api.repository.AccountServiceDbService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceSteps {

    @Autowired private AccountServiceDbService accountServiceDbService;

  @Given("есть услуга с ключом {string}")
  public void saveService(String serviceId) {
        accountServiceDbService.save(serviceId);
    }
}
