package com.best.billing.servicebuilder.services.helpers;

import com.best.billing.servicebuilder.dto.helpers.ActiveAccountingPointDetailsDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NonNull Long keyRoomId);
}
