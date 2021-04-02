package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByMeterValueRule")
public class VolumeByMeterValueCalculationRule implements CalculationRule {

    public long volume(@NonNull final AccountingPointProperties accountingPointProperties) {
        return volumeValueByMeterValue(accountingPointProperties);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_METER;
    }

    private long volumeValueByMeterValue(@NonNull final AccountingPointProperties accountingPointProperties) {
        return 0;
        //return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }
}
