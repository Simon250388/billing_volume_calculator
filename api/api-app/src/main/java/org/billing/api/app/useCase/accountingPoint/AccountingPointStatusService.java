package org.billing.api.app.useCase.accountingPoint;

import org.springframework.http.ResponseEntity;

public interface AccountingPointStatusService {
    ResponseEntity<AccountingPointResponse> update(AccountingPointRequest request);

    ResponseEntity<AccountingPointResponse> save(AccountingPointRequest request);

    void update(String accountingPointId);
}
