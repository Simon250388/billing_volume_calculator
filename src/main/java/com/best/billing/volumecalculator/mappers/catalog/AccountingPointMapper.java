package com.best.billing.volumecalculator.mappers.catalog;

import com.best.billing.volumecalculator.dto.catalog.AccountingPointDTO;
import com.best.billing.volumecalculator.mappers.BaseCatalogMapper;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountingPointMapper extends BaseCatalogMapper<AccountingPoint, AccountingPointDTO> {
}
