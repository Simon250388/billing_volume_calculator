package com.best.billing.volumecalculator.services.entity;

import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoom;
import com.best.billing.volumecalculator.services.catalog.BaseEntityService;

import java.util.List;

public interface AccountingPointKeyRoomService extends BaseEntityService<AccountingPointKeyRoom> {
    List<AccountingPointKeyRoom> findByKeyRoomId(long keyRoomId);
}
