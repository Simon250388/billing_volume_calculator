package org.billing.api.service.Impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.billing.api.request.KeyRoomStateDto;
import org.billing.api.service.AccountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountingPointServiceImpl implements AccountingPointService {

    @Override
    public KeyRoomStateDto getAll(UUID keyRoomID) {
        return KeyRoomStateDto.builder().build();
    }

    @Override
    public void save(KeyRoomStateDto currentState) {

    }
}
