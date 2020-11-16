package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;

public class VolumeByNormCalculationRule implements CalculationRule {

    public static final String CALCULATION_TYPE_BY_NORM = "Начисления по нормативу";
    public static final int MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME = 3;

    @Override
    public Optional<Long> volumeFact(@NonNull CalculationItem item) {

        if (isMeterStateIsActiveOrCanCalculateAvgVolume(item)) return Optional.empty();

        if (!item.isSeasonalityActive()) {
            //logger.addStepVolume(CALCULATION_TYPE_BY_NORM, "не применяется", "Не действует сезонность");
            return Optional.empty();
        }

        if (item.getSeasonalitySetting() != null &&
                item.getSeasonalitySetting().getVolumeByLastYear()
                && item.getStabPeriod().getAccountingPointKeyRoomServiceEntity().getService().getDependOnService() != null
                && item.getAccountingPointServiceAvgVolume() == null) {
            //logger.addStepVolume(CALCULATION_TYPE_BY_NORM, "не применяется", "В настройках применения сезонности указано применение объема за прошлый год и есть средний объем");
            return Optional.empty();
        }

        long volume = volumeValueByNormValue(item);

        return Optional.of(volume);
    }

    @Override
    public Optional<Long> volume(@NonNull CalculationItem item) {
        if (!item.isSeasonalityActive()) {
            //logger.addStepVolumeFact(CALCULATION_TYPE_BY_NORM, "не применяется", "Не действует сезонность");
            return Optional.empty();
        }

        if (item.getSeasonalitySetting() != null
                && item.getSeasonalitySetting().getVolumeByLastYear()
                && item.getAccountingPointServiceAvgVolume() == null) {
            //logger.addStepVolumeFact(CALCULATION_TYPE_BY_NORM, "не применяется", "В настройках применения сезонности указано применение объема за прошлый год и есть средний объем");
            return Optional.empty();
        }

        if (isMeterStateIsActiveOrCanCalculateAvgVolume(item)) return Optional.empty();

        long volume = volumeValueByNormValue(item);

        return Optional.of(volume);
    }

    private boolean isMeterStateIsActiveOrCanCalculateAvgVolume(CalculationItem item) {
        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)
                || monthBetween(item.getStabPeriod().getAccountingPointMeterState().getPeriod().toLocalDate(), item.getEndOfCalculationPeriod().toLocalDate()) > MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME) {
            //logger.addStepVolumeFact(CALCULATION_TYPE_BY_NORM, "не применяется", "Прибор учета не введен или с момента вывода прибора учета еще существует возможность начисления по среденму");
            return true;
        }
        return false;
    }

    private long monthBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    private long volumeValueByNormValue(@NonNull CalculationItem item) {
        /*
          Зачем нам два коэффициента для норматива если мы либо применяем сезонность либо нет
         */

        if (item.getSeasonalitySetting() == null || item.getSeasonalitySetting().getDoNotUseSeasonality() == null ) {
            //logger.addStepVolume(CALCULATION_TYPE_BY_NORM, "Применяется", "Не удалось определить ключ норматива");
            return 0;
        }

        int coefficientNormValue = item.getSeasonalitySetting().getDoNotUseSeasonality()
                ? item.getSeasonalitySetting().getCoefficientNormValueDoNotUseSeasonality() : item.getSeasonalitySetting().getCoefficientNormValue();

        int roomNormIndex = roomNormIndex(item);

        double durationForPeriodRegistration = getDurationForPeriodRegistration(item);

        if (item.getKeyNormValue() == null) {
            //logger.addStepVolume(CALCULATION_TYPE_BY_NORM, "Применяется", "Не удалось определить ключ норматива");
            return 0;
        }

        return (long) (item.getKeyNormValue().getNormValue() * coefficientNormValue * roomNormIndex * durationForPeriodRegistration);
    }

    private int roomNormIndex(@NonNull CalculationItem item) {
        if (item.getCalculationMethodByDirectionOfUse() == null) {
            // TODO add log info
            return 0;
        }

        if (item.getCalculationMethodByDirectionOfUse().getSquareType() != null) {
            return item.getStabPeriod().getRoomSquare().getValue();
        } else if (item.getStabPeriod().getRoomResident() != null && item.getStabPeriod().getRoomResident().getResidentCount() != 0) {
            return item.getStabPeriod().getRoomResident().getResidentCount();
        } else if (item.getStabPeriod().getRoomPrescribed() != null && item.getStabPeriod().getRoomPrescribed().getPrescribedCount() != 0) {
            return item.getStabPeriod().getRoomPrescribed().getPrescribedCount();
        } else if (item.getStabPeriod().getRoomOwner() != null) {
            return item.getStabPeriod().getRoomOwner().getOwnerCount();
        } else {
            return 0;
            // TODO
            // add log info
        }
    }

    private double getDurationForPeriodRegistration(@NonNull CalculationItem item) {
        LocalDateTime localDateTime = item.getEndOfCalculationPeriod();
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, 1);
        int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate endOfCalculationPeriod = item.getEndOfStabPeriod(item);
        long days = ChronoUnit.DAYS.between(item.getStabPeriod().getRegistrationPeriod(), endOfCalculationPeriod);
        return days / dayOfMonth;
    }
}
