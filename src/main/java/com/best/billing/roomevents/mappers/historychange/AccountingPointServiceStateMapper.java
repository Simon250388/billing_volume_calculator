package com.best.billing.roomevents.mappers.historychange;

import com.best.billing.roomevents.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.roomevents.models.AccountingPointMeterState;
import com.best.billing.roomevents.models.AccountingPointServiceState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceStateMapper extends BaseHistoryMapper<AccountingPointServiceState, AccountingPointServiceStateDTO> {


    @Mapping(source = "row.id", target = "id")
    @Mapping(source = "row.period", target = "period")
    @Mapping(source = "row.version", target = "version")
    AccountingPointServiceStateDTO fromEntityAndMeterState(AccountingPointServiceState row, AccountingPointMeterState activeMeter);
}
