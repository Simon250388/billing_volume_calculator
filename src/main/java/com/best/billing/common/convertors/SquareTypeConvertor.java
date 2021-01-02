package com.best.billing.common.convertors;

import com.best.billing.common.model.enums.SquareType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class SquareTypeConvertor implements AttributeConverter<SquareType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SquareType squareType) {
        return squareType.getId();
    }

    @Override
    public SquareType convertToEntityAttribute(Integer integer) {
        return Stream.of(SquareType.values())
                .filter(p -> p.getId() == integer)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
