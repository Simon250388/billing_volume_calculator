package com.best.billing.departmen.customer;

import com.best.billing.common.model.enums.MeterState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.*;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class AccountingPointProperty {
    long accountingPointId;
    long serviceId;
    List<ServicePartProperty> servicePartProperties;
    boolean serviceActive;
    long meterId;
    MeterState meterState;
    long differentiationTypeId;

    public AccountingPointProperty getNewInstance() {
        List<ServicePartProperty> newServicePartProperties = new ArrayList<>();
        for (ServicePartProperty servicePartProperty:this.servicePartProperties) {
            newServicePartProperties.add(servicePartProperty.getNewInstance());
        }
        return this.toBuilder().servicePartProperties(newServicePartProperties).build();
    }


}
