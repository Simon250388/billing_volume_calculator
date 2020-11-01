package com.best.billing.base.mappers;

import com.best.billing.base.dto.BaseHistoryDTO;
import com.best.billing.base.model.BaseHistory;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
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
