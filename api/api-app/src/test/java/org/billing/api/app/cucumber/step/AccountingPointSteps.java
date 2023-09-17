package org.billing.api.app.cucumber.step;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.billing.api.app.cucumber.TestContext;
import org.billing.api.client.AccountingPointClient;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.billing.api.repository.AccountingPointDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AccountingPointSteps {
  @Autowired private AccountingPointClient client;
  @Autowired private AccountingPointDbService dbService;
  @Autowired private ObjectMapper mapper;

  @Given("Есть точка учета с ключом {string} в помещении {string}")
  public void saveAccountingPoint(String accountingPointId, String keyRoomId) {
    dbService.save(
        AccountingPoint.builder()
            .id(accountingPointId)
            .keyRoomId(keyRoomId)
            .serviceId(UUID.randomUUID().toString())
            .providerId(UUID.randomUUID().toString())
            .meterActive(false)
            .active(true)
            .build());
  }

  @When("Пользователь отправляет запрос создания точки учета c параметрами")
  public void sendCreateRequest(List<Map<String, String>> table) {
    TestContext.CONTEXT.setResponse(client.create(convertRequestFromDataTable(table)));
  }

  @When("Пользователь отправляет запрос обновления точки учета c параметрами")
  public void sendUpdateRequest(List<Map<String, String>> table) {
    TestContext.CONTEXT.setResponse(client.update(convertRequestFromDataTable(table)));
  }

  @When("Пользователь отправляет запрос получения списка точек учета {string}")
  public void getAllRequest(String keyRoomId) {
    TestContext.CONTEXT.setResponse(client.getAll(keyRoomId));
  }

  @Then("Созданная точка учета имеет значение полей")
  public void responseEqual(DataTable table) throws JsonProcessingException {

    final AccountingPoint body = getBodyResponse();

    final AccountingPoint expected = convertResponseFromDataTable(table);

    assertThat(body).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
  }

  @Then("У созданной точки учета заполненно свойство id")
  public void responseIdNotEmpty() throws JsonProcessingException {
    final AccountingPoint body = getBodyResponse();
    assertThat(body.getId()).isNotEmpty();
  }

  @Then("полученный список точек учета пуст")
  public void responseIsEmpty() {
    ResponseEntity<Collection<AccountingPoint>> result = TestContext.CONTEXT.getResponse();
    final Collection<AccountingPoint> body = result.getBody();
    Assertions.assertThat(body).isEmpty();
  }

  @Then("точка учета имеет значение полей")
  public void responseIsEqualTo(DataTable table) throws JsonProcessingException {

    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();

    String bodyStr = mapper.writeValueAsString(result.getBody());

    final AccountingPoint body = mapper.readValue(bodyStr, AccountingPoint.class);

    final AccountingPoint expected = convertResponseFromDataTable(table);

    assertThat(body)
        .satisfies(
            value ->
                assertThat(value)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expected))
        .satisfies(value -> assertThat(value.getId()).isNotEmpty());
  }

  private AccountingPoint getBodyResponse() throws JsonProcessingException {
    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();

    String bodyStr = mapper.writeValueAsString(result.getBody());

    return mapper.readValue(bodyStr, AccountingPoint.class);
  }

  private AccountingPoint convertRequestFromDataTable(List<Map<String, String>> values) {

    final AccountingPoint.AccountingPointBuilder builder = AccountingPoint.builder();

    values
        .get(0)
        .forEach(
            (key, value) -> {
              switch (key.toUpperCase()) {
                case "ACCOUNTINGPOINTID":
                  builder.id(value.replace("\"", ""));
                  break;
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
                  builder.meterActive(Boolean.parseBoolean(value));
                default:
                  break;
              }
            });

    return builder.build();
  }

  private AccountingPoint convertResponseFromDataTable(DataTable table) {
    final List<Map<String, String>> values = table.asMaps();

    final AccountingPoint.AccountingPointBuilder expectedBuilder = AccountingPoint.builder();

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
                  expectedBuilder.meterActive(Boolean.parseBoolean(value));
                  break;
                default:
                  break;
              }
            });

    return expectedBuilder.build();
  }
}
