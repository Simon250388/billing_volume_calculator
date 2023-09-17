package org.billing.api.app.cucumber.step;

import static org.assertj.core.api.BDDAssertions.as;

import io.cucumber.java.en.Then;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.billing.api.app.cucumber.TestContext;
import org.springframework.http.ResponseEntity;

public class Assertion {
  @Then("ответ не содержит ошибок")
  public void requestIsSuccess() {
    final ResponseEntity<Object> response = TestContext.CONTEXT.getResponse();
    Assertions.assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
  }

  @Then("ответ содержит ошибку 404")
  public void badRequest() {
    final ResponseEntity<Object> response = TestContext.CONTEXT.getResponse();
    Assertions.assertThat(response.getStatusCode().is4xxClientError()).isTrue();
  }

  @Then("описание ошибки содержит ошибку валидации {string}")
  public void messageContainValidationError(String msg) {
    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();
    final Map<String, String> response = result.getBody();

    Assertions.assertThat(response)
            .containsKey("message")
            .extracting("message", as(InstanceOfAssertFactories.STRING))
            .containsIgnoringCase("VALIDATION ERROR");

    Assertions.assertThat(response)
            .containsKey("error")
            .extracting("error", as(InstanceOfAssertFactories.STRING))
            .containsIgnoringCase(msg);
  }

  @Then("описание ошибки содержит ошибку {string}")
  public void messageContainError(String msg) {
    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();
    final Map<String, String> response = result.getBody();

    Assertions.assertThat(response)
        .containsKey("error")
        .extracting("error", as(InstanceOfAssertFactories.STRING))
            .containsIgnoringCase(msg);
  }
}
