package com.best.billing.volumecalculator.services.helpers;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import org.springframework.stereotype.Service;

@Service
public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(long keyRoomId);

}
