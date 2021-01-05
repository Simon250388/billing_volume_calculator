package com.best.billing.volumecalculator.helpers;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Service
public class DurationCalculatorImpl implements DurationCalculator {
    @Override
    public long getDurationByDays(CalculationItem item) {
        LocalDateTime localDateTime = item.getEndOfCalculationPeriod();
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, 1);
        int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate endOfCalculationPeriod = item.getEndOfStabPeriod(item);
        return ChronoUnit.DAYS.between(item.getStabPeriod().getRegistrationPeriod(), endOfCalculationPeriod);
    }

    @Override
    public long getDurationBySeconds(CalculationItem item) {
        LocalDate registrationPeriodEnd = item.getEndOfCalculationPeriod().toLocalDate();

        if (item.getStabPeriod().getNextRow() != null) {
            registrationPeriodEnd = item.getStabPeriod().getNextRow().getRegistrationPeriod();
        }

        return ChronoUnit.SECONDS.between(registrationPeriodEnd, item.getStabPeriod().getRegistrationPeriod());
    }
}
