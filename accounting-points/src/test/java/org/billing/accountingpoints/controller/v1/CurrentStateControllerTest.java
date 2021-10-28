package org.billing.accountingpoints.controller.v1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.billing.accountingpoints.request.RoomAccountingPointsRequest;
import org.billing.accountingpoints.service.GuidGenerator;
import org.billing.accountingpoints.service.ServiceStateService;
import org.billing.accountingpoints.usecase.AccountingPointsStateService;
import org.junit.jupiter.api.Disabled;
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

@WebMvcTest(CurrentStateController.class)
@Import({CurrentStateControllerTest.TestConfig.class, QueueConfiguration.class})
@ActiveProfiles("test")
@Tag("medium")
@Disabled
class CurrentStateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountingPointsStateService service;

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
            return new CurrentStateControllerTest.TestConfig.SimpleGuidGenerator();
        }

        @Bean
        Clock clock() {
            return Clock.fixed(Instant.parse("2022-01-01T00:00:00.00Z"), ZoneId.of("UTC"));
        }

        @Bean
        AccountingPointsStateService serviceStateService() {
            return Mockito.mock(AccountingPointsStateService.class);
        }
    }

    @Test
    void getCurrentState() throws Exception {

        final RoomAccountingPointsRequest models =
                RoomAccountingPointsRequest.builder().build();

        when(service.currentState(any(UUID.class))).thenReturn(models);

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
                        get("/v1/current-state/e3c08228-9011-4820-9d2a-02d0913acf18")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonModel));
    }
}
