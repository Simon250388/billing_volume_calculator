package org.billing.cucumber.step;

import io.cucumber.java.en.When;

public class RoomStep {

  @When("я отправляю запрос изменения значения площади с параметрами {long} {long} {long}")
  public void sendRequestSetSquareTypeValue(
      final Long keyRoomId, final Long squareType, final long squareValue) {}

  @When("я отправляю запрос изменения количества прописанных с параметрами {long} {long}")
  public void sendRequestSetPrescribedCount(final Long keyRoomId, final Long prescribedCount) {}

  @When("я отправляю запрос изменения количества собственников с параметрами {long} {long}")
  public void sendRequestSetOwnerCount(final Long keyRoomId, final Long ownerCount) {}

  @When("я отправляю запрос изменения количества проживающих с параметрами {long} {long}")
  public void sendRequestSetResidentCount(final Long keyRoomId, final Long residentCount) {}
}
