package org.billing.api.app.useCase.accountingPoint;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
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
    private final Map<Boolean, AccountingPointStatusService> statusServices;
    private final AccountingPointService accountingPointService;
    private final KeyRoomDbService keyRoomService;

    @Autowired
    public AccountingPointUseCaseService(
            @Qualifier("AccountingPointActiveStatusService") AccountingPointStatusService activeStatusService,
            @Qualifier("AccountingPointDisableStatusService") AccountingPointStatusService notActiveStatusService,
            AccountingPointService accountingPointService,
            KeyRoomDbService keyRoomService
    ) {

        this.accountingPointService = accountingPointService;
        this.keyRoomService = keyRoomService;
        this.statusServices = Map.of(
                true, activeStatusService,
                false, notActiveStatusService
        );
    }

    public ResponseEntity<Collection<AccountingPointResponse>> list(String keyRoomId) {
        keyRoomService.existOrElseThrow(keyRoomId);
        return ResponseEntity.ok(accountingPointService.getAll(keyRoomId));
    }

    public ResponseEntity<AccountingPointResponse> update(@NonNull AccountingPointRequest request) {
        return statusServices.get(request.getActive()).update(request);
    }

    public ResponseEntity<AccountingPointResponse> save(@NonNull AccountingPointRequest request) {
        return statusServices.get(request.getActive()).save(request);
    }

    public ResponseEntity<Void> delete(@NonNull String keyRoomId, @NonNull String accountingPointId) {
        keyRoomService.existOrElseThrow(keyRoomId);
        accountingPointService.accountingPointExistOrElseThrow(accountingPointId);
        boolean status = accountingPointService.accountingPointStatus(accountingPointId);
        Optional.ofNullable(statusServices.get(status))
                .orElseThrow(() -> new IllegalStateException(
                        String.format("AccountingPointActiveStatusService with status %s not found", status)))
                .update(accountingPointId);
        return ResponseEntity.ok().build();
    }
}
