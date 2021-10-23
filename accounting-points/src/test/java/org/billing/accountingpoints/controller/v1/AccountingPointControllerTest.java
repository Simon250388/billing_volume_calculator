package org.billing.accountingpoints.controller.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.billing.accountingpoints.configuration.QueueConfiguration;
import org.billing.accountingpoints.service.GuidGenerator;
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

@WebMvcTest(AccountingPointController.class)
@Import({AccountingPointControllerTest.TestConfig.class, QueueConfiguration.class})
@ActiveProfiles("test")
class AccountingPointControllerTest {

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
  }

  @Autowired MockMvc mockMvc;

  @Test
  @Tag("medium")
  void connect() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect.json")))
        .andExpect(status().isOk())
        .andExpect(content().json("{ queryId:\"ce102007-893c-4ab0-9179-3c54d326ea1d\"}"));
  }

  @Test
  @Tag("medium")
  void connectWhenKeyRoomIdIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect-key-room-is-empty.json")))
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
  @Tag("medium")
  void connectWhenAccountingPointIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect-accounting-point-is-empty.json")))
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
  @Tag("medium")
  void connectWhenPeriodIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect-period-is-empty.json")))
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
  @Tag("medium")
  void connectWhenMultiFieldIsEmpty() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/connect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect-multi-field-is-empty.json")))
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
  @Tag("medium")
  void disconnect() throws Exception {
    mockMvc
        .perform(
            post("/v1/accounting-point/disconnect")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                    FileContentUtls.contentAsString(
                        "/controller/v1/accounting-point/connect.json")))
        .andExpect(status().isOk())
        .andExpect(content().json("{ queryId:\"ce102007-893c-4ab0-9179-3c54d326ea1d\" }"));
  }
}
