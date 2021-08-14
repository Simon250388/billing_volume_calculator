package org.billing.common.model;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class MethodProvideMeterValueConvertor implements AttributeConverter<MethodProvideMeterValue, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MethodProvideMeterValue methodProvideMeterValue) {
        return methodProvideMeterValue.getId();
    }

    @Override
    public MethodProvideMeterValue convertToEntityAttribute(Integer integer) {
        return Stream.of(MethodProvideMeterValue.values())
                .filter(p -> p.getId() == integer)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
