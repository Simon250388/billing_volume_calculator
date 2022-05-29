package org.billing.api.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockAccountingPointDbService implements AccountingPointDbService {
    private final Map<String, AccountingPointRequest> dataSet = new HashMap<>();

    @Override
    public void existOrElseThrow(@NonNull final String id) {
        if (notExistsById(id)) {
            throw new KeyRoomNotFoundException(
                    String.format("Помещение с ключом %s не найдено", id));
        }
    }

    @Override
    public AccountingPointResponse save(String id, AccountingPointRequest request) {
        mergeWithRequest(id, request);
        return mapToResponse(id);
    }

    @Override
    public boolean notExistsById(String id) {
        return !exist(id);
    }

    @Override
    public void saveHistory(AccountingPointRequest request, Instant instant) {}

    @Override
    public void deleteById(String id) {
        findById(id).ifPresent(value -> dataSet.remove(id));
    }

    @Override
    public boolean exist(String id) {
        return findById(id).isPresent();
    }

    private Optional<AccountingPointRequest> findById(String id) {
        return Optional.ofNullable(dataSet.getOrDefault(id, null));
    }

    private AccountingPointResponse mapToResponse(String id) {

        AccountingPointRequest model = findById(id).orElseThrow();

        return AccountingPointResponse.builder()
                .id(id)
                .keyRoomId(model.getKeyRoomId())
                .active(model.getActive())
                .serviceId(model.getServiceId())
                .providerId(model.getProviderId())
                .build();
    }

    private void mergeWithRequest(String id, AccountingPointRequest request) {
        final AccountingPointRequest.AccountingPointRequestBuilder modelBuilder = AccountingPointRequest.builder();

        final Optional<AccountingPointRequest> existModel = findById(id);

        Optional.ofNullable(request.getKeyRoomId())
                .ifPresentOrElse(
                        modelBuilder::keyRoomId,
                        () -> modelBuilder.keyRoomId(existModel.orElseThrow().getKeyRoomId()));

        Optional.ofNullable(request.getActive())
                .ifPresentOrElse(
                        modelBuilder::active,
                        () -> modelBuilder.active(existModel.orElseThrow().getActive()));

        Optional.ofNullable(request.getServiceId())
                .ifPresentOrElse(
                        modelBuilder::serviceId,
                        () -> modelBuilder.serviceId(existModel.orElseThrow().getServiceId()));

        Optional.ofNullable(request.getProviderId())
                .ifPresentOrElse(
                        modelBuilder::providerId,
                        () -> modelBuilder.providerId(existModel.orElseThrow().getProviderId()));

        dataSet.put(id, modelBuilder.build());
    }
}
