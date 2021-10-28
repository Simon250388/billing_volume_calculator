package org.billing.accountingpoints.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.billing.accountingpoints.configuration.ObjectMapperConfiguration;
import org.billing.accountingpoints.request.AccountingPointRequest;
import org.billing.accountingpoints.request.AccountingPointStateRequest;
import org.billing.accountingpoints.request.ServiceImprovementTypeRequest;
import org.billing.accountingpoints.request.ServiceProviderRequest;
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
class AccountingPointStateRequestTest {
  @Autowired private JacksonTester<AccountingPointStateRequest> tester;

  @Test
  @Tag("small")
  void deserialize() throws IOException {
    final AccountingPointStateRequest expected = getSimpleModel();


    final AccountingPointStateRequest result =
        tester.readObject("/json/accounting-point-state/model.json");

    assertThat(result, samePropertyValuesAs(expected));
  }

  @Test
  @Tag("small")
  void serialize() throws IOException {
    final AccountingPointStateRequest expected = getSimpleModel();

    final JsonContent<AccountingPointStateRequest> content = tester.write(expected);

    assertEquals(
        JsonParser.parseString(
            FileContentUtls.contentAsString("/json/accounting-point-state/model.json")),
        JsonParser.parseString(content.getJson()));
  }

  private AccountingPointStateRequest getSimpleModel() {
    return AccountingPointStateRequest.builder()
            .serviceId(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
            .accountingPoint(
                    AccountingPointRequest.builder()
                            .id(UUID.fromString("3f187ec3-e6fe-43e8-9648-df77f7c9de27"))
                            .build())
            .serviceProviders(
                    List.of(
                            ServiceProviderRequest.builder()
                                    .serviceId(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
                                    .providerId(UUID.fromString("193e7ad8-195f-475d-8381-9912d092c364"))
                                    .build()))
            .serviceImprovementTypes(
                    List.of(
                            ServiceImprovementTypeRequest.builder()
                                    .serviceId(UUID.fromString("e0b27b11-6e2e-4aa5-83e0-c464d0376f55"))
                                    .improvementTypeId(UUID.fromString("b4d80e55-ba7f-4eb7-b5a0-12b44f7e68f4"))
                                    .build()))
            .build();
  }
}
