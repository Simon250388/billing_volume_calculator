package com.best.billing.servicebuilder.mappers.catalog;

import com.best.billing.servicebuilder.dto.catalog.AccountingPointDTO;
import com.best.billing.servicebuilder.mappers.BaseCatalogMapper;
import com.best.billing.servicebuilder.mappers.BaseEntityMapper;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface AccountingPointMapper extends BaseCatalogMapper<AccountingPoint, AccountingPointDTO> {
}
