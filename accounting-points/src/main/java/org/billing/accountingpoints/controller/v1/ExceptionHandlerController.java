package org.billing.accountingpoints.controller.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.billing.accountingpoints.request.BillingServerError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<BillingServerError> handleDMSRESTException(
      MethodArgumentNotValidException objException) {
    BindingResult result = objException.getBindingResult();

    List<Map.Entry<String, String>> errors = new ArrayList<>();

    result
        .getFieldErrors()
        .forEach(
            fieldError ->
                errors.add(
                    Map.entry(
                        fieldError.getField(),
                        Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""))));

    return ResponseEntity.badRequest()
        .body(
            BillingServerError.builder()
                .message("validation error")
                .errors(errors)
                .build());
  }
}
