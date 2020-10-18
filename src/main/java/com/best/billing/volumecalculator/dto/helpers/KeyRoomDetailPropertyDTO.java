package com.best.billing.volumecalculator.dto.helpers;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Date;

@Value

@EqualsAndHashCode(callSuper = true)
public class KeyRoomDetailPropertyDTO extends BaseEntityDTO {
    int ownerCount;
    int prescribedCount;
    int residentCount;
    int commonSquare;
    String address;
    String lsNumber;
    Boolean lsIsActive;
    Date lsStateAt;
    /**
     * Сумма задолженности
     */
    int debt;
    @Builder
    public KeyRoomDetailPropertyDTO(Long id, int ownerCount, int prescribedCount, int residentCount, int commonSquare, String address, String lsNumber, Boolean lsIsActive, Date lsStateAt, int debt) {
        super(id);
        this.ownerCount = ownerCount;
        this.prescribedCount = prescribedCount;
        this.residentCount = residentCount;
        this.commonSquare = commonSquare;
        this.address = address;
        this.lsNumber = lsNumber;
        this.lsIsActive = lsIsActive;
        this.lsStateAt = lsStateAt;
        this.debt = debt;
    }
}
