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

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class CalculationItem {
    @NotNull
    private final StabPeriod stabPeriod;
    @NotNull
    private final Optional<CalculationMethodByDirectionOfUse> calculationMethodByDirectionOfUse;
    @NotNull
    private final Optional<SeasonalitySetting> seasonalitySettingsByBuilding;
    @NotNull
    private final Optional<SeasonalitySetting> seasonalitySetting;
    @NotNull
    private final Optional<AccountingPointServiceAvgVolume> accountingPointServiceAvgVolume;
    @NotNull
    private final Optional<RateValue> rateValue;
    @NotNull
    private final Optional<KeyNormValue> keyNormValue;
    @NotNull
    private final List<MeterValue> meterValuesStart;
    @NotNull
    private final List<MeterValue> meterValuesEnd;
}
