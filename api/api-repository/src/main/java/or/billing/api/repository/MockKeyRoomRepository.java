package or.billing.api.repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.billing.api.model.keyRoom.KeyRoomRequest;
import org.billing.api.model.keyRoom.KeyRoomResponse;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MockKeyRoomRepository implements KeyRoomDbService {

  @Value
  @Builder
  private static class MockKeyRoomModel {
    String keyRoomId;
    String userId;
    String address;
    Long roomTypeId;
    Long countOwner;
    Long countResident;
    Long countSubscribed;
    BigDecimal square;
  }

  @Override
  public void keyRoomExistOrElseThrow(@NonNull final String keyRoomId) {
    if (notExistsById(keyRoomId)) {
      throw new KeyRoomNotFoundException(
              String.format("Помещение с ключом %s не найдено", keyRoomId));
    }
  }

  private final Set<MockKeyRoomModel> dataSet = new HashSet<>();

  @Override
  public KeyRoomResponse save(String keyRoomId, KeyRoomRequest request, String userId) {

    MockKeyRoomModel model = mergeWithRequest(keyRoomId, request, userId);

    deleteById(keyRoomId);

    dataSet.add(model);

    return mapToResponse(model);
  }

  @Override
  public boolean notExistsById(String keyRoomId) {
    return findById(keyRoomId).isEmpty();
  }

  @Override
  public void saveHistory(KeyRoomRequest request, Instant instant) {}

  @Override
  public void deleteById(String keyRoomId) {
    findById(keyRoomId).ifPresent(dataSet::remove);
  }

  private Optional<MockKeyRoomModel> findById(String uuid) {
    return dataSet.stream().filter(value -> value.getKeyRoomId().equals(uuid)).findAny();
  }

  private KeyRoomResponse mapToResponse(MockKeyRoomModel model) {
    return KeyRoomResponse.builder()
            .id(model.getKeyRoomId())
            .roomTypeId(model.getRoomTypeId())
            .address(model.address)
            .countOwner(model.getCountOwner())
            .countResident(model.getCountResident())
            .countSubscribed(model.countSubscribed)
            .square(model.getSquare())
            .build();
  }

  private MockKeyRoomModel mergeWithRequest(String keyRoomId, KeyRoomRequest request, String userId) {
    final MockKeyRoomModel.MockKeyRoomModelBuilder modelBuilder =
            MockKeyRoomModel.builder()
                    .keyRoomId(keyRoomId)
                    .userId(userId)
                    .roomTypeId(request.getRoomTypeId());

    final Optional<MockKeyRoomModel> existModel = findById(keyRoomId);

    Optional.ofNullable(request.getRoomTypeId())
            .ifPresentOrElse(
                    modelBuilder::roomTypeId,
                    () -> modelBuilder.roomTypeId(existModel.orElseThrow().roomTypeId));

    Optional.ofNullable(request.getAddress())
            .ifPresentOrElse(
                    modelBuilder::address,
                    () -> modelBuilder.address(existModel.orElseThrow().address));

    Optional.ofNullable(request.getCountOwner())
            .ifPresentOrElse(
                    modelBuilder::countOwner,
                    () -> modelBuilder.countOwner(existModel.orElseThrow().countOwner));

    Optional.ofNullable(request.getCountResident())
            .ifPresentOrElse(
                    modelBuilder::countResident,
                    () -> modelBuilder.countResident(existModel.orElseThrow().countResident));

    Optional.ofNullable(request.getCountSubscribed())
            .ifPresentOrElse(
                    modelBuilder::countSubscribed,
                    () -> modelBuilder.countSubscribed(existModel.orElseThrow().countSubscribed));

    Optional.ofNullable(request.getSquare())
            .ifPresentOrElse(
                    modelBuilder::square,
                    () -> modelBuilder.square(existModel.orElseThrow().square));

    return modelBuilder.build();
  }

}
