package org.billing.cucumber.step;

import io.cucumber.java.en.When;
import java.util.UUID;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class MeterValueStep {

  @Autowired RequestBuilder requestBuilder;

  @When("я отправляю запрос передачи показаний приборов учета {uuid} {uuid} {long}")
  public void setupLastAccountNumber(
          final UUID keyRoomId, final UUID meterId, final Long meterValue) {
    System.out.printf(
        "я отправляю запрос передачи показаний приборов учета c параметрами keyRoomId=%s, meterId=%s, meterValue=%s%n",
        keyRoomId, meterId, meterValue);
  }
}
