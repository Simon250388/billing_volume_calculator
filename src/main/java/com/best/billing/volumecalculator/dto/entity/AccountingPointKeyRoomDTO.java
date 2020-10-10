package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountingPointKeyRoomDTO extends BaseEntityDTO {
    private long keyRoomId;
    private long accountingPointId;
}
