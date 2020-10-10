package com.best.billing.volumecalculator.mappers;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig
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
