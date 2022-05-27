package org.billing.api.app.useCase.password;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = PasswordForgotRequest.PasswordForgotRequestBuilder.class)
public class PasswordForgotRequest {

    @JsonPOJOBuilder
    public static class PasswordForgotRequestBuilder {}
}
