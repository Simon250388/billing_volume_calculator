package org.billing.api.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.billing.api.model.keyRoom.KeyRoom;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockKeyRoomRepository implements KeyRoomDbService {
  private final Map<String, KeyRoom> dataSet = new HashMap<>();

  @Override
  public void existOrElseThrow(@NonNull final String id) {
    if (notExistsById(id)) {
      throw new KeyRoomNotFoundException(id);
    }
  }

  @Override
  public KeyRoom save(KeyRoom request, String userId) {
    mergeWithRequest(request);
    return mapToResponse(request.getId());
  }

  @Override
  public boolean notExistsById(String id) {
    return !exist(id);
  }

  @Override
  public void saveHistory(KeyRoom request, Instant instant) {}

  @Override
  public void deleteById(String id) {
    findById(id).ifPresent(value -> dataSet.remove(id));
  }

  @Override
  public boolean exist(String id) {
    return findById(id).isPresent();
  }

  @Override
  public void deleteAll() {
    dataSet.clear();
  }

  private Optional<KeyRoom> findById(String id) {
    return Optional.ofNullable(dataSet.getOrDefault(id, null));
  }

  private KeyRoom mapToResponse(String id) {

    KeyRoom model = findById(id).orElseThrow();

    return KeyRoom.builder()
        .id(id)
        .roomTypeId(model.getRoomTypeId())
        .address(model.getAddress())
        .countOwner(model.getCountOwner())
        .countResident(model.getCountResident())
        .countSubscribed(model.getCountSubscribed())
        .square(model.getSquare())
        .build();
  }

  private void mergeWithRequest(KeyRoom request) {
    var builder =
            dataSet
                    .computeIfAbsent(request.getId(), id -> KeyRoom.builder().id(id).build())
                    .toBuilder();

    Optional.ofNullable(request.getRoomTypeId()).ifPresent(builder::roomTypeId);
    Optional.ofNullable(request.getAddress()).ifPresent(builder::address);
    Optional.ofNullable(request.getCountOwner()).ifPresent(builder::countOwner);
    Optional.ofNullable(request.getCountResident()).ifPresent(builder::countResident);
    Optional.ofNullable(request.getCountSubscribed()).ifPresent(builder::countSubscribed);
    Optional.ofNullable(request.getSquare()).ifPresent(builder::square);

    dataSet.put(request.getId(), builder.build());
  }
}
