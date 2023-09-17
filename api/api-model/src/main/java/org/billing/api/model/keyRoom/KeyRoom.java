package org.billing.api.model.keyRoom;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExist;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = KeyRoom.KeyRoomBuilder.class)
public class KeyRoom {
  @ValueExist(
          groups = KeyRoom.KeyRoomUpdateValidationGroup.class,
          valueType = ExistValueType.KEYROOM,
          message = "Ключ помещения не найден")
  String id;
  @NotEmpty(groups = KeyRoomCreateValidationGroup.class)
  String address;
  @NotNull(groups = KeyRoomCreateValidationGroup.class)
  Long roomTypeId;
  @NotNull(groups = KeyRoomCreateValidationGroup.class)
  Long countResident;
  @NotNull(groups = KeyRoomCreateValidationGroup.class)
  Long countSubscribed;
  @NotNull(groups = KeyRoomCreateValidationGroup.class)
  @Min(
      value = 1,
      message = "Количество собственников не может быть меньше 1",
      groups = KeyRoomCreateValidationGroup.class)
  @Min(value = 1, message = "Количество собственников не может быть меньше 1")
  Long countOwner;
  @NotNull(groups = KeyRoomCreateValidationGroup.class)
  @DecimalMin(
      value = "1",
      message = "Площадь не может быть меньше 1",
      groups = KeyRoomCreateValidationGroup.class)
  @DecimalMin(value = "1", message = "Площадь не может быть меньше 1")
  BigDecimal square;
  @JsonPOJOBuilder(withPrefix = "")
  public static class KeyRoomBuilder {}
  public interface KeyRoomCreateValidationGroup {}
  public interface KeyRoomUpdateValidationGroup {}
}
