package org.billing.api.app.useCase.meterValues;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = MeterResponse.MeterResponseBuilder.class)
public class MeterResponse {

    @JsonPOJOBuilder
    public static class MeterResponseBuilder {
    }
}
