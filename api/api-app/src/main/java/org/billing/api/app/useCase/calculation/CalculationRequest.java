package org.billing.api.app.useCase.calculation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CalculationRequest.CalculationRequestBuilder.class)
public class CalculationRequest {
    @JsonPOJOBuilder
    public static class CalculationRequestBuilder {
    }
}
