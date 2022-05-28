package org.billing.api.app.cucumber.step;

import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import org.billing.api.app.cucumber.TestContext;
import org.billing.api.client.AccountingPointClient;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountingPointSteps {

    @Autowired private AccountingPointClient client;

    @When("Пользователь отправляет запрос создания точки учета c параметрами")
    public void sendCreateRequest(List<Map<String, String>> table) {
        TestContext.CONTEXT.setResponse(client.createAccountingPoint(convertRequestFromDataTable(table)));
    }

    private AccountingPointRequest convertRequestFromDataTable(List<Map<String, String>> values) {

        final AccountingPointRequest.AccountingPointRequestBuilder builder = AccountingPointRequest.builder();

        values
                .get(0)
                .forEach(
                        (key, value) -> {
                            switch (key.toUpperCase()) {
                                case "KEYROOMID":
                                    builder.keyRoomId(value);
                                    break;
                                case "SERVICEID":
                                    builder.serviceId(value);
                                    break;
                                case "PROVIDERID":
                                    builder.providerId(value);
                                    break;
                                case "ACTIVE":
                                    builder.active(Boolean.parseBoolean(value));
                                    break;
                                default:
                                    break;
                            }
                        });

        return builder.build();
    }
}
