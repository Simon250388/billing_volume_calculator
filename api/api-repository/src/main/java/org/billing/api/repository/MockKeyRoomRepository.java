package org.billing.api.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockKeyRoomRepository implements KeyRoomDbService {
  private final Map<String, KeyRoomRequest> dataSet = new HashMap<>();

  @Override
  public void existOrElseThrow(@NonNull final String id) {
    if (notExistsById(id)) {
      throw new KeyRoomNotFoundException(
          String.format("Помещение с ключом %s не найдено", id));
    }
  }

  @Override
  public KeyRoomResponse save(String keyRoomId, KeyRoomRequest request, String userId) {
    mergeWithRequest(keyRoomId, request);
    return mapToResponse(keyRoomId);
  }

  @Override
  public boolean notExistsById(String id) {
    return !exist(id);
  }

  @Override
  public void saveHistory(KeyRoomRequest request, Instant instant) {}

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

  private Optional<KeyRoomRequest> findById(String id) {
    return Optional.ofNullable(dataSet.getOrDefault(id, null));
  }

  private KeyRoomResponse mapToResponse(String id) {

    KeyRoomRequest model = findById(id).orElseThrow();

    return KeyRoomResponse.builder()
        .id(id)
        .roomTypeId(model.getRoomTypeId())
        .address(model.getAddress())
        .countOwner(model.getCountOwner())
        .countResident(model.getCountResident())
        .countSubscribed(model.getCountSubscribed())
        .square(model.getSquare())
        .build();
  }

  private void mergeWithRequest(String keyRoomId, KeyRoomRequest request) {
    final KeyRoomRequest.KeyRoomRequestBuilder modelBuilder = KeyRoomRequest.builder();

    final Optional<KeyRoomRequest> existModel = findById(keyRoomId);

    Optional.ofNullable(request.getRoomTypeId())
        .ifPresentOrElse(
            modelBuilder::roomTypeId,
            () -> modelBuilder.roomTypeId(existModel.orElseThrow().getRoomTypeId()));

    Optional.ofNullable(request.getAddress())
        .ifPresentOrElse(
            modelBuilder::address,
            () -> modelBuilder.address(existModel.orElseThrow().getAddress()));

    Optional.ofNullable(request.getCountOwner())
        .ifPresentOrElse(
            modelBuilder::countOwner,
            () -> modelBuilder.countOwner(existModel.orElseThrow().getCountOwner()));

    Optional.ofNullable(request.getCountResident())
        .ifPresentOrElse(
            modelBuilder::countResident,
            () -> modelBuilder.countResident(existModel.orElseThrow().getCountResident()));

    Optional.ofNullable(request.getCountSubscribed())
        .ifPresentOrElse(
            modelBuilder::countSubscribed,
            () -> modelBuilder.countSubscribed(existModel.orElseThrow().getCountSubscribed()));

    Optional.ofNullable(request.getSquare())
        .ifPresentOrElse(
            modelBuilder::square, () -> modelBuilder.square(existModel.orElseThrow().getSquare()));

    dataSet.put(keyRoomId, modelBuilder.build());
  }
}
