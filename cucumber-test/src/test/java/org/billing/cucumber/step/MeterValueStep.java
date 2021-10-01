package org.billing.cucumber.step;

import io.cucumber.java.en.When;
import org.billing.cucumber.service.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class MeterValueStep {

  @Autowired RequestBuilder requestBuilder;

  @When("я отправляю запрос передачи показаний приборов учета {long} {long} {long}")
  public void setupLastAccountNumber(
      final Long keyRoomId, final Long meterId, final Long meterValue) {
    System.out.printf(
        "я отправляю запрос передачи показаний приборов учета c параметрами keyRoomId=%s, meterId=%s, meterValue=%s%n",
        keyRoomId, meterId, meterValue);
  }
}
