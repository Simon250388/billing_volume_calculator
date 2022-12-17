package org.billing.api.app.controller;

import java.util.Map;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.billing.api.model.exception.AccountingPointNotFoundException;
import org.billing.api.model.exception.KeyRoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler({KeyRoomNotFoundException.class, AccountingPointNotFoundException.class})
  public ResponseEntity<Map<String, String>> handleException(
      Exception exception, HttpServletRequest request) {
    Map<String, String> response =
        Map.of(
            "status",
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "message",
            exception.getMessage(),
            "path",
            request.getRequestURI());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleException(
      MethodArgumentNotValidException exception, HttpServletRequest request) {
    Map<String, String> response =
        Map.of(
            "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "message", "VALIDATION ERROR",
            "path", request.getRequestURI(),
            "error",
                exception.getBindingResult().getFieldErrors().stream()
                    .map(
                        error ->
                            String.format("%s %s", error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.joining(",\n")));
    return ResponseEntity.badRequest().body(response);
  }
}
