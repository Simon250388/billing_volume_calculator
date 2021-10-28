package org.billing.accountingpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BillingValidationException extends RuntimeException {
  private static final long serialVersionUID = -757674936458112645L;

  public BillingValidationException(String message) {
    super(message);
  }
}
