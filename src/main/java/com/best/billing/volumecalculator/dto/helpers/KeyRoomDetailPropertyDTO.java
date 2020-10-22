package com.best.billing.volumecalculator.dto.helpers;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class KeyRoomDetailPropertyDTO  {
    Long keyRoomId;
    Integer ownerCount;
    Integer prescribedCount;
    Integer residentCount;
    Integer commonSquare;
    String address;
    String lsNumber;
    Boolean lsIsActive;
    Date lsStateAt;
    /**
     * Сумма задолженности
     */
    Integer debt;

}
