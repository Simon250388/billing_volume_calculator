package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountingPointServiceState {

  @Autowired RequestBuilder requestBuilder;

  @Given(
      "у ключа помещения {long} на точке учета с ключом {long} подключена услуга с ключом {long}")
  public void setupKeyRoomService(
      final Long keyRoomId, final Long accountingPointId, final Long serviceId) {
    System.out.printf(
        "у ключа помещения %s на точке учета с ключом %s подключена услуга с ключом %s%n",
        keyRoomId, accountingPointId, serviceId);
  }

  @When("я отправляю запрос подключения услуги с параметрами {long} {long} {long}")
  public void sendRequestConnectServiceToAccountingPointInKeyRoom(
      final Long keyRoomId, final Long accountingPointId, final Long serviceId) {
    System.out.printf(
        "я отправляю запрос подключения услуги с параметрами keyRoomId=%s, accountingPointId=%s, serviceId=%s%n",
        keyRoomId, accountingPointId, serviceId);
  }
}
