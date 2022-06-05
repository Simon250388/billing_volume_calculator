package org.billing.api.app.useCase.accountingPoint;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.repository.KeyRoomDbService;
import org.billing.api.app.service.AccountingPointService;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointUseCaseService {
    private final AccountingPointService accountingPointService;
    private final KeyRoomDbService keyRoomService;

    public ResponseEntity<Collection<AccountingPointResponse>> list(String keyRoomId) {
        keyRoomService.existOrElseThrow(keyRoomId);
        return ResponseEntity.ok(accountingPointService.getAll(keyRoomId));
    }

    public ResponseEntity<AccountingPointResponse> update(@NonNull String accountingPointId, @NonNull AccountingPointRequest request) {
        accountingPointService.accountingPointExistOrElseThrow(accountingPointId);
        return ResponseEntity.ok(accountingPointService.save(accountingPointId, request));
    }

    public ResponseEntity<AccountingPointResponse> save(@NonNull AccountingPointRequest request) {
        final String accountingPointId = UUID.randomUUID().toString();
        return ResponseEntity.ok(accountingPointService.save(accountingPointId, request));
    }

    public ResponseEntity<Void> delete(@NonNull String accountingPointId) {
        accountingPointService.accountingPointExistOrElseThrow(accountingPointId);
        accountingPointService.delete(accountingPointId);
        return ResponseEntity.noContent().build();
    }
}
