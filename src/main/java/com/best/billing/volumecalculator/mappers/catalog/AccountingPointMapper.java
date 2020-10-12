package com.best.billing.volumecalculator.mappers.catalog;

import com.best.billing.volumecalculator.dto.catalog.AccountingPointDTO;
import com.best.billing.volumecalculator.mappers.BaseCatalogMapper;
import com.best.billing.volumecalculator.mappers.BaseEntityMapper;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface AccountingPointMapper extends BaseCatalogMapper<AccountingPoint, AccountingPointDTO> {
}
