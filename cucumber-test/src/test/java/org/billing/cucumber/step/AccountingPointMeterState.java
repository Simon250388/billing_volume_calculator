package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AccountingPointMeterState {

  @Given(
      "у ключа помещения {long} на точке учета с ключом {long} подключен прибор учета с ключом {long}")
  public void setupKeyRoomAccountingPointMeterId(
      final Long keyRoomId, final Long accountingPointId, final Long meterId) {
    System.out.printf(
        "у ключа помещения %s на точке учета с ключом %s подключен прибор учета с ключом %s%n",
        keyRoomId, accountingPointId, meterId);
  }

  @When("я отправляю запрос подключения прибора учета с параметрами {long} {long} {long}")
  public void sendRequestConnectMeterToAccountingPointKeyRoom(
      final Long keyRoomId, final Long accountingPointId, final Long meterId) {
    System.out.printf(
        "я отправляю запрос подключения прибора учета с параметрами %s %s %s%n",
        keyRoomId, accountingPointId, meterId);
  }

  @When("я отправляю запрос вывода прибора учета с параметрами {long} {long} {long}")
  public void sendRequestDisconnectMeterToAccountingPointKeyRoom(
      final Long keyRoomId, final Long accountingPointId, final Long meterId) {
    System.out.printf(
        "я отправляю запрос вывода прибора учета с параметрами %s %s %s%n",
        keyRoomId, accountingPointId, meterId);
  }
}
