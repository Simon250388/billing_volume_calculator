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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
class CalculationItem {
    @NotNull final private StabPeriod stabPeriod;
    @NotNull final private Optional<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUse;
    @NotNull final private Optional<SeasonalitySetting> seasonalitySettingsByBuilding;
    @NotNull final private Optional<SeasonalitySetting> seasonalitySetting;
    @NotNull final private Optional<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolume;
    @NotNull final private Optional<RateValue> rateValue;
    @NotNull final private Optional<KeyNormValue> keyNormValue;
    @NotNull final List<MeterValue> meterValuesStart;
    @NotNull final List<MeterValue> meterValuesEnd;
}
