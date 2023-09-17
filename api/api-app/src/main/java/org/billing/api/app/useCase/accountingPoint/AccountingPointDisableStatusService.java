package org.billing.api.app.useCase.accountingPoint;

import org.billing.api.model.accountingPoint.AccountingPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("AccountingPointDisableStatusService")
public class AccountingPointDisableStatusService implements AccountingPointStatusService {
    @Override
    public ResponseEntity<AccountingPoint> update(String accountingPointId, AccountingPoint request) {
        return null;
    }

    @Override
    public ResponseEntity<AccountingPoint> save(AccountingPoint request) {
        return null;
    }

    @Override
    public void update(String accountingPointId) {

    }
}
