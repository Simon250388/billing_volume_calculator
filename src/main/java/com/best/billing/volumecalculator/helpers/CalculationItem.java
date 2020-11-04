package com.best.billing.volumecalculator.helpers;

import com.best.billing.commonsettings.model.CalculationMethodByDirectionOfUse;
import com.best.billing.commonsettings.model.KeyNormValue;
import com.best.billing.commonsettings.model.RateValue;
import com.best.billing.commonsettings.model.SeasonalitySetting;
import com.best.billing.servicebuilder.models.historychange.MeterValue;
import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;
import com.best.billing.volumecalculator.model.StabPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
class CalculationItem {
    @NonNull final private StabPeriod stabPeriod;
    @NonNull final private Optional<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUse;
    @NonNull final private Optional<SeasonalitySetting> seasonalitySettingsByBuilding;
    @NonNull final private Optional<SeasonalitySetting> seasonalitySetting;
    @NonNull final private Optional<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolume;
    @NonNull final private Optional<RateValue> rateValue;
    @NonNull final private Optional<KeyNormValue> keyNormValue;
    @NonNull final List<MeterValue> meterValuesStart;
    @NonNull final List<MeterValue> meterValuesEnd;
}
