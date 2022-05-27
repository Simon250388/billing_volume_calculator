package org.billing.api.app.useCase.accountingPoint;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.service.AccountingPointService;
import org.billing.api.app.service.KeyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountingPointUseCaseService {
    private final Map<Boolean, AccountingPointStatusService> statusServices;
    private final AccountingPointService accountingPointService;
    private final KeyRoomService keyRoomService;

    @Autowired
    public AccountingPointUseCaseService(
            @Qualifier("AccountingPointActiveStatusService") AccountingPointStatusService activeStatusService,
            @Qualifier("AccountingPointDisableStatusService") AccountingPointStatusService notActiveStatusService,
            AccountingPointService accountingPointService,
            KeyRoomService keyRoomService
    ) {

        this.accountingPointService = accountingPointService;
        this.keyRoomService = keyRoomService;
        this.statusServices = Map.of(
                true, activeStatusService,
                false, notActiveStatusService
        );
    }

    public ResponseEntity<Collection<AccountingPointResponse>> list(String keyRoomId) {
        keyRoomService.keyRoomExistOrElseThrow(keyRoomId);
        return ResponseEntity.ok(accountingPointService.getAll(keyRoomId));
    }

    public ResponseEntity<AccountingPointResponse> update(@NonNull AccountingPointRequest request) {
        keyRoomService.keyRoomExistOrElseThrow(request.getKeyRoomId());
        accountingPointService.accountingPointExistOrElseThrow(request.getAccountingPointId());
        return statusServices.get(request.isActive()).update(request);
    }

    public ResponseEntity<AccountingPointResponse> save(@NonNull AccountingPointRequest request) {
        return statusServices.get(request.isActive()).save(request);
    }

    public ResponseEntity<Void> delete(@NonNull String keyRoomId, @NonNull String accountingPointId) {
        keyRoomService.keyRoomExistOrElseThrow(keyRoomId);
        accountingPointService.accountingPointExistOrElseThrow(accountingPointId);
        boolean status = accountingPointService.accountingPointStatus(accountingPointId);
        Optional.ofNullable(statusServices.get(status))
                .orElseThrow(() -> new IllegalStateException(
                        String.format("AccountingPointActiveStatusService with status %s not found", status)))
                .update(accountingPointId);
        return ResponseEntity.ok().build();
    }
}