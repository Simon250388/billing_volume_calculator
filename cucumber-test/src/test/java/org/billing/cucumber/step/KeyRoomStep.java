package org.billing.cucumber.step;

import io.cucumber.java.en.Given;

public class KeyRoomStep {

  @Given("есть ключ помещения с параметрами {long} {long}")
  public void createKeyRoom(final Long buildingKey, final Long roomKey) {
    System.out.printf("есть ключ помещения с параметрами %s %s%n", buildingKey, roomKey);
  }
}
