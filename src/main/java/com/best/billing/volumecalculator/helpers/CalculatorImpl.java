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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope("prototype")
@Slf4j
public class CalculatorImpl implements Calculator {
    public static final long SECOND_IN_DAY = 86400;
    public static final int MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME = 3;
    private final CalculationItemBuilder calculationItemBuilder;

    @Autowired
    public CalculatorImpl(CalculationItemBuilder calculationItemBuilder) {
        this.calculationItemBuilder = calculationItemBuilder;
    }

    @Override
    public List<ServiceVolumeValue> calculate(@NonNull LocalDate calculationPeriod) {
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
                ))
                .filter(value -> value != null).collect(Collectors.toList());
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
                || monthBetween(item.getStabPeriod().getAccountingPointMeterState().getPeriod().toLocalDate(), endOfCalculationPeriod().toLocalDate()) > MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME) {
            return null;
        }

        long volume = volumeValueByNormValue(item);

        return buildVolumeValue(item, 0, volume);
    }

    @Nullable
    private ServiceVolumeValue volumeByNormValue(@NonNull CalculationItem item) {

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)
                || monthBetween(item.getStabPeriod().getAccountingPointMeterState().getPeriod().toLocalDate(), endOfCalculationPeriod().toLocalDate()) > MONTH_METER_IS_NOT_ACTIVE_FOR_AVG_VOLUME) {
            return null;
        }

        if (!item.isSeasonalityActive()) {
            return null;
        }

        if (item.getSeasonalitySetting() == null) {
            return null;
        }

        if (item.getSeasonalitySetting().getVolumeByLastYear()
                && item.getStabPeriod().getAccountingPointKeyRoomServiceEntity().getService().getDependOnService() != null
                && item.getAccountingPointServiceAvgVolume() == null) {
            return null;
        }

        long volume = volumeValueByNormValue(item);

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

    private ServiceVolumeValue buildVolumeValue(@NonNull CalculationItem item, long volume, long volumeFact) {
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

    private long volumeValueByNormValue(@NonNull CalculationItem item) {
        /**
         * Зачем нам два коэффициента для норматива если мы либо применяем сезонность либо нет
         */
        int coefficientNormValue = item.getSeasonalitySetting().getDoNotUseSeasonality()
                ? item.getSeasonalitySetting().getCoefficientNormValueDoNotUseSeasonality() : item.getSeasonalitySetting().getCoefficientNormValue();

        int roomNormIndex = roomNormIndex(item);

        double durationForPeriodRegistration = getDurationForPeriodRegistration(item);

        if (item.getKeyNormValue() == null) {
            // TODO
            // add log info do not read key norm
            return 0;
        }

        return (long) (item.getKeyNormValue().getNormValue() * coefficientNormValue * roomNormIndex * durationForPeriodRegistration);
    }

    private long getDurationBySecondsForPeriodRegistration(@NonNull CalculationItem item) {

        LocalDate registrationPeriodEnd = endOfCalculationPeriod().toLocalDate();

        if (item.getStabPeriod().getNextRow() != null) {
            registrationPeriodEnd = item.getStabPeriod().getNextRow().getRegistrationPeriod();
        }

        return ChronoUnit.SECONDS.between(registrationPeriodEnd, item.getStabPeriod().getRegistrationPeriod());
    }

    private LocalDateTime endOfCalculationPeriod() {
        return LocalDateTime.of(2020, 8, 1, 0, 0, 0);
    }

    private int roomNormIndex(@NonNull CalculationItem item) {
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
        LocalDateTime localDateTime = endOfCalculationPeriod();
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, 1);
        int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate endOfCalculationPeriod = endOfStabPeriod(item);
        long days = dayBetween(item.getStabPeriod().getRegistrationPeriod(), endOfCalculationPeriod);
        return days / dayOfMonth;
    }

    private LocalDate endOfStabPeriod(@NonNull CalculationItem item) {
        return item.getStabPeriod().getNextRow() == null ? endOfCalculationPeriod().toLocalDate() : item.getStabPeriod().getNextRow().getRegistrationPeriod();
    }

    private long monthBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    private long dayBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

}
