package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceProviderDTO;
import com.best.billing.volumecalculator.mappers.BaseEntityMapper;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceProvider;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceProviderMapper extends BaseEntityMapper<AccountingPointServiceProvider, AccountingPointServiceProviderDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "provider.id", target = "providerId")
    @Override
    AccountingPointServiceProviderDTO fromEntity(AccountingPointServiceProvider source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "providerId", target = "provider.id")
    @Override
    AccountingPointServiceProvider toEntity(AccountingPointServiceProviderDTO source);
}
