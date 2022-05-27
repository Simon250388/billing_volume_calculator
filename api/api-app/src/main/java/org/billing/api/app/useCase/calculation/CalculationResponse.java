package org.billing.api.app.useCase.calculation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CalculationResponse.CalculationResponseBuilder.class)
public class CalculationResponse {

    @JsonPOJOBuilder
    public static class CalculationResponseBuilder{}
}
