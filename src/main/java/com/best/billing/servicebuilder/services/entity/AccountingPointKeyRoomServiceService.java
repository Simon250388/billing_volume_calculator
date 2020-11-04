package com.best.billing.servicebuilder.services.entity;

import com.best.billing.servicebuilder.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.List;

public interface AccountingPointKeyRoomServiceService extends BaseEntityService<AccountingPointKeyRoomDTO> {
    List<AccountingPointKeyRoomDTO> findByKeyRoomId(@NonNull final Long keyRoomId);
}
