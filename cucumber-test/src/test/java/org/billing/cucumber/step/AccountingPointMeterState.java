package org.billing.cucumber.step;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.UUID;

public class AccountingPointMeterState {



  @Given(
      "у ключа помещения {uuid} на точке учета с ключом {uuid} подключен прибор учета с ключом {uuid}")
  public void setupKeyRoomAccountingPointMeterId(
      final UUID keyRoomId, final UUID accountingPointId, final UUID meterId) {
    System.out.printf(
        "у ключа помещения %s на точке учета с ключом %s подключен прибор учета с ключом %s%n",
        keyRoomId, accountingPointId, meterId);
  }

  @When("я отправляю запрос подключения прибора учета с параметрами {uuid} {uuid} {uuid}")
  public void sendRequestConnectMeterToAccountingPointKeyRoom(
      final UUID keyRoomId, final UUID accountingPointId, final UUID meterId) {
    System.out.printf(
        "я отправляю запрос подключения прибора учета с параметрами %s %s %s%n",
        keyRoomId, accountingPointId, meterId);
  }

  @When("я отправляю запрос вывода прибора учета с параметрами {uuid} {uuid} {uuid}")
  public void sendRequestDisconnectMeterToAccountingPointKeyRoom(
      final UUID keyRoomId, final UUID accountingPointId, final UUID meterId) {
    System.out.printf(
        "я отправляю запрос вывода прибора учета с параметрами %s %s %s%n",
        keyRoomId, accountingPointId, meterId);
  }
}
