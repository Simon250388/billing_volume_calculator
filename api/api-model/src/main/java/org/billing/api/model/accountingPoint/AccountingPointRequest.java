package org.billing.api.model.accountingPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExist;

@Value
@Builder
@JsonDeserialize(builder = AccountingPointRequest.AccountingPointRequestBuilder.class)
public class AccountingPointRequest {

  @NotBlank(
      message = "Ключ помещения не может быть пустым",
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.KEYROOM,
      message = "Помещение не найдено")
  @ValueExist(valueType = ExistValueType.KEYROOM, message = "Помещение не найдено")
  String keyRoomId;

  @NotBlank(
      message = "Услуга не заполненна",
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.SERVICE,
      message = "Услуга не найдена")
  @ValueExist(valueType = ExistValueType.SERVICE, message = "Услуга не найдена")
  String serviceId;

  @NotBlank(
      message = "Поставщик не может быть пустым",
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class)
  @ValueExist(
      groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class,
      valueType = ExistValueType.PROVIDER,
      message = "Поставщик не найден")
  @ValueExist(valueType = ExistValueType.PROVIDER, message = "Поставщик не найден")
  String providerId;

  @NotNull(groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class)
  Boolean active;

  String meterId;

  @NotNull(groups = AccountingPointRequest.AccountingPointCreateValidationGroup.class)
  Boolean meterIsActive;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountingPointRequestBuilder {}

  public interface AccountingPointCreateValidationGroup {}
}
