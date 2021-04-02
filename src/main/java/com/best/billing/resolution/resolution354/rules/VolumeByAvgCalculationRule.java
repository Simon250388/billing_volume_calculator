package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByAvgRule")
public class VolumeByAvgCalculationRule implements CalculationRule {
    @Override
    public long volume(@NonNull final AccountingPointProperties accountingPointProperties) {
        return accountingPointProperties.getServiceAvgVolume();
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_VOLUME;
    }
}
