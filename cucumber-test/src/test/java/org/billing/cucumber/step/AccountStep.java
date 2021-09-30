package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.billing.api.dto.AccountResponse;
import org.billing.api.dto.CreateAccountRequest;
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
    System.out.printf(
        "в строении %s помещении %s есть открытый лицевой счет%n", buildingId, roomId);
  }

  @Given(
      "в строении {long} помещении {long} есть открытый лицевой счет c номером договора {string}")
  public void keyRoomHasActiveWithAccountNumber(
      final Long buildingId, final Long roomId, final String accountNumber) {
    System.out.printf(
        "в строении %s помещении %s есть открытый лицевой счет c номером договора %s%n",
        buildingId, roomId, accountNumber);
  }

  @When("я отправляю запрос октрытия лицевого счета c параметрами {long} {long}")
  public void createAccountRequest(final Long keyRoomId, final Long customerId) {

    CreateAccountRequest request =
        CreateAccountRequest.builder().keyRoomId(keyRoomId).customerId(customerId).build();

    requestBuilder.sendPostForEntity("accounting/create", request, AccountResponse.class);

    System.out.printf(
        "Create request with params keyRoomId:%s customerId:%s%n", keyRoomId, customerId);
  }

  @When("я отправляю запрос закрытия лицевого счета c параметрами {long} {long}")
  public void closeAccountRequest(final Long keyRoomId, final Long customerId) {
    System.out.printf(
        "Create request with params keyRoomId:%s customerId:%s%n", keyRoomId, customerId);
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
