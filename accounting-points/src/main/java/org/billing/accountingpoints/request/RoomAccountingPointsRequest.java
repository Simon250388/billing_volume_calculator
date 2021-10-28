package org.billing.accountingpoints.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = RoomAccountingPointsRequest.RoomAccountingPointsRequestBuilder.class)
public class RoomAccountingPointsRequest {

  @NotNull
  UUID keyRoomId;

  @NotNull
  OffsetDateTime period;

  OffsetDateTime periodFact;

  @NotNull
  List<AccountingPointStateRequest> accountingPoints;

  @JsonPOJOBuilder
  public static class RoomAccountingPointsRequestBuilder {}
}
