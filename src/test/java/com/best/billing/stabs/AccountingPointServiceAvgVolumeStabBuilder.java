package com.best.billing.stabs;

import com.best.billing.volumecalculator.model.AccountingPointServiceAvgVolume;

public class AccountingPointServiceAvgVolumeStabBuilder {

    public static AccountingPointServiceAvgVolume accountingPointServiceAvgVolume(StabFactory stabFactory, Long avgVolume){
        return AccountingPointServiceAvgVolume.builder()
                .accountingPointKeyRoomServiceEntity(stabFactory.accountingPointKeyRoomServiceEntity)
                .avgVolume(avgVolume)
                .build();
    }

}
