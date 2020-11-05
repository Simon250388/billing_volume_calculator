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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope("prototype")
@Slf4j
public class CalculatorImpl implements Calculator {

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
        return null;
    }

    @Nullable
    private ServiceVolumeValue volumeByNormValue(@NonNull CalculationItem item) {
        return null;
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

        if (!item.getMeterValueIsProvide()) {
            return null;
        }

        int volume = getVolumeByMeterValue(item);

        if (getDuration(item) < 86400 && volume == 0) {
            return null;
        }

        return buildVolumeValue(item, 0, volume);
    }

    @Nullable
    private ServiceVolumeValue volumeByMeterValues(@NonNull CalculationItem item) {
        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.ACTIVE_STATE)) {
            return null;
        }

        if (!item.getMeterValueIsProvide()) {
            return null;
        }

        boolean volumeByLastYear = false;

        if (item.getSeasonalitySetting() != null ) {
            volumeByLastYear = item.getSeasonalitySetting().getVolumeByLastYear();
        }

        if (volumeByLastYear) {
            return null;
        }

        int volume = getVolumeByMeterValue(item);

        if (getDuration(item) < 86400 && volume == 0) {
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

    private int getVolumeByMeterValue(@NonNull CalculationItem item) {
        return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }

    private long getDuration(@NonNull CalculationItem item) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        long registrationPeriodEnd = calendar.getTime().getTime();

        if (item.getStabPeriod().getNextRow() != null) {
            registrationPeriodEnd = item.getStabPeriod().getNextRow().getRegistrationPeriod().getTime();
        }

        return registrationPeriodEnd - item.getStabPeriod().getRegistrationPeriod().getTime();
    }

}
