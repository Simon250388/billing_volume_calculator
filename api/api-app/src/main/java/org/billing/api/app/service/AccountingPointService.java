package org.billing.api.app.service;

import java.util.Collection;
import java.util.Collections;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointService {
    public Collection<AccountingPointResponse> getAll(@NonNull final String keyRoomID) {
        return Collections.emptyList();
    }

    public void accountingPointExistOrElseThrow(@NonNull final String accountingPointId) {}

    public boolean accountingPointStatus(@NonNull final String accountingPointId ) {
        return false;
    }

}
