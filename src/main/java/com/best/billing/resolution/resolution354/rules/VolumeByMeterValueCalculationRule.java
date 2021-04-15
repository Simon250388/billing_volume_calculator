package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByMeterValueRule")
public class VolumeByMeterValueCalculationRule implements CalculationRule {

    public long volume(
            @NonNull final CalculationSettings calculationSettings,
            @NonNull final RoomProperties roomProperties,
            @NonNull final AccountingPointProperty accountingPointProperty,
            @NonNull final ServicePartProperty servicePartProperty) {
        return volumeValueByMeterValue(roomProperties, accountingPointProperty, servicePartProperty);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_METER;
    }

    private long volumeValueByMeterValue(@NonNull final RoomProperties roomProperties,
                                         @NonNull final AccountingPointProperty accountingPointProperty,
                                         @NonNull final ServicePartProperty servicePartProperty) {
        return 0;
        //return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }
}
