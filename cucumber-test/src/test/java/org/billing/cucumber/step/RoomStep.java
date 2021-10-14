package org.billing.cucumber.step;

import io.cucumber.java.en.When;
import java.util.UUID;

public class RoomStep {

  @When("я отправляю запрос изменения значения площади с параметрами {uuid} {long} {long}")
  public void sendRequestSetSquareTypeValue(
      final UUID keyRoomId, final Long squareType, final Long squareValue) {}

  @When("я отправляю запрос изменения количества прописанных с параметрами {uuid} {long}")
  public void sendRequestSetPrescribedCount(final UUID keyRoomId, final Long prescribedCount) {}

  @When("я отправляю запрос изменения количества собственников с параметрами {uuid} {long}")
  public void sendRequestSetOwnerCount(final UUID keyRoomId, final Long ownerCount) {}

  @When("я отправляю запрос изменения количества проживающих с параметрами {uuid} {long}")
  public void sendRequestSetResidentCount(final UUID keyRoomId, final Long residentCount) {}
}
