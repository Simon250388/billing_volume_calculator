package org.billing.api.app.useCase.accountingPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointResponse.AccountingPointResponseBuilder.class)
public class AccountingPointResponse {

    @JsonPOJOBuilder
    public static class AccountingPointResponseBuilder {
    }
}
