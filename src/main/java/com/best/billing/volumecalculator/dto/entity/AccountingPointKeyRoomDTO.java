package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointKeyRoomDTO extends BaseEntityDTO {
    Long keyRoomId;
    Long accountingPointId;

    public AccountingPointKeyRoomDTO(Long id, Long keyRoomId, Long accountingPointId) {
        super(id);
        this.keyRoomId = keyRoomId;
        this.accountingPointId = accountingPointId;
    }
}
