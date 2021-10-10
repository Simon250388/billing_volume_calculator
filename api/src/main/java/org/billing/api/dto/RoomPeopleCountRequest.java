package org.billing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = RoomPeopleCountRequest.RoomPeopleCountRequestBuilder.class)
public class RoomPeopleCountRequest {
  @NotNull
  @Positive
  @JsonProperty("keyRoomId")
  Long keyRoomId;

  @NotNull
  @PositiveOrZero
  @JsonProperty("prescribed")
  Long count;
}
