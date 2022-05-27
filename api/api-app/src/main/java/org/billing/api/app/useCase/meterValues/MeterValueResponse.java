package org.billing.api.app.useCase.meterValues;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = MeterValueResponse.MeterValueResponseBuilder.class)
public class MeterValueResponse {

    @JsonPOJOBuilder
    public static class MeterValueResponseBuilder {
    }
}
