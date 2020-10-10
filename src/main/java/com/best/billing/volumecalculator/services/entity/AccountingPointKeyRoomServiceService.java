package com.best.billing.volumecalculator.services.entity;

import com.best.billing.volumecalculator.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.List;

public interface AccountingPointKeyRoomServiceService extends BaseEntityService<AccountingPointKeyRoomDTO> {
    List<AccountingPointKeyRoomDTO> findByKeyRoomId(long keyRoomId);
}
