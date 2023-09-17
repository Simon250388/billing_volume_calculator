package org.billing.api.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockAccountingPointDbService implements AccountingPointDbService {
  private final Map<String, AccountingPoint> dataSet = new HashMap<>();

  @Override
  public void existOrElseThrow(@NonNull final String id) {
    if (notExistsById(id)) {
      throw new KeyRoomNotFoundException(id);
    }
  }

  @Override
  public AccountingPoint save(AccountingPoint request) {
    mergeWithRequest(request);
    return mapToResponse(request.getId());
  }

  @Override
  public boolean notExistsById(String id) {
    return !exist(id);
  }

  @Override
  public void saveHistory(AccountingPoint request, Instant instant) {}

  @Override
  public void deleteById(String id) {
    findById(id).ifPresent(value -> dataSet.remove(id));
  }

  @Override
  public void deleteAll() {
    dataSet.clear();
  }

  @Override
  public boolean exist(String id) {
    return findById(id).isPresent();
  }

  @Override
  public Optional<AccountingPoint> findById(String id) {
    return Optional.ofNullable(dataSet.getOrDefault(id, null)).map(value -> mapToResponse(id));
  }

  private AccountingPoint mapToResponse(String id) {

    final AccountingPoint model = this.dataSet.get(id);

    return AccountingPoint.builder()
        .id(id)
        .keyRoomId(model.getKeyRoomId())
        .active(model.getActive())
        .serviceId(model.getServiceId())
        .providerId(model.getProviderId())
        .meterActive(model.getMeterActive())
        .build();
  }

  private void mergeWithRequest(AccountingPoint request) {
    var builder =
        dataSet
            .computeIfAbsent(request.getId(), id -> AccountingPoint.builder().id(id).build())
            .toBuilder();

    Optional.ofNullable(request.getKeyRoomId()).ifPresent(builder::keyRoomId);
    Optional.ofNullable(request.getActive()).ifPresent(builder::active);
    Optional.ofNullable(request.getServiceId()).ifPresent(builder::serviceId);
    Optional.ofNullable(request.getProviderId()).ifPresent(builder::providerId);
    Optional.ofNullable(request.getMeterActive()).ifPresent(builder::meterActive);
    Optional.ofNullable(request.getMeterActive()).ifPresent(builder::meterActive);

    dataSet.put(request.getId(), builder.build());
  }
}
