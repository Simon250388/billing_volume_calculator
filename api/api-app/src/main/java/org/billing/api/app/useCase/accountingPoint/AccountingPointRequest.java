package org.billing.api.app.useCase.accountingPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointRequest.AccountingPointRequestBuilder.class)
public class AccountingPointRequest {

    @NotBlank(message = "Ключ помещения не может быть пустым")
    String keyRoomId;
    String accountingPointId;
    @NotBlank(message = "Услуга не заполненна")
    String serviceId;
    @NotBlank(message = "Поставщик не может быть пустым")
    String providerId;
    boolean isActive;
    String meterId;
    boolean meterIsActive;
    OffsetDateTime meterStateChangeAt;

    @JsonPOJOBuilder
    public static class AccountingPointRequestBuilder {
    }
}
