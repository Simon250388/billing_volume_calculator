package com.best.billing.servicebuilder.mappers.historychange;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.servicebuilder.mappers.BaseHistoryMapper;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceStateMapper extends BaseHistoryMapper<AccountingPointServiceState, AccountingPointServiceStateDTO> {


    @Mapping(source = "row.id", target = "id")
    @Mapping(source = "row.period", target = "period")
    AccountingPointServiceStateDTO fromEntityAndMeterState(AccountingPointServiceState row, AccountingPointMeterState activeMeter);
}
