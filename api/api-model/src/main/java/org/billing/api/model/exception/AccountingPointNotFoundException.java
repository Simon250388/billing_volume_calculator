package org.billing.api.model.exception;

public class AccountingPointNotFoundException extends IllegalArgumentException {
  public AccountingPointNotFoundException(String accountingPointId) {
    super(String.format("Точка учета с ключом %s не найдена", accountingPointId));
  }
}
