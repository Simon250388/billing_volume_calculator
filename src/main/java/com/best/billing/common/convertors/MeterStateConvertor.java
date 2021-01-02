package com.best.billing.common.convertors;

import com.best.billing.common.model.enums.MeterState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class MeterStateConvertor implements AttributeConverter<MeterState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MeterState meterType) {
        return meterType.getId();
    }

    @Override
    public MeterState convertToEntityAttribute(Integer integer) {
        return Stream.of(MeterState.values())
                .filter(p -> p.getId() == integer)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
