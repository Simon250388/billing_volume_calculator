package com.best.billingvolumecalculator.mappers;


import com.best.billingvolumecalculator.basemodels.BaseEntity;
import com.best.billingvolumecalculator.dto.BaseEntityDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;

@MapperConfig(componentModel = "spring")
public interface BaseEntityMapper<E extends BaseEntity, D extends BaseEntityDTO> {
    @Mapping(source = "id", target = "id")
    D fromEntity(E source);

    List<D> fromEntity(List<E> source);

    @Mapping(source = "id", target = "id")
    E toEntity(D source);

    List<E> toEntity(List<D> source);
}
