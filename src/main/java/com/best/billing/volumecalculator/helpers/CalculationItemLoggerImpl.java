package com.best.billing.volumecalculator.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculationItemLoggerImpl implements  CalculationItemLogger {
    private final LocalDate calculationPeriod;

    private enum logLevel {
        error(0),
        info(1);

        logLevel(int value) {
            this.value = value;
        }

        private final int value;

        public int getValue() {
            return value;
        }
    }


    private class logItem {
        private String calculationType;
        private String result;
        private String details;

        public logItem(String calculationType, String result, String details) {
            this.calculationType = calculationType;
            this.result = result;
            this.details = details;
        }
    }

    private List<logItem> steps = new ArrayList<>();

    public CalculationItemLoggerImpl(LocalDate calculationPeriod) {
        this.calculationPeriod = calculationPeriod;
    }

    public void addStep(String calculationType, String result, String details) {
        steps.add(new logItem(calculationType, result, details));
    }

}
