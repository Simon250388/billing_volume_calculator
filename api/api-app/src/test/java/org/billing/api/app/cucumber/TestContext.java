package org.billing.api.app.cucumber;

import org.springframework.http.ResponseEntity;

public enum TestContext {
  CONTEXT;

  private String token;

  private ResponseEntity<Object> response;

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public <T> void setResponse(ResponseEntity<T> response) {
    this.response = (ResponseEntity<Object>) response;
  }

  public <T> ResponseEntity<T> getResponse() {
    return (ResponseEntity<T>) response;
  }
}
