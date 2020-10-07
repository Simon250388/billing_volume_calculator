package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;

public class AccountingPointKeyRoomDTO extends BaseEntityDTO {
    private long keyRoomId;
    private long accountingPointId;

    public long getKeyRoomId() {
        return keyRoomId;
    }

    public void setKeyRoomId(long keyRoomId) {
        this.keyRoomId = keyRoomId;
    }

    public long getAccountingPointId() {
        return accountingPointId;
    }

    public void setAccountingPointId(long accountingPointId) {
        this.accountingPointId = accountingPointId;
    }
}
