package org.billing.api.app.validator;

import lombok.RequiredArgsConstructor;
import org.billing.api.repository.AccountServiceDbService;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExistChecker;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceValueExistChecker implements ValueExistChecker {

    private final AccountServiceDbService accountServiceDbService;

    @Override
    public boolean exist(String value) {
        return accountServiceDbService.existsById(value);
    }

    @Override
    public ExistValueType getValueType() {
        return ExistValueType.SERVICE;
    }
}
