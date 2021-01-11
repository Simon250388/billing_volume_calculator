package com.best.billing.volumecalculator.helpers;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.stabs.CalculationItemStabFactory;
import com.best.billing.stabs.StabFactory;
import com.best.billing.volumecalculator.resolution.Resolution;
import com.best.billing.volumecalculator.resolution.resolution354.Resolution354;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByAvgCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByAvgNormCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByMeterValueCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByNormCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByAvgNormCalculationValidator;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByAvgVolumeCalculationValidator;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByMeterVolumeCalculationValidator;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByNormCalculationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        Resolution354.class,
        VolumeByAvgCalculationRule.class,
        VolumeByAvgNormCalculationRule.class,
        VolumeByMeterValueCalculationRule.class,
        VolumeByNormCalculationRule.class,
        ByAvgNormCalculationValidator.class,
        ByAvgVolumeCalculationValidator.class,
        ByMeterVolumeCalculationValidator.class,
        ByNormCalculationValidator.class,
        CalculatorImpl.class
})
class CalculatorImplTest {
    @MockBean
    public CalculationItemBuilder calculationItemBuilder;

    @MockBean
    public DurationCalculator durationCalculator;

    @Autowired
    public Resolution resolution;

    @Autowired
    public Calculator calculator;

    @Test
    void get_perfomance() {

        StabFactory stabFactory = StabFactory.builder().build();

        final CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory, true, MeterState.ACTIVE);

        when(durationCalculator.getDurationByDays(any())).thenReturn(30L);
        when(durationCalculator.daysOfCalculationPeriod(any())).thenReturn(30L);

        final Stream<CalculationItem> calculationItemStream = Stream.generate(() -> calculationItem).limit(500000);
        List<CalculationItem> collect = calculationItemStream.collect(Collectors.toList());
        when(calculationItemBuilder.buildStream(any())).thenReturn(collect.stream());

        calculator.calculate(LocalDate.now(), resolution);
    }

}