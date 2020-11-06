package com.best.billing.volumecalculator.helpers;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope("prototype")
@Slf4j
public class CalculatorImpl implements Calculator {
    public static final  long SECOND_IN_DAY = 86400;
    public static final  int MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME = 3;
    private final CalculationItemBuilder calculationItemBuilder;

    @Autowired
    public CalculatorImpl(CalculationItemBuilder calculationItemBuilder) {
        this.calculationItemBuilder = calculationItemBuilder;
    }

    @Override
    public List<ServiceVolumeValue> calculate(@NonNull Date calculationPeriod) {
        return calculationItemBuilder.buildStream(calculationPeriod)
                .flatMap(item -> Stream.of(
                        volumeByMeterValues(item),
                        volumeFactByMeterValues(item),
                        volumeByNormValue(item),
                        volumeFactByNormValue(item),
                        volumeByAvgNormValueOnLastYear(item),
                        volumeByAvgNormValue(item),
                        volumeFactByAvgNormValue(item),
                        volumeByAvgValueOnLastYear(item),
                        volumeByAvgValue(item),
                        volumeFactByAvgValue(item)
                )).collect(Collectors.toList());
    }

    @Nullable
    private ServiceVolumeValue volumeFactByAvgValue(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeByAvgValue(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeByAvgValueOnLastYear(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeByAvgNormValue(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeByAvgNormValueOnLastYear(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeFactByNormValue(@NonNull CalculationItem item) {
        if (!item.isSeasonalityActive()) {
            return null;
        }

        assert item.getSeasonalitySetting() != null;
        if (item.getSeasonalitySetting().getVolumeByLastYear()
                && item.getStabPeriod().getAccountingPointKeyRoomServiceEntity().getService().getDependOnService() != null
                && item.getAccountingPointServiceAvgVolume() == null) {
            return null;
        }

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)
                || ChronoUnit.MONTHS.between(item.getStabPeriod().getAccountingPointMeterState().getPeriod().toInstant(), endOfCalculationPeriod().toInstant()) > MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME) {
            return null;
        }

        int volume = volumeValueByNormValue(item);

        return buildVolumeValue(item, 0, volume);
    }

    @Nullable
    private ServiceVolumeValue volumeByNormValue(@NonNull CalculationItem item) {
        if (!item.isSeasonalityActive()) {
            return null;
        }

        if (Objects.requireNonNull(item.getSeasonalitySetting()).getVolumeByLastYear()
                && item.getStabPeriod().getAccountingPointKeyRoomServiceEntity().getService().getDependOnService() != null
                && item.getAccountingPointServiceAvgVolume() == null) {
            return null;
        }

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)
                || ChronoUnit.MONTHS.between(item.getStabPeriod().getAccountingPointMeterState().getPeriod().toInstant(), endOfCalculationPeriod().toInstant()) > MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME) {
            return null;
        }

        int volume = volumeValueByNormValue(item);

        return buildVolumeValue(item, volume, 0);
    }

    @Nullable
    private ServiceVolumeValue volumeFactByAvgNormValue(@NonNull CalculationItem item) {
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeFactByMeterValues(@NonNull CalculationItem item) {

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)) {
            return null;
        }

        if (!item.isMeterValueProvide()) {
            return null;
        }

        int volume = volumeValueByMeterValue(item);

        if (getDurationBySecondsForPeriodRegistration(item) < SECOND_IN_DAY && volume == 0) {
            return null;
        }

        return buildVolumeValue(item, 0, volume);
    }

    @Nullable
    private ServiceVolumeValue volumeByMeterValues(@NonNull CalculationItem item) {
        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)) {
            return null;
        }

        if (!item.isMeterValueProvide()) {
            return null;
        }

        boolean isVolumeByLastYear = false;

        if (item.getSeasonalitySetting() != null) {
            isVolumeByLastYear = item.getSeasonalitySetting().getVolumeByLastYear();
        }

        if (isVolumeByLastYear) {
            return null;
        }

        int volume = volumeValueByMeterValue(item);

        if (getDurationBySecondsForPeriodRegistration(item) < SECOND_IN_DAY && volume == 0) {
            return null;
        }

        return buildVolumeValue(item, volume, 0);
    }

    private ServiceVolumeValue buildVolumeValue(@NonNull CalculationItem item, @NonNull Integer volume, @NonNull Integer volumeFact) {
        return ServiceVolumeValue.builder()
                .calculationMethod(CalculationMethod.METHOD_BY_METER)
                .stabPeriod(item.getStabPeriod())
                .volume(volume)
                .factVolume(volumeFact)
                .build();
    }

    private int volumeValueByMeterValue(@NonNull CalculationItem item) {
        return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }

    private int volumeValueByNormValue(@NonNull CalculationItem item) {
        /**
         * Зачем нам два коэффициента для норматива если мы либо применяем сезонность либо нет
         */
        int coefficientNormValue = Objects.requireNonNull(item.getSeasonalitySetting()).getDoNotUseSeasonality()
                ? item.getSeasonalitySetting().getCoefficientNormValueDoNotUseSeasonality() : item.getSeasonalitySetting().getCoefficientNormValue();

        int roomNormIndex = roomNormIndex(item);

        int durationForPeriodRegistration = getDurationForPeriodRegistration(item);

        return Objects.requireNonNull(item.getKeyNormValue()).getNormValue() * coefficientNormValue * roomNormIndex * durationForPeriodRegistration;
    }

    private long getDurationBySecondsForPeriodRegistration(@NonNull CalculationItem item) {

        long registrationPeriodEnd = endOfCalculationPeriod().getTime().getTime();

        if (item.getStabPeriod().getNextRow() != null) {
            registrationPeriodEnd = item.getStabPeriod().getNextRow().getRegistrationPeriod().getTime();
        }

        return registrationPeriodEnd - item.getStabPeriod().getRegistrationPeriod().getTime();
    }

    private long getDurationByDayPeriodRegistration(@NonNull CalculationItem item) {
        return ChronoUnit.DAYS.between(endOfCalculationPeriod().toInstant(), item.getStabPeriod().getRegistrationPeriod().toInstant());
    }

    private Calendar endOfCalculationPeriod() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        return calendar;
    }

    private int roomNormIndex(@NonNull CalculationItem item) {
        if (Objects.requireNonNull(item.getCalculationMethodByDirectionOfUse()).getSquareType() != null) {
            return item.getStabPeriod().getRoomSquare().getValue();
        } else if (item.getStabPeriod().getRoomResident() != null && item.getStabPeriod().getRoomResident().getResidentCount() != 0) {
            return item.getStabPeriod().getRoomResident().getResidentCount();
        } else if (item.getStabPeriod().getRoomPrescribed() != null && item.getStabPeriod().getRoomPrescribed().getPrescribedCount() != 0) {
            return item.getStabPeriod().getRoomPrescribed().getPrescribedCount();
        } else {
            return item.getStabPeriod().getRoomOwner() == null ? 0 : item.getStabPeriod().getRoomOwner().getOwnerCount();
        }
    }

    private int getDurationForPeriodRegistration(@NonNull CalculationItem item) {
        int dayOfMonth = endOfCalculationPeriod().get(Calendar.DAY_OF_MONTH);

        return (int) (getDurationByDayPeriodRegistration(item) / dayOfMonth);
    }

}
