package org.billing.api.service;

import java.util.UUID;
import org.billing.api.request.KeyRoomStateDto;

public interface AccountingPointService {
    KeyRoomStateDto getAll(UUID keyRoomID);

    void save(KeyRoomStateDto currentState);
}
