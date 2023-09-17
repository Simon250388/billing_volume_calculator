package org.billing.api.model.accountingPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExist;

@Getter
@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = AccountingPoint.AccountingPointBuilder.class)
public class AccountingPoint {
  @NotNull(
      message = "Ключ точки учета не может быть пустым",
      groups = AccountingPoint.AccountingPointUpdateValidationGroup.class)
  @ValueExist(
          groups = AccountingPoint.AccountingPointUpdateValidationGroup.class,
          valueType = ExistValueType.ACCOUNTING_POINT,
          message = "Точка учета не найдена")
  String id;
  @NotBlank(
      message = "Ключ помещения не может быть пустым",
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.KEYROOM,
      message = "Помещение не найдено")
  @ValueExist(valueType = ExistValueType.KEYROOM, message = "Помещение не найдено")
  String keyRoomId;
  @NotBlank(
      message = "Услуга не заполненна",
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.SERVICE,
      message = "Услуга не найдена")
  @ValueExist(valueType = ExistValueType.SERVICE, message = "Услуга не найдена")
  String serviceId;
  @NotBlank(
      message = "Поставщик не может быть пустым",
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPoint.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.PROVIDER,
      message = "Поставщик не найден")
  @ValueExist(valueType = ExistValueType.PROVIDER, message = "Поставщик не найден")
  String providerId;
  @NotNull(groups = AccountingPoint.AccountingPointCreateValidationGroup.class)
  Boolean active;

  String meterId;

  @NotNull(groups = AccountingPoint.AccountingPointCreateValidationGroup.class)
  Boolean meterActive;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountingPointBuilder {}

  public interface AccountingPointCreateValidationGroup {}

  public interface AccountingPointUpdateValidationGroup {}
}
