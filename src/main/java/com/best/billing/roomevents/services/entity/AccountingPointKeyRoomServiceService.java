package com.best.billing.roomevents.services.entity;

import com.best.billing.roomevents.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.List;

public interface AccountingPointKeyRoomServiceService extends BaseEntityService<AccountingPointKeyRoomDTO> {
    List<AccountingPointKeyRoomDTO> findByKeyRoomId(@NonNull final Long keyRoomId);
}
