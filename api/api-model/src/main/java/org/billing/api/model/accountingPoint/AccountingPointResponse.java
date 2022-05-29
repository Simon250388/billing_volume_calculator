package org.billing.api.model.accountingPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointResponse.AccountingPointResponseBuilder.class)
public class AccountingPointResponse {
    String id;
    String keyRoomId;
    String serviceId;
    String providerId;
    boolean active;
    boolean meterIsActive;
    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountingPointResponseBuilder {
    }
}
