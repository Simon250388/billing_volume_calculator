package org.billing.api.app.useCase.accountingPoint;

import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("AccountingPointDisableStatusService")
public class AccountingPointDisableStatusService implements AccountingPointStatusService {
    @Override
    public ResponseEntity<AccountingPointResponse> update(String accountingPointId, AccountingPointRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<AccountingPointResponse> save(AccountingPointRequest request) {
        return null;
    }

    @Override
    public void update(String accountingPointId) {

    }
}
