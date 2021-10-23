package org.billing.accountingpoints.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = AccountingPointRequest.AccountingPointRequestBuilder.class)
public class AccountingPointRequest {
  @JsonDeserialize(using = UUIDDeserializer.class)
  UUID id;

  String name;

  @JsonPOJOBuilder
  public static class AccountingPointRequestBuilder {}
}
