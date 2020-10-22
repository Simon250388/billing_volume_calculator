package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceStateMapper extends BaseHistoryMapper<AccountingPointServiceState, AccountingPointServiceStateDTO> {


    @Mapping(source = "row.id", target = "id")
    @Mapping(source = "row.period", target = "period")
    AccountingPointServiceStateDTO fromEntityAndMeterState(AccountingPointServiceState row, AccountingPointMeterState activeMeter);
}
