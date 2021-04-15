package com.best.billing.departmen.customer;

import com.best.billing.common.model.enums.SquareType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class ServicePartProperty {
    public static int AVG_VOLUME_EMPTY_VALUE = -1;
    long servicePartId;
    SquareType serviceSquareType;
    long roomRateGroupId;
    long providerId;
    int serviceAvgVolume;
    public boolean isHasServiceAvgVolume() {
        return serviceAvgVolume != AVG_VOLUME_EMPTY_VALUE;
    }

    public ServicePartProperty getNewInstance(){
        return this.toBuilder().build();
    }
}
