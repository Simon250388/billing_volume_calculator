package com.best.billingvolumecalculator.mappers;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;
import com.best.billingvolumecalculator.dto.BaseCatalogDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig(componentModel = "spring")
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
