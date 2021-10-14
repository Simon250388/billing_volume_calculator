package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.UUID;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountStep {

  @Autowired RequestBuilder requestBuilder;

  @Given("последний номер лицевого счета равен {string}")
  public void setupLastAccountNumber(final String lastAccountNumber) {
    System.out.printf("last account number %s%n", lastAccountNumber);
  }

  @Given("в ключе помещения {uuid} открыт лицевой счет на физ лицо к ключом {uuid}")
  public void keyRoomHasActiveAccount(final UUID keyRoomId, final UUID accountId) {
    System.out.printf(
        "в ключе помещения %s открыт лицевой счет c ключом %s%n", keyRoomId, accountId);
  }

  @Given(
      "в строении {uuid} помещении {uuid} есть открытый лицевой счет c номером договора {string}")
  public void keyRoomHasActiveWithAccountNumber(
      final UUID buildingId, final UUID roomId, final String accountNumber) {
    System.out.printf(
        "в строении %s помещении %s есть открытый лицевой счет c номером договора %s%n",
        buildingId, roomId, accountNumber);
  }

  @When("я отправляю запрос октрытия лицевого счета c параметрами {uuid} {uuid}")
  public void createAccountRequest(final UUID keyRoomId, final UUID customerId) {

    //    CreateAccountRequest request =
    //        CreateAccountRequest.builder().keyRoomId(keyRoomId).customerId(customerId).build();
    //
    //    requestBuilder.sendPostForEntity("accounting/create", request, AccountResponse.class);

    System.out.printf(
        "я отправляю запрос октрытия лицевого счета c параметрами keyRoomId=%s, customerId=%s %n",
        keyRoomId, customerId);
  }

  @When("я отправляю запрос закрытия лицевого счета c параметрами {uuid} {uuid}")
  public void closeAccountRequest(final UUID keyRoomId, final UUID customerId) {
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
