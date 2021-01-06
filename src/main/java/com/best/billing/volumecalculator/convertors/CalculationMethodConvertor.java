package com.best.billing.volumecalculator.convertors;

import com.best.billing.volumecalculator.model.CalculationMethod;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class CalculationMethodConvertor implements AttributeConverter<CalculationMethod, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CalculationMethod calculationMethod) {
        return calculationMethod.getId();
    }

    @Override
    public CalculationMethod convertToEntityAttribute(Integer integer) {
        return Stream.of(CalculationMethod.values())
                .filter(p -> p.getId() == integer)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
