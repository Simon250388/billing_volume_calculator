package com.best.billing.servicebuilder.services.entity;

import com.best.billing.servicebuilder.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface AccountingPointKeyRoomServiceService extends BaseEntityService<AccountingPointKeyRoomDTO> {
    List<AccountingPointKeyRoomDTO> findByKeyRoomId(@NotNull final Long keyRoomId);
}
