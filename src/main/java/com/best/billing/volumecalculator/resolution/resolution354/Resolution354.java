package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.volumecalculator.resolution.CalculationRule;
import com.best.billing.volumecalculator.resolution.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Resolution354 implements Resolution {
    //@Value()
    private Map<CalculationRule, CalculationValidator> rules;

    @Override
    public Map<CalculationRule, CalculationValidator> getRules() {
        return rules;
    }
}
