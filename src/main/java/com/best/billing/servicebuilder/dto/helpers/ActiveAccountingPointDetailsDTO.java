package com.best.billing.servicebuilder.dto.helpers;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Value
@Builder
public class ActiveAccountingPointDetailsDTO {
    public static class ActiveAccountingPointDetailsDTOBuilder {

        public ActiveAccountingPointDetailsDTOBuilder addServicePart(@NonNull Long partServiceId, @NonNull Long providerId){
            if (this.servicePartProviders == null) {
                this.servicePartProviders = new HashMap<>();
            }
            this.servicePartProviders.put(partServiceId, providerId);
            return this;
        }
    }

    @NotNull Long keyRoomId;
    @NotNull Long accountingPointId;
    @NotNull Long serviceId;
    @NotNull Long providerId;
    @NotNull Long directionOfUseId;
    @NotNull Boolean isActive;
    @NotNull Long meterId;
    @Null Boolean meterIsActive;
    @Null Date meterStateChangeAt;
    @Null Long differentiationTypeId;
    @Null Integer lastMeterValue;
    @Null Map<Long, Long> servicePartProviders;
}
