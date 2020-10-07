package com.best.billing.volumecalculator.mappers;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;
import com.best.billing.volumecalculator.dto.BaseCatalogDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig
public interface BaseCatalogMapper<E extends BaseCatalog, D extends BaseCatalogDTO> extends BaseEntityMapper<E, D> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "description", target = "present")
    @Override
    D fromEntity(E source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "present", target = "description")
    @Override
    E toEntity(D source);
}
