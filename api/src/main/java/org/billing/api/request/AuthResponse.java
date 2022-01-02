package org.billing.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AuthResponse.AuthResponseBuilder.class)
public class AuthResponse {
  String token;

  @JsonPOJOBuilder
  public static class AuthResponseBuilder {}
}
