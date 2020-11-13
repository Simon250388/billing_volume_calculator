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
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CalculationItem {
    @NotNull
    private final StabPeriod stabPeriod;
    @Nullable
    private final CalculationMethodByDirectionOfUse calculationMethodByDirectionOfUse;
    @Nullable
    private final SeasonalitySetting seasonalitySettingsByBuilding;
    @Nullable
    private final SeasonalitySetting seasonalitySetting;
    @Nullable
    private final AccountingPointServiceAvgVolume accountingPointServiceAvgVolume;
    @Nullable
    private final RateValue rateValue;
    @Nullable
    private final KeyNormValue keyNormValue;
    @NotNull
    private final List<MeterValue> meterValuesStart;
    @NotNull
    private final List<MeterValue> meterValuesEnd;
    /**
     * Показания приборов представлены
     */
    @NotNull
    private final boolean isMeterValueProvide;

    /**
     * Сейчас сезон услуги
     */
    @NotNull
    private final boolean isSeasonalityActive;
}
