package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceStateMapper extends BaseHistoryMapper<AccountingPointServiceState, AccountingPointServiceStateDTO> {
    AccountingPointServiceStateDTO fromEntityAndMeterState(AccountingPointServiceState row, AccountingPointMeterState activeMeter);
}
