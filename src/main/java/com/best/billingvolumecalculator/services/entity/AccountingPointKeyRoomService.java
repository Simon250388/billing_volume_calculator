package com.best.billingvolumecalculator.services.entity;

import com.best.billingvolumecalculator.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billingvolumecalculator.services.catalog.BaseEntityService;

import java.util.List;

public interface AccountingPointKeyRoomService extends BaseEntityService<AccountingPointKeyRoom, AccountingPointKeyRoomDTO> {
    List<AccountingPointKeyRoomDTO> findByKeyRoomId(long keyRoomId);
}
