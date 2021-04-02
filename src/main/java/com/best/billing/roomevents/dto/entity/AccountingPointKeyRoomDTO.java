package com.best.billing.roomevents.dto.entity;

import com.best.billing.base.dto.BaseEntityDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointKeyRoomDTO extends BaseEntityDTO {
    Long keyRoomId;
    Long accountingPointId;

    public AccountingPointKeyRoomDTO(Long id, Long version, Long keyRoomId, Long accountingPointId) {
        super(id, version);
        this.keyRoomId = keyRoomId;
        this.accountingPointId = accountingPointId;
    }
}
