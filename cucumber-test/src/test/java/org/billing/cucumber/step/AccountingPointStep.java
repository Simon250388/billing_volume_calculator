package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import java.util.UUID;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountingPointStep {

  @Autowired RequestBuilder requestBuilder;

  @Given("есть точка учета с ключом {uuid}")
  public void setupLastAccountNumber(final UUID accountingPointId) {
    System.out.printf("есть точка учета с ключом %s%n", accountingPointId);
  }
}
