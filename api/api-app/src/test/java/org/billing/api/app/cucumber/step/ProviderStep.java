package org.billing.api.app.cucumber.step;

import io.cucumber.java.en.Given;
import org.billing.api.repository.ProviderDbService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProviderStep {

  @Autowired private ProviderDbService providerDbService;

  @Given("есть поставщик с ключом {string}")
  public void saveService(String providerId) {
    providerDbService.save(providerId);
  }
}
