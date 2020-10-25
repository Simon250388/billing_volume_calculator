package com.best.billing.servicebuilder.dto.helpers;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Value
@Builder
public class ActiveAccountingPointDetailsDTO {
    public static class ActiveAccountingPointDetailsDTOBuilder {

        public ActiveAccountingPointDetailsDTOBuilder addServicePart(@NotNull Long partServiceId, @NotNull Long providerId){
            if (this.servicePartProviders == null) {
                this.servicePartProviders = new HashMap<>();
            }
            this.servicePartProviders.put(partServiceId, providerId);
            return this;
        }
    }
    Long keyRoomId;
    Long accountingPointId;
    Long serviceId;
    Long providerId;
    Long directionOfUseId;
    Boolean isActive;
    Long meterId;
    @Nullable
    Boolean meterIsActive;
    @Nullable
    Date meterStateChangeAt;
    @Nullable
    Long differentiationTypeId;
    @Nullable
    Integer lastMeterValue;
    @Nullable
    Map<Long, Long> servicePartProviders;
}
