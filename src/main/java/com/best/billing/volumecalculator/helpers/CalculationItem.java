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
import lombok.NonNull;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Nullable
    private final List<MeterValue> meterValuesStart;
    @NotNull
    private final List<MeterValue> meterValuesEnd;
    /**
     * Сейчас сезон услуги
     */
    @NotNull
    private final boolean isSeasonalityActive;
    /**
     * Завершение расчетного периода
     */
    @NotNull
    private final LocalDateTime calculationPeriodEnd;

    /**
     * Начало расчетного периода
     */
    @NotNull
    private final LocalDateTime calculationPeriodStart;

    public LocalDate getEndOfStabPeriod(@NonNull CalculationItem item) {
        return item.getStabPeriod().getNextRow() == null ? calculationPeriodEnd.toLocalDate() : item.getStabPeriod().getNextRow().getRegistrationPeriod();
    }

    public boolean meterValuesIsProvide() {
        return false;
    }

    /**
     * Показатель нормы
     */
    public long getNormIndex() {
        if (getCalculationMethodByDirectionOfUse().getSquareType() != null) {
            return getStabPeriod().getRoomSquare().getValue();
        } else {
            return getStabPeriod().getPeopleCount();
        }
    }

    /**
     * Коэффцциент норматива
     */
    public int getCoefficientNormValue() {
        return getSeasonalitySetting().isDoNotUseSeasonality()
                ? getSeasonalitySetting().getCoefficientNormValueDoNotUseSeasonality() : getSeasonalitySetting().getCoefficientNormValue();

    }
}
