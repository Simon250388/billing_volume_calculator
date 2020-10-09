package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;

public class AccountingPointMeterStateDTO extends BaseHistoryDTO {

    private long accountingPointKeyRoomServiceEntityId;
    private long meterId;
    private long meterStateId;

    public long getAccountingPointKeyRoomServiceEntityId() {
        return accountingPointKeyRoomServiceEntityId;
    }

    public void setAccountingPointKeyRoomServiceEntityId(long accountingPointKeyRoomServiceEntityId) {
        this.accountingPointKeyRoomServiceEntityId = accountingPointKeyRoomServiceEntityId;
    }

    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }

    public long getMeterStateId() {
        return meterStateId;
    }

    public void setMeterStateId(long meterStateId) {
        this.meterStateId = meterStateId;
    }
}
