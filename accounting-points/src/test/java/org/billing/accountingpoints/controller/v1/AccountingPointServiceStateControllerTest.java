package org.billing.accountingpoints.controller.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.GsonBuilder;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.billing.accountingpoints.configuration.QueueConfiguration;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.service.GuidGenerator;
import org.billing.accountingpoints.service.ServiceStateService;
import org.billing.accountingpoints.usecase.AccountingPointsStateService;
import org.billing.accountingpoints.utils.FileContentUtls;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountingPointServiceStateController.class)
@Import({AccountingPointServiceStateControllerTest.TestConfig.class, QueueConfiguration.class})
@ActiveProfiles("test")
@Tag("medium")
class AccountingPointServiceStateControllerTest {

  @TestConfiguration
  public static class TestConfig {

    public class SimpleGuidGenerator implements GuidGenerator {

      @Override
      public UUID randomUUID() {
        return UUID.fromString("ce102007-893c-4ab0-9179-3c54d326ea1d");
      }
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
      return Mockito.mock(RabbitTemplate.class);
    }

    @Bean
    GuidGenerator billingGuidGenerator() {
      return new SimpleGuidGenerator();
    }

    @Bean
    Clock clock() {
      return Clock.fixed(Instant.parse("2022-01-01T00:00:00.00Z"), ZoneId.of("UTC"));
    }

    @Bean
    ServiceStateService serviceStateService() {
      return Mockito.mock(ServiceStateService.class);
    }
  }

  @Autowired MockMvc mockMvc;

  @Autowired ServiceStateService service;

  @Test
  void getActive() throws Exception {
    final Set<AccountingPointServiceStateDto> models =
        Set.of(
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(true)
                .period(Instant.parse("2021-10-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build());

    when(service.currentActive(any(UUID.class))).thenReturn(models);

    List<Map<String, Object>> contentExpected =
        List.of(
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", true,
                "period", "2021-10-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"));

    final String jsonModel = new GsonBuilder().create().toJson(contentExpected);

    mockMvc
        .perform(
            get("/v1/accounting-point-state/active/e3c08228-9011-4820-9d2a-02d0913acf18")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonModel));
  }

  @Test
  void getHistory() throws Exception {
    final Set<AccountingPointServiceStateDto> models =
        Set.of(
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(true)
                .period(Instant.parse("2021-10-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build(),
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(false)
                .period(Instant.parse("2021-11-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build());

    when(service.getHistory(any(UUID.class))).thenReturn(models);

    List<Map<String, Object>> contentExpected =
        List.of(
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", true,
                "period", "2021-10-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"),
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", false,
                "period", "2021-11-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"));

    final String jsonModel = new GsonBuilder().create().toJson(contentExpected);

    mockMvc
        .perform(
            get("/v1/accounting-point-state/history/e3c08228-9011-4820-9d2a-02d0913acf18")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonModel));
  }

  @Test
  void getHistoryWithOnlyFrom() throws Exception {
    final Set<AccountingPointServiceStateDto> models =
        Set.of(
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(true)
                .period(Instant.parse("2021-10-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build(),
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(false)
                .period(Instant.parse("2021-11-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build());

    when(service.getHistory(any(UUID.class), any(Instant.class))).thenReturn(models);

    List<Map<String, Object>> contentExpected =
        List.of(
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", true,
                "period", "2021-10-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"),
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", false,
                "period", "2021-11-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"));

    final String jsonModel = new GsonBuilder().create().toJson(contentExpected);

    mockMvc
        .perform(
            get("/v1/accounting-point-state/history/e3c08228-9011-4820-9d2a-02d0913acf18?from=2021-10-01T00:00:00Z")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonModel));
  }

  @Test
  void getHistoryWithFromAndTo() throws Exception {
    final Set<AccountingPointServiceStateDto> models =
        Set.of(
            AccountingPointServiceStateDto.builder()
                .accountPointId(UUID.fromString("e7dd6279-e1dc-4ea6-a378-1803372a26d8"))
                .active(true)
                .period(Instant.parse("2021-10-01T00:00:00.00Z"))
                .keyRoomID(UUID.fromString("d76929e2-97eb-4abb-970c-f82e72b490ff"))
                .serviceId(UUID.fromString("46029699-4eff-4766-8bd5-5483f2b9c67b"))
                .build());

    when(service.getHistory(any(UUID.class), any(Instant.class), any(Instant.class)))
        .thenReturn(models);

    List<Map<String, Object>> contentExpected =
        List.of(
            Map.of(
                "accountPointId", "e7dd6279-e1dc-4ea6-a378-1803372a26d8",
                "active", true,
                "period", "2021-10-01T00:00:00Z",
                "keyRoomID", "d76929e2-97eb-4abb-970c-f82e72b490ff",
                "serviceId", "46029699-4eff-4766-8bd5-5483f2b9c67b"));

    final String jsonModel = new GsonBuilder().create().toJson(contentExpected);

    mockMvc
        .perform(
            get("/v1/accounting-point-state/history/e3c08228-9011-4820-9d2a-02d0913acf18"
                    + "?from=2021-10-01T00:00:00Z&to=2022-10-01T00:00:00Z")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonModel));
  }

  @Test
  void getHistoryWithOnlyTo() throws Exception {

    mockMvc
        .perform(
            get("/v1/accounting-point-state/history/e3c08228-9011-4820-9d2a-02d0913acf18?to=2021-10-01T00:00:00Z")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(content().json("{\"message\": \"param from is empty\", \"errors\": [] }"));
  }

  @Test
  void getActiveWhenNotFound() throws Exception {
    mockMvc
        .perform(
            get("/v1/accounting-point-state/active/aaaaaaaa")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }

  @Test
  void connect() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect.json")))
        .andExpect(status().isOk())
        .andExpect(content().json("{ queryId:\"ce102007-893c-4ab0-9179-3c54d326ea1d\"}"));
  }

  @Test
  void connectWhenKeyRoomIdIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect-key-room-is-empty.json")))
        .andExpect(status().is4xxClientError())
        .andExpect(
            content()
                .json(
                    "{\n"
                        + "  \"message\": \"validation error\",\n"
                        + "  \"errors\": [\n"
                        + "    {\"keyRoomId\": \"must not be null\"}\n"
                        + "  ]\n"
                        + "}"));
  }

  @Test
  void connectWhenAccountingPointIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect-accounting-point-is-empty.json")))
        .andExpect(status().is4xxClientError())
        .andExpect(
            content()
                .json(
                    "{\n"
                        + "  \"message\": \"validation error\",\n"
                        + "  \"errors\": [\n"
                        + "    {\"accountingPoint\": \"must not be null\"}\n"
                        + "  ]\n"
                        + "}"));
  }

  @Test
  void connectWhenPeriodIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect-period-is-empty.json")))
        .andExpect(status().is4xxClientError())
        .andExpect(
            content()
                .json(
                    "{\n"
                        + "  \"message\": \"validation error\",\n"
                        + "  \"errors\": [\n"
                        + "    {\"period\": \"must not be null\"}\n"
                        + "  ]\n"
                        + "}"));
  }

  @Test
  void connectWhenMultiFieldIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect-multi-field-is-empty.json")))
        .andExpect(status().is4xxClientError())
        .andExpect(
            content()
                .json(
                    "{\n"
                        + "  \"message\": \"validation error\",\n"
                        + "  \"errors\": [\n"
                        + "    {\"accountingPoint\": \"must not be null\"},\n"
                        + "    {\"period\": \"must not be null\"},\n"
                        + "    {\"keyRoomId\": \"must not be null\"}\n"
                        + "  ]\n"
                        + "}"));
  }

  @Test
  void disconnect() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point-state/disconnect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point-state/connect.json")))
        .andExpect(status().isOk())
        .andExpect(content().json("{ queryId:\"ce102007-893c-4ab0-9179-3c54d326ea1d\" }"));
  }
}
