package org.billing.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = AuthRequest.AuthRequestBuilder.class)
public class AuthRequest {
  @JsonProperty("username")
  String username;
  @JsonProperty("password")
  String password;

  @JsonPOJOBuilder
  public static class AuthRequestBuilder {}
}
