package com.best.billingvolumecalculator.mappers;

import com.best.billingvolumecalculator.basemodels.BaseHistory;
import com.best.billingvolumecalculator.dto.BaseHistoryDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig(componentModel = "spring")
public interface BaseHistoryMapper<E extends BaseHistory, D extends BaseHistoryDTO> extends BaseEntityMapper<E, D> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "period", target = "period", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Override
    D fromEntity(E source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "period", target = "period", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Override
    E toEntity(D source);
}
