package org.billing.api.app.validator;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.billing.api.repository.KeyRoomDbService;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExistChecker;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyRoomValueExistChecker implements ValueExistChecker {

    private final KeyRoomDbService keyRoomDbService;

    @Override
    public boolean exist(String value) {
        return Optional.ofNullable(value).map(keyRoomDbService::exist).orElse(true);
    }

    @Override
    public ExistValueType getValueType() {
        return ExistValueType.KEYROOM;
    }
}
