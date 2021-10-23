package org.billing.accountingpoints.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.billing.accountingpoints.configuration.ObjectMapperConfiguration;
import org.billing.accountingpoints.request.AccountingPointRequest;
import org.billing.accountingpoints.request.ChangeServiceStateRequest;
import org.billing.accountingpoints.utils.FileContentUtls;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.annotation.Import;

@JsonTest
@Import(ObjectMapperConfiguration.class)
class ChangeServiceStateRequestTest {

  @Autowired private JacksonTester<ChangeServiceStateRequest> tester;

  @Test
  @Tag("small")
  void deserialize() throws IOException {
    final ChangeServiceStateRequest expected =
        ChangeServiceStateRequest.builder()
            .keyRoomId(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
            .accountingPoint(
                AccountingPointRequest.builder()
                    .id(UUID.fromString("3f187ec3-e6fe-43e8-9648-df77f7c9de27"))
                    .build())
            .serviceId(UUID.fromString("49049133-dd1e-4dbb-9bdc-da9f46b08b6f"))
            .period(OffsetDateTime.parse("2021-10-01T12:00:00+04:00"))
            .build();

    final ChangeServiceStateRequest result =
        tester.readObject("/json/change-service-state-request/simple.json");

    assertThat(result, samePropertyValuesAs(expected));
  }

  @Test
  @Tag("small")
  void serialize() throws IOException {
    final ChangeServiceStateRequest expected =
        ChangeServiceStateRequest.builder()
            .keyRoomId(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
            .accountingPoint(
                AccountingPointRequest.builder()
                    .id(UUID.fromString("3f187ec3-e6fe-43e8-9648-df77f7c9de27"))
                    .build())
            .serviceId(UUID.fromString("49049133-dd1e-4dbb-9bdc-da9f46b08b6f"))
            .period(OffsetDateTime.parse("2021-10-01T12:00:00+04:00"))
            .build();

    final JsonContent<ChangeServiceStateRequest> content = tester.write(expected);

    assertEquals(
        JsonParser.parseString(
            FileContentUtls.contentAsString("/json/change-service-state-request/simple.json")),
        JsonParser.parseString(content.getJson()));
  }
}
