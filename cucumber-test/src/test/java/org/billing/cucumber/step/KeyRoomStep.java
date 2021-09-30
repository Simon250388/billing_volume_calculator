package org.billing.cucumber.step;

import io.cucumber.java.en.Given;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class KeyRoomStep {
  @Autowired
  RequestBuilder requestBuilder;

  @Given("есть ключ помещения {long}")
  public void createKeyRoom(final Long keyRoomId) {
    System.out.printf("есть ключ помещения %s %n", keyRoomId);
  }
}
