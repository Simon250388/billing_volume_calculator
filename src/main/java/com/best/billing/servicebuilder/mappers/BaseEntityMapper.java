package com.best.billing.servicebuilder.mappers;


import com.best.billing.base.model.BaseEntity;
import com.best.billing.base.dto.BaseEntityDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseEntityMapper<E extends BaseEntity, D extends BaseEntityDTO> {
    @Mapping(source = "id", target = "id")
    D fromEntity(E source);

    List<D> fromEntity(List<E> source);

    Iterable<D> fromEntity(Iterable<E> source);

    @Mapping(source = "id", target = "id")
    E toEntity(D source);

    List<E> toEntity(List<D> source);
}
