package org.billing.api.app.useCase.accountingPoint;

import java.util.Collection;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.service.AccountingPointService;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.billing.api.repository.KeyRoomDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointUseCaseService {
    private final AccountingPointService accountingPointService;
    private final KeyRoomDbService keyRoomService;

    public ResponseEntity<Collection<AccountingPoint>> list(String keyRoomId) {
        keyRoomService.existOrElseThrow(keyRoomId);
        return ResponseEntity.ok(accountingPointService.getAll(keyRoomId));
    }

    public ResponseEntity<AccountingPoint> update(@NonNull AccountingPoint request) {
        return ResponseEntity.ok(accountingPointService.save(request));
    }

    public ResponseEntity<AccountingPoint> create(@NonNull AccountingPoint request) {
        final String id = UUID.randomUUID().toString();
        return ResponseEntity.ok(accountingPointService.save(request.toBuilder().id(id).build()));
    }

    public ResponseEntity<Void> delete(@NonNull String accountingPointId) {
        accountingPointService.accountingPointExistOrElseThrow(accountingPointId);
        accountingPointService.delete(accountingPointId);
        return ResponseEntity.noContent().build();
    }
}
