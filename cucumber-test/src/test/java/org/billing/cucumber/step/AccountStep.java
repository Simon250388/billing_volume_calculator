package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountStep {

  @Autowired RequestBuilder requestBuilder;

  @Given("последний номер лицевого счета равен {string}")
  public void setupLastAccountNumber(final String lastAccountNumber) {
    System.out.printf("last account number %s%n", lastAccountNumber);
  }

  @Given("в строении {long} помещении {long} есть открытый лицевой счет")
  public void keyRoomHasActiveAccount(final Long buildingId, final Long roomId) {
    System.out.printf("в строении %s помещении %s есть открытый лицевой счет%n", buildingId, roomId);
  }

  @Given(
      "в строении {long} помещении {long} есть открытый лицевой счет c номером договора {string}")
  public void keyRoomHasActiveWithAccountNumber(
      final Long buildingId, final Long roomId, final String accountNumber) {
    System.out.printf(
            "в строении %s помещении %s есть открытый лицевой счет c номером договора %s%n",
        buildingId, roomId, accountNumber);
  }

  @When("я отправляю запрос октрытия лицевого счета c параметрами {long} {long} {string}")
  public void createAccountRequest(
      final Long buildingId, final Long roomId, final String accountName) {
    System.out.printf(
            "Create request with params buildingId:%s roomId:%s, accountName:%s%n",
        buildingId, roomId, accountName);
  }

  @When("я отправляю запрос закрытия лицевого счета c параметрами {long} {long} {string}")
  public void closeAccountRequest(
          final Long buildingId, final Long roomId, final String accountName) {
    System.out.printf(
            "Create request with params buildingId:%s roomId:%s, accountName:%s%n",
            buildingId, roomId, accountName);
  }

  @Then("последний ответ не содержит ошибок")
  public void responseHasNoError() {
    // Assertions.assertEquals(HttpStatus.OK, requestBuilder.getResponse().statusCode());
  }

  @Then("я получения ответ с ошибкой {string}")
  public void responseHasError(final String errorMessage) {
    // Assertions.assertEquals(HttpStatus.OK, requestBuilder.getResponse().statusCode());
  }

  @Then("номер лицевого счета равен {string}")
  public void checkLastLsNumber(String lsNumber) {
    System.out.printf("check last account number %s%n", lsNumber);
    //        requestBuilder.getResponse();
  }
}
