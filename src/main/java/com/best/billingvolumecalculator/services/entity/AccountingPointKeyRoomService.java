package com.best.billingvolumecalculator.services.entity;

import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billingvolumecalculator.services.catalog.BaseEntityService;

import java.util.List;

public interface AccountingPointKeyRoomService extends BaseEntityService<AccountingPointKeyRoom> {
    List<AccountingPointKeyRoom> findByKeyRoomId(long keyRoomId);
}
