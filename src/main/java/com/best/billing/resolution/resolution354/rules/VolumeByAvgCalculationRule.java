package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByAvgRule")
public class VolumeByAvgCalculationRule implements CalculationRule {
    @Override
    public long volume(@NonNull final RoomProperties roomProperties,
                       @NonNull final AccountingPointProperty accountingPointProperty,
                       @NonNull final ServicePartProperty servicePartProperty) {
        return servicePartProperty.getServiceAvgVolume();
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_VOLUME;
    }
}
