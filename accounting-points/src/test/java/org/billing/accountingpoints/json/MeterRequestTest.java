package org.billing.accountingpoints.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.billing.accountingpoints.configuration.ObjectMapperConfiguration;
import org.billing.accountingpoints.request.AccountingPointRequest;
import org.billing.accountingpoints.request.ChangeServiceStateRequest;
import org.billing.accountingpoints.request.MeterRequest;
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
class MeterRequestTest {
    @Autowired
    private JacksonTester<MeterRequest> tester;

    @Test
    @Tag("small")
    void deserialize() throws IOException {
        final MeterRequest expected =
                MeterRequest.builder()
                        .id(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
                        .factoryNumber("12345678")
                        .build();

        final MeterRequest result =
                tester.readObject("/json/meter-request/meter-request.json");

        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    @Tag("small")
    void serialize() throws IOException {
        final MeterRequest expected =
                MeterRequest.builder()
                        .id(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
                        .factoryNumber("12345678")
                        .build();

        final JsonContent<MeterRequest> content = tester.write(expected);

        assertEquals(
                JsonParser.parseString(
                        FileContentUtls.contentAsString("/json/meter-request/meter-request.json")),
                JsonParser.parseString(content.getJson()));
    }
}
