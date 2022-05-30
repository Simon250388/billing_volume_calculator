package org.billing.api.app.cucumber.step;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.billing.api.app.cucumber.TestContext;
import org.billing.api.client.KeyRoomClient;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.billing.api.repository.KeyRoomDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class KeyRoomSteps {
  @Autowired private KeyRoomClient keyRoomClient;
  @Autowired private ObjectMapper mapper;
  @Autowired private KeyRoomDbService keyRoomRepository;

  @Given("Есть помещение с параметрами")
  public void saveKeyRoom(List<Map<String, String>> table) {
    keyRoomRepository.save(table.get(0).get("keyRoomId"), convertRequestFromDataTable(table), "");
  }

  @Given("Есть помещение с ключом {string}")
  public void saveKeyRoom(String keyRoomId) {
    keyRoomRepository.save(keyRoomId, KeyRoomRequest.builder()
                    .roomTypeId(1L)
                    .address("")
                    .countResident(1L)
                    .countOwner(1L)
                    .countSubscribed(1L)
                    .square(BigDecimal.ZERO)
            .build(), "");
  }
  @When("Пользователь отправляет запрос получения списка помещений")
  public void getAllRequest() {
    TestContext.CONTEXT.setResponse(keyRoomClient.getAllKeyRooms());
  }

  @When("Пользователь отправляет запрос создания помещений c параметрами")
  public void sendCreateKeyRoomMessage(List<Map<String, String>> table) {
    TestContext.CONTEXT.setResponse(keyRoomClient.createKeyRoom(convertRequestFromDataTable(table)));
  }

  @When("Пользователь отправляет запрос обновление помещения с параметрами")
  public void sendUpdateRequest(List<Map<String, String>> params) {
    TestContext.CONTEXT.setResponse(
        keyRoomClient.updateKeyRoom(params.get(0).get("keyRoomId"), convertRequestFromDataTable(params)));
  }

  @Then("полученный список помещений пуст")
  public void responseIsEmpty() {
    ResponseEntity<Collection<KeyRoomResponse>> result = TestContext.CONTEXT.getResponse();
    final Collection<KeyRoomResponse> body = result.getBody();
    Assertions.assertThat(body).isEmpty();
  }

  @Then("созданное помещение имеет значение полей")
  public void responseIsEmpty(DataTable table) throws JsonProcessingException {

    ResponseEntity<Map<String, String>> result = TestContext.CONTEXT.getResponse();

    String bodyStr = mapper.writeValueAsString(result.getBody());

    final KeyRoomResponse body = mapper.readValue(bodyStr, KeyRoomResponse.class);

    final KeyRoomResponse expected = convertResponseFromDataTable(table);

    assertThat(body)
        .satisfies(
            value ->
                assertThat(value)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expected))
        .satisfies(value -> assertThat(value.getId()).isNotEmpty());
  }

  private KeyRoomRequest convertRequestFromDataTable(List<Map<String, String>> values) {

    final KeyRoomRequest.KeyRoomRequestBuilder builder = KeyRoomRequest.builder();

    values
        .get(0)
        .forEach(
            (key, value) -> {
              switch (key.toUpperCase()) {
                case "ADDRESS":
                  builder.address(value);
                  break;
                case "ROOMTYPEID":
                  builder.roomTypeId(Long.parseLong(value));
                  break;
                case "COUNTRESIDENT":
                  builder.countResident(Long.parseLong(value));
                  break;
                case "COUNTSUBSCRIBED":
                  builder.countSubscribed(Long.parseLong(value));
                  break;
                case "COUNTOWNER":
                  builder.countOwner(Long.parseLong(value));
                  break;
                case "SQUARE":
                  builder.square(BigDecimal.valueOf(Double.parseDouble(value)));
                  break;
                default:
                  break;
              }
            });

    return builder.build();
  }

  private KeyRoomResponse convertResponseFromDataTable(DataTable table) {
    final List<Map<String, String>> values = table.asMaps();

    final KeyRoomResponse.KeyRoomResponseBuilder expectedBuilder = KeyRoomResponse.builder();

    values
        .get(0)
        .forEach(
            (key, value) -> {
              switch (key.toUpperCase()) {
                case "ADDRESS":
                  expectedBuilder.address(value);
                  break;
                case "ROOMTYPEID":
                  expectedBuilder.roomTypeId(Long.parseLong(value));
                  break;
                case "COUNTRESIDENT":
                  expectedBuilder.countResident(Long.parseLong(value));
                  break;
                case "COUNTSUBSCRIBED":
                  expectedBuilder.countSubscribed(Long.parseLong(value));
                  break;
                case "COUNTOWNER":
                  expectedBuilder.countOwner(Long.parseLong(value));
                  break;
                case "SQUARE":
                  expectedBuilder.square(BigDecimal.valueOf(Double.parseDouble(value)));
                  break;
                default:
                  break;
              }
            });

    return expectedBuilder.build();
  }

}
