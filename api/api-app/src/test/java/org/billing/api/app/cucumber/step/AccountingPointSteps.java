package org.billing.api.app.cucumber.step;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.billing.api.app.cucumber.TestContext;
import org.billing.api.client.AccountingPointClient;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AccountingPointSteps {

  @Autowired private AccountingPointClient client;

  @Autowired private ObjectMapper mapper;

  @When("Пользователь отправляет запрос создания точки учета c параметрами")
  public void sendCreateRequest(List<Map<String, String>> table) {
    TestContext.CONTEXT.setResponse(
        client.create(convertRequestFromDataTable(table)));
  }

  @When("Пользователь отправляет запрос получения списка точек учета {string}")
  public void getAllRequest(String keyRoomId) {
    TestContext.CONTEXT.setResponse(client.getAll(keyRoomId));
  }

  @Then("Созданная точка учета имеет значение полей")
  public void responseEqual(DataTable table) throws JsonProcessingException {

    final AccountingPointResponse body = getBodyResponse();

    final AccountingPointResponse expected = convertResponseFromDataTable(table);

    assertThat(body).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
  }

  @Then("У созданной точки учета заполненно свойство id")
  public void responseIdNotEmpty() throws JsonProcessingException {
    final AccountingPointResponse body = getBodyResponse();
    assertThat(body.getId()).isNotEmpty();
  }

  @Then("полученный список точек учета пуст")
  public void responseIsEmpty() {
    ResponseEntity<Collection<AccountingPointResponse>> result = TestContext.CONTEXT.getResponse();
    final Collection<AccountingPointResponse> body = result.getBody();
    Assertions.assertThat(body).isEmpty();
  }

  private AccountingPointResponse getBodyResponse() throws JsonProcessingException {
    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();

    String bodyStr = mapper.writeValueAsString(result.getBody());

    return mapper.readValue(bodyStr, AccountingPointResponse.class);
  }

  private AccountingPointRequest convertRequestFromDataTable(List<Map<String, String>> values) {

    final AccountingPointRequest.AccountingPointRequestBuilder builder =
        AccountingPointRequest.builder();

    values
        .get(0)
        .forEach(
            (key, value) -> {
              switch (key.toUpperCase()) {
                case "KEYROOMID":
                  builder.keyRoomId(value.replace("\"", ""));
                  break;
                case "SERVICEID":
                  builder.serviceId(value.replace("\"", ""));
                  break;
                case "PROVIDERID":
                  builder.providerId(value.replace("\"", ""));
                  break;
                case "ACTIVE":
                  builder.active(Boolean.parseBoolean(value));
                  break;
                case "METERISACTIVE":
                  builder.meterIsActive(Boolean.parseBoolean(value));
                default:
                  break;
              }
            });

    return builder.build();
  }

  private AccountingPointResponse convertResponseFromDataTable(DataTable table) {
    final List<Map<String, String>> values = table.asMaps();

    final AccountingPointResponse.AccountingPointResponseBuilder expectedBuilder =
        AccountingPointResponse.builder();

    values
        .get(0)
        .forEach(
            (key, value) -> {
              switch (key.toUpperCase()) {
                case "KEYROOMID":
                  expectedBuilder.keyRoomId(value.replace("\"", ""));
                  break;
                case "SERVICEID":
                  expectedBuilder.serviceId(value.replace("\"", ""));
                  break;
                case "PROVIDERID":
                  expectedBuilder.providerId(value.replace("\"", ""));
                  break;
                case "ACTIVE":
                  expectedBuilder.active(Boolean.parseBoolean(value));
                case "METERISACTIVE":
                  expectedBuilder.meterIsActive(Boolean.parseBoolean(value));
                  break;
                default:
                  break;
              }
            });

    return expectedBuilder.build();
  }
}
