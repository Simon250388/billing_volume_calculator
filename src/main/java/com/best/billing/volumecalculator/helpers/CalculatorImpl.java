package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.ServiceVolumeValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorImpl implements Calculator {

    @Override
    public List<ServiceVolumeValue> calculate(Stream<CalculationItem> items) {
        return items.flatMap(item -> Stream.of(
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

    private ServiceVolumeValue volumeFactByAvgValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByAvgValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByAvgValueOnLastYear(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByAvgNormValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByAvgNormValueOnLastYear(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeFactByNormValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByNormValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeFactByAvgNormValue(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeFactByMeterValues(CalculationItem item) {
        return null;
    }

    private ServiceVolumeValue volumeByMeterValues(CalculationItem item) {
        return null;
    }


}
