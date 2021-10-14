package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.UUID;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountingPointServiceState {

  @Autowired RequestBuilder requestBuilder;

  @Given(
      "у ключа помещения {uuid} на точке учета с ключом {uuid} подключена услуга с ключом {uuid}")
  public void setupKeyRoomService(
      final UUID keyRoomId, final UUID accountingPointId, final UUID serviceId) {
    System.out.printf(
        "у ключа помещения %s на точке учета с ключом %s подключена услуга с ключом %s%n",
        keyRoomId, accountingPointId, serviceId);
  }

  @When("я отправляю запрос подключения услуги с параметрами {uuid} {uuid} {uuid}")
  public void sendRequestConnectServiceToAccountingPointInKeyRoom(
      final UUID keyRoomId, final UUID accountingPointId, final UUID serviceId) {
    System.out.printf(
        "я отправляю запрос подключения услуги с параметрами keyRoomId=%s, accountingPointId=%s, serviceId=%s%n",
        keyRoomId, accountingPointId, serviceId);
  }

  @When("я отправляю запрос отключения услуги с параметрами {uuid} {uuid} {uuid}")
  public void sendRequestDisconnectServiceToAccountingPointInKeyRoom(
      final UUID keyRoomId, final UUID accountingPointId, final UUID serviceId) {
    System.out.printf(
        "я отправляю запрос отключения услуги с параметрами keyRoomId=%s, accountingPointId=%s, serviceId=%s%n",
        keyRoomId, accountingPointId, serviceId);
  }
}
